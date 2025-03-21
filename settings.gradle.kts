gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))


pluginManagement {
    includeBuild("build-logic") // Include the build-logic

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Dog"
include(":app")
include(":core:network")
include(":core:test")
include(":data:breeds")
include(":feature:dog_breeds")
include(":domain:breeds")
include(":core:design_system")
include(":core:ui")
include(":feature:dog_images")