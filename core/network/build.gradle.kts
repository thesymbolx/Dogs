plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlinx-serialization")
    id("uk.co.chip.convention.library")
}

android {
    namespace = "uk.co.chip.network"
}

dependencies {

    ksp(libs.hilt.complier)
    implementation(libs.hilt)

    implementation(libs.retrofit)
    implementation(libs.retrofit.ktx.serialization)
    implementation(libs.kotlinx.serialization.json)
    implementation(platform(libs.okhttp.bom))
}