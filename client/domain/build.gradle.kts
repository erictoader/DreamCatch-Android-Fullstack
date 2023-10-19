@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

fun getInt(param: Provider<String>) = Integer.parseInt(param.get())

android {
    namespace = "ps.erictoader.domain"
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
}

dependencies {
    // Core Android
    implementation(libs.bundles.androidx)

    // Koin Di
    implementation(libs.koin.android)

    // Testing
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlin.junit.test)
    testImplementation(libs.androidx.arch.core)
}
