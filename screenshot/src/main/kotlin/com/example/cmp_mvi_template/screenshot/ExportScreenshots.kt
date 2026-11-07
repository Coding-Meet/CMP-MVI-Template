package com.example.cmp_mvi_template.screenshot

import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File

/**
 * Entry point for exporting framed screenshots across all platforms in both Dark and Light themes:
 * - Android (Pixel 9 Realistic Hardware Frame)
 * - iOS (iPhone 16 Pro Realistic Frame & Dynamic Island)
 * - macOS (Large Desktop Window Two-Pane Layout)
 * - Web (Chrome Browser Two-Pane Layout)
 */
fun main(args: Array<String>) {
    val baseDir = if (args.isNotEmpty()) File(args[0]) else File("image")
    val androidDir = File(baseDir, "android").apply { mkdirs() }
    val iosDir = File(baseDir, "ios").apply { mkdirs() }
    val macDir = File(baseDir, "mac").apply { mkdirs() }
    val webDir = File(baseDir, "web").apply { mkdirs() }

    val androidDarkDir = File(androidDir, "dark").apply { mkdirs() }
    val androidLightDir = File(androidDir, "light").apply { mkdirs() }
    val iosDarkDir = File(iosDir, "dark").apply { mkdirs() }
    val iosLightDir = File(iosDir, "light").apply { mkdirs() }
    val macDarkDir = File(macDir, "dark").apply { mkdirs() }
    val macLightDir = File(macDir, "light").apply { mkdirs() }
    val webDarkDir = File(webDir, "dark").apply { mkdirs() }
    val webLightDir = File(webDir, "light").apply { mkdirs() }

    // Clear previous images to ensure clean numbering
    clearDirectory(androidDarkDir)
    clearDirectory(androidLightDir)
    clearDirectory(iosDarkDir)
    clearDirectory(iosLightDir)
    clearDirectory(macDarkDir)
    clearDirectory(macLightDir)
    clearDirectory(webDarkDir)
    clearDirectory(webLightDir)

    // Clean flat legacy files in platform root folders
    cleanFlatFiles(androidDir)
    cleanFlatFiles(iosDir)
    cleanFlatFiles(macDir)
    cleanFlatFiles(webDir)

    println("=== Exporting Store App CMP MVI Screen States Across All Platforms (Dark & Light) ===")

    // Pre-warm all image URLs so Coil memory and disk caches are hot before screenshot 01 renders
    preWarmAllImages()

    // 1. Android (Google Pixel 9 Realistic Frame)
    println("\n[1/4] Generating Android screen states (Pixel 9)...")
    println("  -> Dark Mode...")
    exportPlatformScreens(TargetPlatform.ANDROID, androidDarkDir, isDark = true)
    println("  -> Light Mode...")
    exportPlatformScreens(TargetPlatform.ANDROID, androidLightDir, isDark = false)

    // 2. iOS (Apple iPhone 16 Pro Realistic Frame)
    println("\n[2/4] Generating iOS screen states (iPhone 16 Pro)...")
    println("  -> Dark Mode...")
    exportPlatformScreens(TargetPlatform.IOS, iosDarkDir, isDark = true)
    println("  -> Light Mode...")
    exportPlatformScreens(TargetPlatform.IOS, iosLightDir, isDark = false)

    // 3. macOS (Desktop Two-Pane Window Frame)
    println("\n[3/4] Generating macOS desktop two-pane screen states (Large Desktop Window)...")
    println("  -> Dark Mode...")
    exportPlatformScreens(TargetPlatform.MACOS, macDarkDir, isDark = true)
    println("  -> Light Mode...")
    exportPlatformScreens(TargetPlatform.MACOS, macLightDir, isDark = false)

    // 4. Web (Chrome Browser Two-Pane Frame)
    println("\n[4/4] Generating Web two-pane screen states (Chrome Browser Frame)...")
    println("  -> Dark Mode...")
    exportPlatformScreens(TargetPlatform.WEB, webDarkDir, isDark = true)
    println("  -> Light Mode...")
    exportPlatformScreens(TargetPlatform.WEB, webLightDir, isDark = false)

    println("\n=== All Screen States Exported Successfully! ===")
}

private fun clearDirectory(dir: File) {
    dir.listFiles()?.filter { it.isFile }?.forEach { it.delete() }
}

private fun cleanFlatFiles(dir: File) {
    dir.listFiles()?.filter { it.isFile && it.name.endsWith(".png") }?.forEach { it.delete() }
}

private fun preWarmAllImages() {
    val urls = allSampleImageUrls
    println("\n[Pre-warming] Pre-caching ${urls.size} sample image URLs into Coil memory cache...")
    val context = PlatformContext.INSTANCE
    val imageLoader = SingletonImageLoader.get(context)

    runBlocking {
        withContext(Dispatchers.IO) {
            urls.map { url ->
                async {
                    try {
                        val request = ImageRequest.Builder(context)
                            .data(url)
                            .build()
                        val result = imageLoader.execute(request)
                        when (result) {
                            is SuccessResult -> println("  ✓ Cached: $url")
                            is ErrorResult -> System.err.println("  ✗ Failed to cache: $url (${result.throwable.message})")
                        }
                    } catch (e: Exception) {
                        System.err.println("  ✗ Error pre-warming: $url (${e.message})")
                    }
                }
            }.awaitAll()
        }
    }
    println("[Pre-warming] ✓ All images pre-cached successfully!\n")
}

