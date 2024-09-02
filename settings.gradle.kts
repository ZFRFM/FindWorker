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
include(":screen:profile")
include(":core")
include(":data")
include(":domain")
include(":network")
include(":local")
include(":features:auth")
include(":features:messages")
