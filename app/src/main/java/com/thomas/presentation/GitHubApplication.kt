package com.thomas.presentation

import android.app.Application
import com.thomas.presentation.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

internal class GitHubApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        GlobalContext.startKoin {
            androidContext(this@GitHubApplication)
            modules(appModules)
        }
    }
}