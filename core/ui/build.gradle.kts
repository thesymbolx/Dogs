plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.library")
    id("uk.co.chip.convention.compose")
}

android {
    namespace = "uk.co.chip.dogs"
}

dependencies {

    implementation(libs.bundles.coil)
    implementation(project(":core:design_system"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}