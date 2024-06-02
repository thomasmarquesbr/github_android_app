import org.jetbrains.kotlin.gradle.plugin.kotlinToolingVersion

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
//    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.thomas.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isJniDebuggable = true
            buildConfigField("String", "BASE_URL", "\"https://api.github.com\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://api.github.com\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.retrofitGson)
    implementation(libs.okhttpLogginInterceptor)
    implementation(libs.koin)
    implementation(libs.coroutines)
    testImplementation(libs.test.koin)
    testImplementation(libs.test.koinJunit)
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.okhttpMockwebserver)
    testImplementation(libs.test.turbine)
    testImplementation(libs.test.coroutines)
    implementation(project(":domain"))
}