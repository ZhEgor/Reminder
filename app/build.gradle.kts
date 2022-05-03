plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {

    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.packageName
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependencies.Compose.version
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.activityCompose)

    testImplementation(Dependencies.Test.jUnit)
    androidTestImplementation(Dependencies.Test.androidJUnit)
    androidTestImplementation(Dependencies.Test.espresso)

    // Lifecycle
    implementation(Dependencies.Lifecycle.lifecycleKtx)
    implementation(Dependencies.Lifecycle.viewModelCompose)
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.4.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    // Compose dependencies
    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.materialIconsExtended)
    implementation(Dependencies.Compose.material)
    debugImplementation(Dependencies.Compose.tooling)
    debugImplementation(Dependencies.Compose.toolingPreview)

    implementation(Dependencies.Navigation.navigationCompose)

    // Tab Layout
    implementation(Dependencies.Compose.accompanistPager)

    // Koin
    implementation(Dependencies.Koin.core)
    implementation(Dependencies.Koin.android)
//    implementation(Dependencies.Koin.androidExt)
    implementation(Dependencies.Koin.compose)

    // Coroutines
    implementation(Dependencies.Coroutines.core)

    // Room
    implementation(Dependencies.Room.runtime)
    implementation(Dependencies.Room.ktx)
    kapt(Dependencies.Room.compiler)

    // Timber
    implementation("com.jakewharton.timber:timber:4.7.1")

}
