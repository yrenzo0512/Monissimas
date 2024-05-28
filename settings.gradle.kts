pluginManagement {
    repositories {
        google()
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
    pluginManagement {
        repositories {
            gradlePluginPortal()
            google()
            mavenCentral()
        }
    }
}

rootProject.name = "Diseno2"
include(":app")
 