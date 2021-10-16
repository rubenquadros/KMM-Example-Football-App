plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    //modules
    implementation(project(Dependencies.Modules.shared))
    implementation(project(Dependencies.Modules.injection))

    implementation(Dependencies.Android.koin)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.splashScreenApi)

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
    implementation(Dependencies.Android.Compose.viewmodel)
    debugImplementation(Dependencies.Android.Compose.uiTooling)
    debugImplementation(Dependencies.Android.Compose.uiTest)
}

android {
    compileSdk = Versions.AndroidVersions.compileSdk
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
            kotlinCompilerExtensionVersion = Versions.Android.composeVersion
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
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}