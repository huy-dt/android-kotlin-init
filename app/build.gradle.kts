import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    // Khi dùng Hilt thì mở 2 dòng này
    // alias(libs.plugins.hilt)
    // alias(libs.plugins.kotlin.kapt)
}

/**
 * =====================================================
 * READ local.properties (KHÔNG COMMIT GIT)
 * =====================================================
 */
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        file.inputStream().use { load(it) }
    }
}

fun prop(key: String): String? =
    localProperties.getProperty(key)

/**
 * =====================================================
 * ANDROID CONFIG
 * =====================================================
 */
android {

    namespace = "com.xxx.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.xxx.app"
        minSdk = 24
        targetSdk = 35

        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    /**
     * =====================================================
     * SIGNING CONFIG (RELEASE)
     * LẤY DATA TỪ local.properties
     * =====================================================
     */
    signingConfigs {
        create("release") {

            val storeFilePath = prop("RELEASE_STORE_FILE")

            // Chỉ config khi có local.properties
            if (storeFilePath != null) {
                storeFile = file(storeFilePath)
                storePassword = prop("RELEASE_STORE_PASSWORD")
                keyAlias = prop("RELEASE_KEY_ALIAS")
                keyPassword = prop("RELEASE_KEY_PASSWORD")
            }
        }
    }

    /**
     * =====================================================
     * BUILD TYPES
     * =====================================================
     */
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true

            signingConfigs {

                val storeFilePath = prop("RELEASE_STORE_FILE")

                if (storeFilePath != null) {
                    create("release") {
                        storeFile = file(storeFilePath)
                        storePassword = prop("RELEASE_STORE_PASSWORD")
                        keyAlias = prop("RELEASE_KEY_ALIAS")
                        keyPassword = prop("RELEASE_KEY_PASSWORD")
                    }
                }
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    /**
     * =====================================================
     * COMPOSE
     * =====================================================
     */
    buildFeatures {
        compose = true
    }

    composeOptions {
        // Phù hợp Kotlin 1.9.22
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    /**
     * =====================================================
     * JAVA / KOTLIN
     * =====================================================
     */
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    // Khi dùng Hilt
    // kapt {
    //     correctErrorTypes = true
    // }
}

/**
 * =====================================================
 * DEPENDENCIES
 * =====================================================
 */
dependencies {

    implementation(project(":core"))

    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.bundles.compose.core)
    implementation(libs.bundles.compose.material3)
    implementation(libs.bundles.compose.lifecycle)


    implementation(libs.bundles.compose.preview)
    debugImplementation(libs.bundles.compose.debug)

    // ===============================
    // HILT (KHI CẦN)
    // ===============================
    // implementation(libs.hilt.android)
    // kapt(libs.hilt.compiler)
    // implementation(libs.androidx.hilt.navigation.compose)
}