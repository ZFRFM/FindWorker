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

rootProject.name = "HeadHunter"
include(":app")
include(":screen:search")
include(":screen:favourites")
include(":screen:responses")
include(":screen:messages")
include(":screen:profile")
include(":screen:auth")
include(":core")
include(":data")
include(":domain")
include(":network")
