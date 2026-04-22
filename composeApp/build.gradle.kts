import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinSerialization)

    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)

    alias(libs.plugins.buildkonfig)

    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    // Platform targets
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            // Required when using NativeSQLiteDriver
            linkerOpts.add("-lsqlite3")
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            // 🎨 Android Compose Preview (Default library)
            implementation(compose.preview)                     // Compose preview for Android Studio

            // 🎨 Activity Compose (Default library)
            implementation(libs.androidx.activity.compose)     // Activity with Compose support

            // 🖼️ Splash Screen
            implementation(libs.androidx.core.splashscreen)    // Splash Screen

            // 💉 Koin Android - Android-specific DI features
            implementation(libs.koin.android)                  // Koin Android integration
            implementation(libs.koin.androidx.compose)         // Koin AndroidX Compose integration

            // 📡 Ktor Android - OkHttp engine for Android
            implementation(libs.ktor.client.okhttp)            // OkHttp engine (best for Android)

            // ⚡ Coroutines Android - Android coroutines support
            implementation(libs.kotlinx.coroutines.android)    // Android coroutines dispatcher
        }
        commonMain.dependencies {
            // 🎨 Compose Multiplatform - UI Framework (Default libraries)
            implementation(compose.runtime)                    // Compose runtime for state management
            implementation(compose.foundation)                 // Foundation layouts and components
            implementation(compose.ui)                        // Core UI components
            implementation(compose.components.resources)       // Resource handling
            implementation(compose.components.uiToolingPreview) // Preview support

            // 🎨 Additional Material Design 3 - Enhanced design system
            implementation(compose.material3)                  // Material3 design system
            implementation(compose.material3AdaptiveNavigationSuite) // adaptive design
            implementation(compose.materialIconsExtended) // Extended Material icons
            implementation(libs.material3.adaptive)

            // 🧭 Navigation - Type-safe screen navigation
            implementation(libs.androidx.navigation.compose)   // Compose navigation

            // 🔄 Lifecycle - ViewModel and state management (Default + Enhanced)
            implementation(libs.androidx.lifecycle.viewmodel)         // ViewModel for business logic
            implementation(libs.androidx.lifecycle.viewmodel.compose) // ViewModel integration with Compose
            implementation(libs.androidx.lifecycle.runtime.compose)   // Lifecycle-aware Compose

            // ⚡ Coroutines - Asynchronous programming
            implementation(libs.kotlinx.coroutines.core)       // Kotlin coroutines core

            // 📡 Networking - HTTP client and serialization
            implementation(libs.ktor.client.core)              // Ktor HTTP client core
            implementation(libs.ktor.client.content.negotiation) // Content negotiation for JSON
            implementation(libs.ktor.client.serialization)     // Serialization support
            implementation(libs.ktor.serialization.kotlinx.json) // JSON serialization
            implementation(libs.ktor.client.logging)           // HTTP request/response logging
            implementation(libs.ktor.client.auth)              // Authentication support

            // 💾 Local Storage - DataStore for preferences
            implementation(libs.datastore.preferences)         // DataStore for user preferences
            implementation(libs.datastore.core)               // DataStore core functionality

            // 🗄️ Database - Room for local data storage
            implementation(libs.androidx.room.runtime)         // Room database runtime
            implementation(libs.androidx.sqlite.bundled)              // SQLite database engine

            // 💉 Dependency Injection - Koin for DI
            api(libs.koin.core)                     // Koin dependency injection core
            implementation(libs.koin.compose)                  // Koin integration with Compose
            implementation(libs.koin.composeVM)                // Koin ViewModel integration

            // 🖼️ Image Loading - Coil3 for loading images
            implementation(libs.coil.compose)                  // Coil3 Compose integration
            implementation(libs.coil.compose.core)            // Coil3 core functionality
            implementation(libs.coil.network.ktor3)           // Coil3 network loading with Ktor
            implementation(libs.coil.mp)                      // Coil3 multiplatform support

            // 📅 Date & Time - Kotlinx datetime
            implementation(libs.kotlinx.datetime)              // Multiplatform date/time handling

            // 📦 Serialization - JSON parsing
            implementation(libs.kotlinx.serialization.json)    // JSON serialization

            // 📝 Logging - Kermit for multiplatform logging
            implementation(libs.kermit)                        // TouchLab Kermit logger
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            // 🖥️ Compose Desktop (Default library)
            implementation(compose.desktop.currentOs)          // Platform-specific desktop support

            // ⚡ Coroutines Swing (Default library)
            implementation(libs.kotlinx.coroutines.swing)      // Swing dispatcher for desktop UI

            // 📡 Ktor Desktop - OkHttp engine for Desktop
            implementation(libs.ktor.client.okhttp)               // OkHttp engine


            implementation(libs.jsystemthemedetector)
        }

        iosMain.dependencies {
            // 🍎 iOS-specific dependencies

            // 📡 Ktor iOS - Darwin engine for iOS
            implementation(libs.ktor.client.darwin)            // Darwin engine (native iOS networking)
        }
    }
}

// 🤖 Android configuration
android {
    namespace = "com.example.cmp_mvi_template"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.cmp_mvi_template"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    add("kspAndroid", libs.androidx.room.compiler)
    add("kspDesktop", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.example.cmp_mvi_template.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.cmp_mvi_template"
            packageVersion = "1.0.0"
        }
    }
}
// 🗄️ Room configuration
room {
    schemaDirectory("$projectDir/schemas") // Database schema location
}

// BuildConfig
buildkonfig {
    packageName = "com.example.cmp_mvi_template"

    val localProperties =
        Properties().apply {
            val propsFile = rootProject.file("local.properties")
            if (propsFile.exists()) {
                load(propsFile.inputStream())
            }
        }

    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "API_KEY",
            localProperties["API_KEY"]?.toString(),
        )
        buildConfigField(
            FieldSpec.Type.BOOLEAN,
            "Is_Debug_Build",
            localProperties["Is_Debug_Build"]?.toString(),
        )
    }
}