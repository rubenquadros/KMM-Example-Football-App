import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.gradle.GradleReleaseChannel

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.GradlePlugin.kotlin)
        classpath(Dependencies.GradlePlugin.kotlinSerialization)
        classpath(Dependencies.GradlePlugin.gradle)
        classpath(Dependencies.GradlePlugin.buildKonfig)
        classpath(Dependencies.GradlePlugin.google)
        classpath(Dependencies.GradlePlugin.sqlDelight)
        classpath(Dependencies.GradlePlugin.crashlytics)
    }
}

plugins {
    id(Dependencies.GradlePlugin.kover) version Versions.PluginVersions.kover
    id(Dependencies.GradlePlugin.dependencyUpdate) version Versions.PluginVersions.dependencyUpdate
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.koverVerify {
    rule {
        name = "Minimal line coverage rate in percents"
        bound {
            minValue = 50
        }
    }
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    checkForGradleUpdate = true
    gradleReleaseChannel = GradleReleaseChannel.RELEASE_CANDIDATE.id
    revision = "integration" // See available revisions
    outputFormatter = "plain" // xml and json available too
    outputDir = "build/dependencyUpdates"
    reportfileName = "dependency_update_report"
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}