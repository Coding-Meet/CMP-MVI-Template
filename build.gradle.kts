plugins {
    // 🚫 Apply plugins without configuring them (they will be configured in subprojects)

    // 🤖 Android plugins - For Android app and library modules
    alias(libs.plugins.androidApplication) apply false   // Android application plugin
    alias(libs.plugins.androidLibrary) apply false       // Android library plugin
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false // KMP library plugin

    // 🔧 Kotlin plugins - For multiplatform and serialization
    alias(libs.plugins.kotlinMultiplatform) apply false  // Kotlin Multiplatform plugin
    alias(libs.plugins.kotlinSerialization) apply false  // Kotlinx Serialization plugin

    // 🎨 Compose plugins - For UI framework
    alias(libs.plugins.composeMultiplatform) apply false // Compose Multiplatform plugin
    alias(libs.plugins.composeCompiler) apply false      // Compose Compiler plugin
    alias(libs.plugins.composeHotReload) apply false     // Hot reload for development

    // 🗄️ Database plugins - For Room database
    alias(libs.plugins.room) apply false                 // Room database plugin
    alias(libs.plugins.ksp) apply false                  // Kotlin Symbol Processing (for Room)
}