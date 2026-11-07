plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

dependencies {
    implementation(project(":shared"))

    // --- CMPDevicePreview Dependency ---
    // Option 1 (Default / Portable): Pre-compiled standalone JVM JAR with custom dialog layer & web frame.
    // Built from: https://github.com/Coding-Meet/CMPDevicePreview
    // Can also be copied and reused in any other Compose project without needing CMPDevicePreview submodule.
    implementation(files("libs/cmpdevicepreview-jvm.jar"))

    // Option 2 (Source / Debugging): Link local source module for active development or troubleshooting.
    // If using this, also uncomment `:cmpdevicepreview` lines in settings.gradle.kts.
    // implementation(project(":cmpdevicepreview"))

    // Official upstream library (reference only; currently has dialog and web frame limitations)
    // implementation("com.apadmi:cmpdevicepreview:0.1.1")

    implementation(compose.desktop.currentOs)
    implementation(libs.compose.foundation)
    implementation(libs.material3)
    implementation(libs.material3.adaptive.navigation.suite)
    implementation(libs.material3.adaptive)
    implementation(libs.material3.adaptive.layout)
    implementation(libs.material3.adaptive.navigation)
    implementation(libs.material3.window.size.class1)
    implementation(libs.material.icons.extended)
    implementation(libs.compose.components.resources)
    implementation(libs.kotlinx.coroutines.swing)

    // Coil Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.mp)
    implementation(libs.coil.network.ktor3)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.compose)

    implementation(libs.compose.uiToolingPreview)

    testImplementation(kotlin("test"))
    @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
    testImplementation(compose.uiTest)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<JavaExec>("exportDeviceScreenshots") {
    group = "documentation"
    description = "Exports realistic framed device screenshots of Store App CMP MVI Template."
    mainClass.set("com.example.cmp_mvi_template.screenshot.ExportScreenshotsKt")
    classpath = sourceSets["main"].runtimeClasspath
    workingDir = rootProject.projectDir
    val outDir = project.findProperty("outputDir")?.toString() ?: "${rootProject.projectDir}/image"
    args = listOf(outDir)
}
