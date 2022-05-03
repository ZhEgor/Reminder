object Dependencies {
    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.4.1"
        const val material = "com.google.android.material:material:1.5.0"
        const val activityCompose = "androidx.activity:activity-compose:1.4.0"
    }

    object Compose {

        const val version = "1.1.1"
        const val ui = "androidx.compose.ui:ui:$version"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$version"
        const val material = "androidx.compose.material:material:$version"
        const val toolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val accompanistPager = "com.google.accompanist:accompanist-pager:0.12.0"
    }

    object Coroutines {

        const val version = "1.5.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object Kotlin {
        const val version = "1.6.10"
    }

    object Koin {

        const val version = "3.1.5"
        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val androidExt = "io.insert-koin:koin-android-ext:$version"
        const val compose = "io.insert-koin:koin-androidx-compose:$version"
    }

    object Room {

        const val version = "2.4.2"
        const val runtime = "androidx.room:room-runtime:$version"
        const val ktx = "androidx.room:room-ktx:$version"
        const val compiler = "androidx.room:room-compiler:$version"
    }

    object Navigation {

        const val navigationCompose = "androidx.navigation:navigation-compose:2.5.0-alpha03"
    }

    object Lifecycle {

        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.1"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-alpha06"
    }

    object Test {

        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Plugins {

        const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.4"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    }
}
