import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("com.codingfeline.buildkonfig")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()

    cocoapods {
        summary = "Shared module for common code in all features"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = Versions.IosVersions.deploymentTarget
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
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
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting

        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
        }
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
    val file = rootProject.file("local.properties")

    packageName = "com.ruben.footiescore.core"

    defaultConfigs {
        if (file.exists()) {
            val localProperties = Properties()
            localProperties.load(FileInputStream(file))
            buildConfigField(STRING, "API_KEY", "${localProperties["api.key"]}")
        } else {
            buildConfigField(STRING, "API_KEY", System.getenv("API_KEY"))
        }
        buildConfigField(STRING, "BASE_URL", "footiescore.herokuapp.com")
    }
}