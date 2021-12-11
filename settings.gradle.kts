pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "FootieScore"
include(":androidApp")
include(":injection")
include(":cache")
include(":core")
include(":shared")
