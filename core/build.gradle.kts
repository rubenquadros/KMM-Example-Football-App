import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
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
        summary = "Core shared code between android and ios app"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.IosVersions.deploymentTarget
        framework {
            baseName = "core"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Dependencies.Modules.shared))
                implementation(project(Dependencies.Modules.cache))
                implementation(Dependencies.Shared.coroutines)
                implementation(Dependencies.Shared.koin)
                implementation(Dependencies.Shared.ktor)
                implementation(Dependencies.Shared.ktorSerialization)
                implementation(Dependencies.Shared.kotlinSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Test.kotlinTestCommon))
                implementation(kotlin(Dependencies.Test.kotlinTestAnnotations))
                implementation(Dependencies.Android.koinTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Android.ktor)
                implementation(Dependencies.Android.dataStore)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Test.kotlinTestCommon))
                implementation(Dependencies.Android.junit)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Dependencies.Ios.ktor)
            }
        }

        val iosTest by getting
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