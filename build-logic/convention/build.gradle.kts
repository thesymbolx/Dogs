import org.jetbrains.kotlin.gradle.dsl.JvmTarget

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

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}

gradlePlugin {
    plugins {
        create("androidLibrary") {
            id = "uk.co.chip.convention.library"
            implementationClass = "uk.co.chip.convention.AndroidLibraryConventionPlugin"
        }

        create("androidCompose") {
            id = "uk.co.chip.convention.compose"
            implementationClass = "uk.co.chip.convention.AndroidLibraryComposeConventionPlugin"
        }
    }
}