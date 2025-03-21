plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.hilt")
    id("kotlinx-serialization")
    id("uk.co.chip.convention.library")
}

android {
    namespace = "uk.co.chip.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.ktx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.okhttp.bom))
}