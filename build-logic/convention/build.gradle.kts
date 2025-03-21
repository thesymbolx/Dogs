plugins {
    `kotlin-dsl`
}

group = "uk.co.chip.buildlogic" // Package name for the our plugins

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        create("androidLibraryConventionPlugin") {
            id = "uk.co.chip.convention.application.lib"
            implementationClass = "uk.co.chip.convention.AndroidLibraryConventionPlugin"
        }
    }
}