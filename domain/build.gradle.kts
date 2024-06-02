plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.koin)
    implementation(libs.coroutines)
    testImplementation(libs.test.mockk)
    testImplementation(libs.test.core)
    testImplementation(libs.test.turbine)
    testImplementation(libs.test.archCore)
}