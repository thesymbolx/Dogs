plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.library")
}

android {
    namespace = "uk.co.chip.common"
}

dependencies {

    implementation(libs.junit)
    implementation(libs.coroutine.test)
    androidTestImplementation(libs.androidx.junit)
}