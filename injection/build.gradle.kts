import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Dependency injection code shared between android and ios app"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.IosVersions.deploymentTarget
        framework {
            baseName = "injection"
        }
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Dependencies.Modules.shared))
                api(project(Dependencies.Modules.remote))
                api(project(Dependencies.Modules.data))
                implementation(Dependencies.Shared.koin)
            }
        }
        val androidMain by getting
        val iosMain by getting
    }
}

android {
    compileSdk = Versions.AndroidVersions.compileSdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.AndroidVersions.minSdk
        targetSdk = Versions.AndroidVersions.targetSdk
    }
}