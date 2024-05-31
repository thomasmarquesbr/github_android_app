plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    val koinVersion = "3.2.2"
    val mockkVersion = "1.13.11"
    // Koin
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    // Mockk
    testImplementation("io.mockk:mockk:${mockkVersion}")
}