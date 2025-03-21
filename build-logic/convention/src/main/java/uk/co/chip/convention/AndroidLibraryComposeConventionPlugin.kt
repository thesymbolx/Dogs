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
                    add("debugImplementation", libs.findLibrary("ui.tooling").get())
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