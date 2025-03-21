plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    id("uk.co.chip.convention.library")
}

android {
    namespace = "uk.co.chip.breeds"
}

dependencies {
    implementation(project(":data:breeds"))
    implementation(project(":core:network"))

    ksp(libs.hilt.complier)
    implementation(libs.hilt)
    implementation(libs.kotlinx.collections.immutable)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutine.test)
    testImplementation(project(":core:test"))
    androidTestImplementation(libs.androidx.junit)
}