plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(Dependencies.Modules.shared))
    implementation(project(Dependencies.Modules.injection))
    implementation(Dependencies.Android.koin)
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha02")
}

android {
    compileSdk = Versions.AndroidVersions.compileSdk
    defaultConfig {
        applicationId = "com.ruben.footiescore.android"
        minSdk = Versions.AndroidVersions.minSdk
        targetSdk = Versions.AndroidVersions.targetSdk
        versionCode = Versions.AndroidVersions.versionCode
        versionName = Versions.AndroidVersions.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}