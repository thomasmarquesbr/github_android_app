package com.thomas.data.di

import com.google.gson.GsonBuilder
import com.thomas.data.repository.GithubAPIDataSource
import com.thomas.data.repository.GithubServiceAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val GITHUB_API = "github_api"

val remoteModule = module {
    single(named(GITHUB_API)) {
        providesOkHttpClient()
    }

    single(named(GITHUB_API)) {
        provideRetrofit(
            okHttpClient = get(named(GITHUB_API)) as OkHttpClient,
            baseUrl = "https://api.github.com"
        )
    }

    factory<GithubServiceAPI> {
        provideApi(retrofit = get(named(GITHUB_API)))
    }

    factory {
        GithubAPIDataSource(
            get() as GithubServiceAPI
        )
    }
}

fun providesOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    return OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    baseUrl: String
): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    return Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

inline fun <reified T> provideApi(retrofit: Retrofit): T = retrofit.create(T::class.java)