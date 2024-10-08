plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "ru.faimizufarov.headhunter"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.faimizufarov.headhunter"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(path = ":features:auth"))
    implementation(project(path = ":features:messages"))
    implementation(project(path = ":features:responses"))
    implementation(project(path = ":features:profile"))
    implementation(project(path = ":screen:search"))
    implementation(project(path = ":screen:favourites"))
    implementation(project(path = ":core"))
    implementation(project(path = ":data"))
    implementation(project(path = ":domain"))

    //region Dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    //endregion

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}