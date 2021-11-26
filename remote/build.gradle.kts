import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import java.util.*
import java.io.*

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
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
        summary = "Remote layer code shared between android and ios app"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.IosVersions.deploymentTarget
        framework {
            baseName = "remote"
        }
        podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Shared.ktor)
                implementation(Dependencies.Shared.ktorEngine)
                implementation(Dependencies.Shared.ktorLogging)
                implementation(Dependencies.Shared.ktorSerialization)
                implementation(Dependencies.Shared.kotlinSerialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Test.kotlinTestCommon))
                implementation(kotlin(Dependencies.Test.kotlinTestAnnotations))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Android.ktor)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin(Dependencies.Test.androidJunit))
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

    kotlin.sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
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


buildkonfig {
    val localProperties = Properties()
    localProperties.load(FileInputStream(rootProject.file("local.properties")))

    packageName = "com.ruben.remote"

    defaultConfigs {
        buildConfigField(STRING, "API_KEY", "${localProperties["api.key"]}")
        buildConfigField(STRING, "BASE_URL", "https://footiescore.herokuapp.com")
    }
}