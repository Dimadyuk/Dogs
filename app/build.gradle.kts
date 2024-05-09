plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.navigation.safeargs.kotlin)
    id("kotlin-parcelize")
}

android {
    namespace = "com.dimadyuk.dogs"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dimadyuk.dogs"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}


dependencies {
    implementation(libs.lifecycleExtensions)

    implementation(libs.room.runtime)
    implementation(libs.androidx.swiperefreshlayout)
    kapt(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.coroutines)

    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)

    implementation(libs.retrofit)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.adapter.rxjava2)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    implementation(libs.glide)
    implementation(libs.palette)
    implementation(libs.preference)

    implementation(libs.design)

    implementation(libs.firebase.core)
    implementation(libs.multidex)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}