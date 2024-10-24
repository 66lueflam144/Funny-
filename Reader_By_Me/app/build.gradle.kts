plugins {
    alias(libs.plugins.android.application)
}


android {
    namespace = "com.example.reader_by_me"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.reader_by_me"
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.robolectric)
    implementation(libs.okhttp3)
    implementation(libs.glide) // Glide依赖
    annotationProcessor(libs.glide.compiler) // Glide编译器依赖
    testImplementation(libs.junit)
    testImplementation("org.mockito:mockito-core:5.11.0")
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
