pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "FootieScore"
include(":androidApp")
include(":shared")
include(":data")
include(":remote")
include(":injection")
include(":cache")
