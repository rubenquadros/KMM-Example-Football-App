/**
 * Created by Ruben Quadros on 15/10/21
 **/
object Versions {
    //gradle
    object GradlePluginVersions {
        const val gradleVersion = "7.0.3"
        const val buildKonfigVersion = "0.10.2"
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
    }


    //shared
    object Shared {
        const val kotlinVersion = "1.5.31"
        const val coroutinesVersion = "1.5.2"
        const val koinVersion = "3.1.2"
        const val ktorVersion = "1.6.4"
        const val kotlinSerializationVersion = "1.2.2"
    }

    //android
    object Android {
        const val junitVersion = "4.13.2"
        const val composeVersion = "1.0.4"
        const val materialDesignVersion = "1.4.0"
        const val lifecycleVersion = "2.4.0-rc01"
        const val composeNavigationVersion = "2.4.0-alpha10"
        const val composeActivityVersion = "1.3.1"
        const val composeConstraintLayout = "1.0.0-rc01"
        const val composeCoilVersion = "1.3.2"
        const val mviOrbitVersion = "4.2.0"
    }
}

object Dependencies {
    object GradlePlugin {
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Shared.kotlinVersion}"
        const val gradle = "com.android.tools.build:gradle:${Versions.GradlePluginVersions.gradleVersion}"
        const val buildKonfig = "com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:${Versions.GradlePluginVersions.buildKonfigVersion}"
    }

    object Android {
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        const val junit = "junit:junit:${Versions.Android.junitVersion}"
        const val ktor = "io.ktor:ktor-client-android:${Versions.Shared.ktorVersion}"
        const val koin = "io.insert-koin:koin-android:${Versions.Shared.koinVersion}"
        const val koinTest = "io.insert-koin:koin-test:${Versions.Shared.koinVersion}"
        const val materialDesign = "com.google.android.material:material:${Versions.Android.materialDesignVersion}"

        object Compose {
            const val ui = "androidx.compose.ui:ui:${Versions.Android.composeVersion}"
            const val material = "androidx.compose.material:material:${Versions.Android.composeVersion}"
            const val tooling = "androidx.compose.ui:ui-tooling-preview:${Versions.Android.composeVersion}"
            const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.Android.composeVersion}"
            const val uiTest = "androidx.compose.ui:ui-test-manifest:${Versions.Android.composeVersion}"
            const val navigation = "androidx.navigation:navigation-compose:${Versions.Android.composeNavigationVersion}"
            const val activity = "androidx.activity:activity-compose:${Versions.Android.composeActivityVersion}"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.Android.composeConstraintLayout}"
            const val coil = "io.coil-kt:coil-compose:${Versions.Android.composeCoilVersion}"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Android.lifecycleVersion}"
        }

        object OrbitMvi {
            const val mviCore = "org.orbit-mvi:orbit-core:${Versions.Android.mviOrbitVersion}"
            const val mviViewmodel = "org.orbit-mvi:orbit-viewmodel:${Versions.Android.mviOrbitVersion}"
        }
    }

    object Ios {
        const val ktor = "io.ktor:ktor-client-ios:${Versions.Shared.ktorVersion}"
    }

    object Test {
        const val kotlinTestCommon = "test-common"
        const val kotlinTestAnnotations = "test-annotations-common"
        const val androidJunit = "test-junit"
    }

    object Shared {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Shared.coroutinesVersion}"
        const val koin = "io.insert-koin:koin-core:${Versions.Shared.koinVersion}"
        const val ktor = "io.ktor:ktor-client-core:${Versions.Shared.ktorVersion}"
        const val ktorEngine = "io.ktor:ktor-client-cio:${Versions.Shared.ktorVersion}"
        const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.Shared.ktorVersion}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.Shared.ktorVersion}"
        const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.Shared.kotlinSerializationVersion}"
    }

    object Modules {
        const val data = ":data"
        const val injection = ":injection"
        const val remote = ":remote"
        const val shared = ":shared"
    }

}