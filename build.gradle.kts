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
    id(Dependencies.GradlePlugin.kover) version Versions.GradlePluginVersions.kover
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

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}