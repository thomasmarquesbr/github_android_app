plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation("org.testng:testng:6.9.6")
    val koinVersion = "3.2.2"
    val mockkVersion = "1.13.11"
    // Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    // Mockk
    testImplementation("io.mockk:mockk:${mockkVersion}")

    testImplementation("org.assertj:assertj-core:3.20.2")
    testImplementation("androidx.test:core:1.4.0")
    testImplementation("app.cash.turbine:turbine:0.5.2")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}