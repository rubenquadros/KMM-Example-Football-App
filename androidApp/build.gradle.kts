import java.util.*
import java.io.*

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    kotlin("android")
}

dependencies {
    //modules
    implementation(project(Dependencies.Modules.core))
    implementation(project(Dependencies.Modules.injection))

    //koin
    implementation(Dependencies.Android.koin)
    implementation(Dependencies.Android.koinCompose)

    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.splashScreenApi)
    implementation(Dependencies.Android.lottie)
    implementation(Dependencies.Android.googleAuth)

    //mvi orbit
    implementation(Dependencies.Android.OrbitMvi.mviCore)
    implementation(Dependencies.Android.OrbitMvi.mviViewmodel)

    //compose
    implementation(Dependencies.Android.Compose.ui)
    implementation(Dependencies.Android.Compose.material)
    implementation(Dependencies.Android.Compose.tooling)
    implementation(Dependencies.Android.Compose.navigation)
    implementation(Dependencies.Android.Compose.activity)
    implementation(Dependencies.Android.Compose.constraintLayout)
    implementation(Dependencies.Android.Compose.coil)
    implementation(Dependencies.Android.Compose.coilSvg)
    implementation(Dependencies.Android.Compose.viewmodel)
    debugImplementation(Dependencies.Android.Compose.uiTooling)
    debugImplementation(Dependencies.Android.Compose.uiTest)

    //accompanist
    implementation(Dependencies.Android.Accompanist.navigationAnimation)

    //firebase
    implementation(Dependencies.Android.Firebase.crashlytics)
    implementation(Dependencies.Android.Firebase.analytics)
}

android {
    compileSdk = Versions.AndroidVersions.compileSdk

    val file = rootProject.file("local.properties")

    defaultConfig {
        applicationId = "com.ruben.footiescore.android"
        minSdk = Versions.AndroidVersions.minSdk
        targetSdk = Versions.AndroidVersions.targetSdk
        versionCode = Versions.AndroidVersions.versionCode
        versionName = Versions.AndroidVersions.versionName

        testInstrumentationRunner = Dependencies.Android.testInstrumentationRunner

        vectorDrawables {
            useSupportLibrary = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = Versions.AndroidVersions.compose
        }

        buildFeatures {
            compose = true
        }

        testOptions {
            unitTests {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
            }
        }

        if (file.exists()) {
            val localProperties = Properties()
            localProperties.load(FileInputStream(file))
            buildConfigField(
                "String",
                "GOOGLE_CLIENT_ID",
                "\"" + localProperties["google.client.id"] + "\""
            )
        } else {
            buildConfigField(
                "String",
                "GOOGLE_CLIENT_ID",
                "\"" + System.getenv("GOOGLE_CLIENT_ID").toString() + "\""
            )
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            signingConfig = signingConfigs.findByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "footiescoredebugkmm"
            keyPassword = "footiescoredebug"
            storeFile = file("$projectDir/keystore/footiescore_debug.jks")
            storePassword = "footiescoredebug"
        }
    }
}