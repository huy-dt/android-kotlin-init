plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.xxx.app.feature_xxx"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Có ui nhớ
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        // Phù hợp Kotlin 1.9.22
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(":core"))

    // ⭐ BẮT BUỘC: Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)

    implementation(libs.bundles.core)
    implementation(libs.bundles.compose.core)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // vì bạn dùng Material3
    implementation(libs.bundles.compose.material3)

}

