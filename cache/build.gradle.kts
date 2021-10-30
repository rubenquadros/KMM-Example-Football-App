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
        summary = "Cache layer code shared between android and ios app"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.IosVersions.deploymentTarget
        framework {
            baseName = "cache"
        }
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Shared.koin)
                implementation(Dependencies.Shared.coroutines)
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
                implementation(Dependencies.Android.dataStore)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Test.kotlinTestCommon))
                implementation(Dependencies.Android.junit)
            }
        }
        val iosMain by getting
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