plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("uk.co.chip.convention.compose")
    id("uk.co.chip.convention.library")
}

android {
    namespace = "co.uk.chip.dogs.design_system"
}