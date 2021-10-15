/**
 * Created by Ruben Quadros on 15/10/21
 **/
object Versions {
    //ios
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
    const val coroutinesVersion = "1.5.2"
    const val koinVersion = "3.1.2"
    const val ktorVersion = "1.6.4"
    const val kotlinSerializationVersion = "1.2.2"

    //android
    const val junitVersion = "4.13.2"
}

object Dependencies {

    object Android {
        const val junit = "junit:junit:${Versions.junitVersion}"
        const val ktor = "io.ktor:ktor-client-android:${Versions.ktorVersion}"
        const val koin = "io.insert-koin:koin-android:${Versions.koinVersion}"
    }

    object Ios {
        const val ktor = "io.ktor:ktor-client-ios:${Versions.ktorVersion}"
    }

    object Test {
        const val kotlinTestCommon = "test-common"
        const val kotlinTestAnnotations = "test-annotations-common"
        const val androidJunit = "test-junit"
    }

    object Shared {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
        const val koin = "io.insert-koin:koin-core:${Versions.koinVersion}"
        const val ktor = "io.ktor:ktor-client-core:${Versions.ktorVersion}"
        const val ktorEngine = "io.ktor:ktor-client-cio:${Versions.ktorVersion}"
        const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktorVersion}"
        const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktorVersion}"
        const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinSerializationVersion}"
    }

    object Modules {
        const val data = ":data"
        const val injection = ":injection"
        const val remote = ":remote"
        const val shared = ":shared"
    }

}