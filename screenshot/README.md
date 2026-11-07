# Screenshot Module 📸

This module is an internal developer tool for generating realistic, device-framed screenshots of the **Store App — CMP MVI Template** across multiple platforms (Android, iOS, macOS, and Web) in both **Dark Mode** and **Light Mode**.

---

## ⚠️ Important Note: Keep Commented in `settings.gradle.kts`

By default, the screenshot-related modules **can remain commented out** in [`settings.gradle.kts`](../settings.gradle.kts) when doing normal production app builds:

```kotlin
// Screenshot and Preview functionality only for developer internal screenshot capture
//include(":screenshot")
```

> [!TIP]
> Keeping it commented out ensures everyday Android and Desktop app builds remain lightweight and fast without loading test snapshot dependencies.

---

## 🔍 How It Works

1. **Portable Pre-built JVM JAR (`screenshot/libs/cmpdevicepreview-jvm.jar`)**:
   - The screenshot pipeline uses [CMPDevicePreview](https://github.com/Apadmi-Engineering/CMPDevicePreview) to render realistic device hardware bezels (Google Pixel 9, iPhone 16 Pro Dynamic Island, macOS desktop windows, and Chrome browser frames).
   - This module uses the enhanced pre-compiled JAR (`screenshot/libs/cmpdevicepreview-jvm.jar`) built from [Coding-Meet/CMPDevicePreview](https://github.com/Coding-Meet/CMPDevicePreview), which includes:
     - **Native Dialog Interception**: Direct `ComposeSceneLayer` capture for dialogs (`ThemeSelectionDialog`, `SortBottomSheet`, and `CheckoutSuccessDialog`).
     - **Solid Opacity Scrims**: Fast-forwards Material 3 dialog appearance animations to 100% solid opacity.
     - **Chrome Web Frame**: Realistic browser header with window controls, back/forward/refresh buttons, URL pill, and dark/light styling.

2. **Unified Adaptive Layout with `NavigationSuiteScaffold`**:
   - Compact screens (`< 840.dp`, Android & iOS): Uses `NavigationSuiteType.NavigationBar` for bottom navigation tabs (Products, Cart with badge, Favorites, Profile, Settings) and `NavigationSuiteType.None` for detail/onboarding screens.
   - Expanded screens (`>= 840.dp`, macOS & Web): Uses permanent `NavigationDrawer` navigation sheet on the left and full responsive screen views on the right.

---

## 🛠️ How to Export Screenshots & Run Tests

### Step 1: Enable the Screenshot Module
In [`settings.gradle.kts`](../settings.gradle.kts), ensure `include(":screenshot")` is uncommented:
```kotlin
include(":screenshot")
```

### Step 2: Export All Screenshots (All Platforms, Dark & Light)
Run the automated export task from the terminal:
```bash
./gradlew :screenshot:exportDeviceScreenshots
```
Screenshots are generated into:
```
image/
├── android/
│   ├── dark/   (12 screens: Catalog, Shimmer, Search, Sort Sheet, Details, Cart, Checkout Dialog, Favorites, Profile, Settings, Theme Dialog, Onboarding)
│   └── light/  (12 screens)
├── ios/
│   ├── dark/   (12 screens: iPhone 16 Pro Dynamic Island)
│   └── light/  (12 screens)
├── mac/
│   ├── dark/   (7 desktop window screens with navigation drawer)
│   └── light/  (7 desktop window screens)
└── web/
    ├── dark/   (7 Chrome browser window screens)
    └── light/  (7 Chrome browser window screens)
```
