plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.hilt")
    id("kotlinx-serialization")
    id("uk.co.chip.convention.library")
    id("uk.co.chip.convention.compose")
}

android {
    namespace = "co.uk.chip.dogs.breeds"
}

dependencies {
    implementation(project(":domain:breeds"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:design_system"))
    testImplementation(project(":core:test"))
}