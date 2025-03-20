pluginManagement {
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

rootProject.name = "Dogs"
include(":app")
include(":core:network")
include(":core:common")
include(":data:breeds")
include(":feature:dog_breeds")
include(":domain:breeds")
include(":core:design_system")
include(":core:ui")
include(":feature:dog_images")
