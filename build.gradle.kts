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
        classpath (Dependencies.GradlePlugin.buildKonfig)
        classpath(Dependencies.GradlePlugin.google)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}