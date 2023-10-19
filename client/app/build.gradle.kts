@file:Suppress("UnstableApiUsage", "DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

fun getInt(param: Provider<String>) = Integer.parseInt(param.get())

android {
    namespace = "ps.erictoader.dreamcatch"
    compileSdk = getInt(libs.versions.compileSdk)

    defaultConfig {
        applicationId = "ps.erictoader.dreamcatch"
        minSdk = getInt(libs.versions.minSdk)
        targetSdk = getInt(libs.versions.targetSdk)
        versionCode = getInt(libs.versions.versionCode)
        versionName = libs.versions.versionName.get()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":ui"))

    // Android
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.datastore)

    // Koin Di
    implementation(libs.koin.android)
    
    // Debugging
    implementation(libs.soLoader)
    implementation(libs.flipper.base)
}
