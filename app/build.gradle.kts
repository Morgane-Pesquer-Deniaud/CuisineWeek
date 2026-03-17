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
    implementation(libs.androidx.cardview)
    implementation(libs.material)

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
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.activity:activity-ktx:1.9.0")
    //Evite la perte des donnée
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0-rc01")
    // Scanner de code-barre
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    // Retrofit - pour appeler des APIs
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutines - pour les appels réseau asynchrones
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    //Style
    implementation("com.google.android.material:material:1.11.0")

}
