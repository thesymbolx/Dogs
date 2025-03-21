plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.library")
    id("uk.co.chip.convention.compose")
    id("uk.co.chip.convention.hilt")

}

android {
    namespace = "co.uk.chip.dogs.images"
}

dependencies {
    implementation(project(":domain:breeds"))
    implementation(project(":core:network"))
    implementation(project(":data:breeds"))
    implementation(project(":core:ui"))
    implementation(project(":core:design_system"))
    testImplementation(project(":core:test"))

    implementation(libs.bundles.coil)
}