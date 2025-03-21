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

    implementation(libs.kotlinx.collections.immutable)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutine.test)
    testImplementation(project(":core:test"))
    androidTestImplementation(libs.androidx.junit)
}