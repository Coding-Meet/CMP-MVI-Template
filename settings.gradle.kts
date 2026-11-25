rootProject.name = "CMP-MVI-Template"

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(url= "https://jitpack.io") {  }
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven(url= "https://jitpack.io") {  }

    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":shared")
include(":androidApp")
include(":desktopApp")
include(":screenshot")


// ==============================================================================================
// External SSD Build Directory Relocation
// ==============================================================================================
// Purpose:
//   Redirects all Gradle build outputs (root project & submodules) to an external SSD ('Meet')
//   to save internal SSD storage space.
//
// How it works:
//   1. Checks if the external SSD path `/Volumes/Meet/AndroidStudioProjectsBuild` exists & is writable.
//   2. If connected: Routes build outputs dynamically to:
//      `/Volumes/Meet/AndroidStudioProjectsBuild/<ProjectName>/<moduleName>`
//      - Root project build -> `<projectBuildBase>/build`
//      - Submodules (e.g. :shared) -> `<projectBuildBase>/shared`
//   3. If disconnected (Fallback): Automatically falls back to the default local `build/` folder
//      without throwing errors or failing the build.
//
// One-time Cleanup:
//   To free up previously accumulated local storage space on your internal SSD, you can
//   safely delete the old local build folders once using the terminal:
//   $ rm -rf build */build
// ==============================================================================================
val externalSsdBase = file("/Volumes/Meet/AndroidStudioProjectsBuild")
if (externalSsdBase.exists() && externalSsdBase.canWrite()) {
    val projectBuildBase = externalSsdBase.resolve(rootProject.name)

    gradle.beforeProject {
        // Formats module path: root project ":" becomes "build", ":shared" becomes "shared", etc.
        val modulePath = if (path == ":") "build" else path.removePrefix(":").replace(":", "/")
        layout.buildDirectory.set(projectBuildBase.resolve(modulePath))
    }
}
