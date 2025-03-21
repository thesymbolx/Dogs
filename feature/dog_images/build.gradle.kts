plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlinx-serialization")
    id("uk.co.chip.convention.library")
    id("uk.co.chip.convention.compose")
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

    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)
    
    implementation(libs.bundles.coil)

    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.complier)
    implementation(libs.hilt)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutine.test)
    testImplementation(project(":core:test"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}