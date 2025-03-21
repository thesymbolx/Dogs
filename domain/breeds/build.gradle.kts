plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.hilt")
    id("uk.co.chip.convention.library")
}

android {
    namespace = "uk.co.chip.breeds"
}

dependencies {
    implementation(project(":data:breeds"))
    implementation(project(":core:network"))
    testImplementation(project(":core:test"))

    implementation(libs.kotlinx.collections.immutable)
}