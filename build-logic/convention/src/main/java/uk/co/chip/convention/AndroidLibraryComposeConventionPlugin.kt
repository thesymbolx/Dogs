package uk.co.chip.convention

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("kotlinx-serialization")
            }

            val extension = extensions.getByType<LibraryExtension>()

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            extension.apply {
                buildFeatures {
                    compose = true
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }

                dependencies {
                    val bom = libs.findLibrary("androidx-compose-bom").get()
                    add("implementation", platform(bom))
                    add("androidTestImplementation", platform(bom))

                    add("implementation", libs.findLibrary("androidx-navigation-compose").get())
                    add("implementation", libs.findLibrary("androidx.material3").get())
                    add("implementation", libs.findLibrary("ui.tooling").get())
                    add("implementation", libs.findLibrary("androidx.ui.tooling.preview.android").get())

                    //Need for type safe navigation
                    add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
                    //Need for UiState recompositions
                    add("implementation", libs.findLibrary("kotlinx.collections.immutable").get())

                    add("androidTestImplementation", libs.findLibrary("androidx.ui.test.manifest").get())
                    add("androidTestImplementation", libs.findLibrary("androidx.ui.test.junit4").get())
                }
            }

            tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions {
                    freeCompilerArgs = freeCompilerArgs
                    jvmTarget = JavaVersion.VERSION_11.toString()
                }
            }
        }
    }

}