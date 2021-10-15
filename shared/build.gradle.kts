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
        summary = "Domain layer code shared between android and ios app"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.IosVersions.deploymentTarget
        frameworkName = "shared"
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
                implementation("io.insert-koin:koin-core:3.1.2")
                implementation("org.orbit-mvi:orbit-core:4.2.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.insert-koin:koin-test:3.1.2")
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(31)
    }
}