/**
 * Created by Ruben Quadros on 15/10/21
 **/
object Versions {
    //gradle
    internal object GradlePluginVersions {
        const val gradle = "7.0.3"
        const val buildKonfig = "0.10.2"
        const val google = "4.3.10"
        const val crashlytics = "2.8.0"
    }

    //ios targets
    object IosVersions {
        const val deploymentTarget = "14.1"
    }

    //android targets
    object AndroidVersions {
        const val compileSdk = 31
        const val minSdk = 21
        const val targetSdk = 31
        const val versionName = "0.0.1"
        const val versionCode = 1
        const val compose = "1.0.5"
    }

    object PluginVersions {
        const val kover = "0.4.4"
        const val dependencyUpdate = "0.39.0"
    }


    //shared
    internal object Shared {
        const val kotlin = "1.5.31"
        const val coroutines = "1.5.2"
        const val koin = "3.1.4"
        const val ktor = "1.6.3"
        const val kotlinSerialization = "1.3.1"
        const val sqlDelight = "1.5.3"
    }

    //android
    internal object Android {
        const val junit = "4.13.2"
        const val materialDesign = "1.4.0"
        const val lifecycle = "2.4.0"
        const val composeNavigation = "2.4.0-beta02"
        const val composeActivity = "1.4.0"
        const val composeConstraintLayout = "1.0.0-rc02"
        const val composeCoil = "1.4.0"
        const val mviOrbit = "4.3.0"
        const val splashScreenApi = "1.0.0-alpha02"
        const val dataStore = "1.0.0"
        const val accompanist = "0.20.2"
        const val lottie = "4.2.1"
        const val googleAuth = "19.2.0"
        const val crashlytics = "18.2.4"
        const val analytics = "20.0.0"
    }
}

object Dependencies {
    object GradlePlugin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Shared.kotlin}"
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.Shared.kotlin}"
        const val gradle = "com.android.tools.build:gradle:${Versions.GradlePluginVersions.gradle}"
        const val buildKonfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.GradlePluginVersions.buildKonfig}"
        const val google = "com.google.gms:google-services:${Versions.GradlePluginVersions.google}"
        const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.Shared.sqlDelight}"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.GradlePluginVersions.crashlytics}"
        const val kover = "org.jetbrains.kotlinx.kover"
        const val dependencyUpdate = "com.github.ben-manes.versions"
    }

    object Android {
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:${Versions.Android.junit}"
        const val ktor = "io.ktor:ktor-client-android:${Versions.Shared.ktor}"
        const val koin = "io.insert-koin:koin-android:${Versions.Shared.koin}"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.Shared.koin}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.Shared.koin}"
        const val materialDesign = "com.google.android.material:material:${Versions.Android.materialDesign}"
        const val splashScreenApi = "androidx.core:core-splashscreen:${Versions.Android.splashScreenApi}"
        const val dataStore = "androidx.datastore:datastore-preferences:${Versions.Android.dataStore}"
        const val lottie = "com.airbnb.android:lottie-compose:${Versions.Android.lottie}"
        const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.Android.googleAuth}"
        const val sqlDelight = "com.squareup.sqldelight:android-driver:${Versions.Shared.sqlDelight}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.AndroidVersions.compose}"
            const val material = "androidx.compose.material:material:${Versions.AndroidVersions.compose}"
            const val tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.AndroidVersions.compose}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.AndroidVersions.compose}"
            const val uiTest = "androidx.compose.ui:ui-test-manifest:${Versions.AndroidVersions.compose}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.Android.composeNavigation}"
            const val activity = "androidx.activity:activity-compose:${Versions.Android.composeActivity}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.Android.composeConstraintLayout}"
            const val coil = "io.coil-kt:coil-compose:${Versions.Android.composeCoil}"
            const val coilSvg = "io.coil-kt:coil-svg:${Versions.Android.composeCoil}"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Android.lifecycle}"
        }

        object Accompanist {
            const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.Android.accompanist}"
            const val insets = "com.google.accompanist:accompanist-insets:${Versions.Android.accompanist}"
            const val insetsUi = "com.google.accompanist:accompanist-insets-ui:${Versions.Android.accompanist}"

        }

        object OrbitMvi {
            const val mviCore = "org.orbit-mvi:orbit-core:${Versions.Android.mviOrbit}"
            const val mviViewmodel = "org.orbit-mvi:orbit-viewmodel:${Versions.Android.mviOrbit}"
        }

        object Firebase {
            const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx:${Versions.Android.crashlytics}"
            const val analytics = "com.google.firebase:firebase-analytics-ktx:${Versions.Android.analytics}"
        }
    }

    object Ios {
        const val ktor = "io.ktor:ktor-client-ios:${Versions.Shared.ktor}"
        const val sqlDelight = "com.squareup.sqldelight:native-driver:${Versions.Shared.sqlDelight}"
    }

    object Test {
        const val kotlinTestCommon = "test-common"
        const val kotlinTestAnnotations = "test-annotations-common"
        const val androidJunit = "test-junit"
    }

    object Shared {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Shared.coroutines}"
        const val koin = "io.insert-koin:koin-core:${Versions.Shared.koin}"
        const val ktor = "io.ktor:ktor-client-core:${Versions.Shared.ktor}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.Shared.ktor}"
        const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Shared.kotlinSerialization}"
        const val sqlDelight = "com.squareup.sqldelight:runtime:${Versions.Shared.sqlDelight}"
        const val sqlDelightCoroutines = "com.squareup.sqldelight:coroutines-extensions:${Versions.Shared.sqlDelight}"
    }

    object Modules {
        const val core = ":core"
        const val injection = ":injection"
        const val shared = ":shared"
        const val cache = ":cache"
    }

}