plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":injection"))
    implementation(project(":shared"))
    implementation("io.insert-koin:koin-android:3.1.2")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.activity:activity-ktx:1.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha02")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "com.ruben.footiescore.android"
        minSdkVersion(21)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}