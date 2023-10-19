@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

fun getInt(param: Provider<String>) = Integer.parseInt(param.get())

android {
    namespace = "ps.erictoader.ui"
    compileSdk = getInt(libs.versions.compileSdk)

    defaultConfig {
        minSdk = getInt(libs.versions.minSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
    }
}

dependencies {
    implementation(project(":domain"))

    // Core Android
    implementation(libs.bundles.androidx)

    // Jetpack Compose
    implementation(libs.bundles.compose)
    implementation(libs.compose.ratingbar)
    implementation(libs.compose.icons)

    // Charts
    implementation(libs.bundles.composeCharts)

    // Lottie animation
    implementation (libs.lottie)

    // Koin Di
    implementation(libs.bundles.koin)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.junit.test)
    testImplementation(libs.androidx.arch.core)

    implementation("com.maxkeppeler.sheets-compose-dialogs:core:1.1.1")
    implementation("com.maxkeppeler.sheets-compose-dialogs:calendar:1.1.1")
}
