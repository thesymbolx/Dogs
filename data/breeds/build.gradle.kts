plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("uk.co.chip.convention.library")
}

android {
    namespace = "uk.co.chip.data"
}

dependencies {
    implementation(project(":core:network"))
    ksp(libs.hilt.complier)
    implementation(libs.hilt)



    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.annotation) // Or the latest version

    androidTestImplementation(platform(libs.androidx.compose.bom))
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutine.test)
    testImplementation(project(":core:test"))
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.ui.test.junit4)
}