plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")   // ✅ déjà présent chez toi, bien
}

android {
    namespace = "com.example.cuisineweek"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.cuisineweek"
        minSdk = 28
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }

    // ❌ Tu avais buildFeatures { compose = true } ← on supprime ça
    // On n'utilise pas Compose, on utilise les Views XML classiques
}

// ✅ UN SEUL bloc dependencies (tu en avais deux, on les fusionne)
dependencies {

    // Bibliothèques de base Android — déjà présentes, on les garde
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // ❌ On supprime tout ce qui concerne Compose
    // implementation(platform(libs.androidx.compose.bom))
    // implementation(libs.androidx.compose.ui)
    // etc...

    // Tests — on les garde
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.appcompat:appcompat:1.7.0")
    // ✅ Room — base de données locale
    // "2.6.1" = la version de Room qu'on utilise
    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    // room-ktx = ajoute le support des coroutines (suspend, etc.)
    implementation("androidx.room:room-ktx:$room_version")
    // ksp = génère le code Room automatiquement à la compilation
    ksp("androidx.room:room-compiler:$room_version")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    // ✅ ViewModel + LiveData pour l'architecture MVVM
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
}
