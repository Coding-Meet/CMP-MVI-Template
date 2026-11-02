# 🛍️ Store App - MVI Compose Multiplatform Template ✨

A beautiful, modern E-Commerce application built with Compose Multiplatform featuring MVI architecture,
type-safe navigation, and dynamic theming. Explore products, manage favorites/wishlist, add items to cart,
and enjoy a seamless experience across Android, Desktop, and iOS platforms powered by DummyJSON API.

[![Store App Compose Multiplatform Preview](screenshot/pokemon_cmp_mvi_template.png)]()

## ✨ Features

### 🎯 Core Features

- 📱 **Multiplatform**: Android, Desktop, and iOS support with shared business logic and UI
- 🏗️ **MVI Architecture**: Clean, unidirectional data flow (Intent/Event -> ViewModel -> State & Effect)
- 🧭 **Type-Safe Navigation**: Kotlin Serialization-based routing with adaptive navigation (Bottom Bar & Navigation Rail)
- 🎨 **Material 3 Design**: Modern UI with dynamic theming and Material You integration
- 🌓 **Theme Management**: Dark / Light / System default modes + Android Dynamic Colors persisted via DataStore
- 💾 **Offline Support**: Room Multiplatform database with SQLite for persistent favorites and shopping cart
- 🔄 **Reactive UI**: StateFlow and SharedFlow for real-time, responsive UI updates
- 👁️ **MultiPreview Support**: Comprehensive `@ComponentPreviews`, `@AllAdaptivePreviews`, and `@PhonePreview` annotations with custom `MyThemeWrapper`

### 🛍️ Product & E-Commerce Features

- 🔍 **Product Catalog**: Browse products with infinite scroll pagination and pull-to-refresh
- 🔎 **Instant Search & Filters**: 350ms debounced live search, horizontal category pill filter, and dynamic sorting by Price (Low/High) & Rating
- 🛒 **Shopping Cart System**: Room DB persisted cart with real-time bottom nav badge, quantity controls, discount calculations, item deletion, and checkout simulation
- 👤 **User Authentication & Profile**: JWT token persistence via DataStore, 1-click demo login (`emilys`), detailed user profile view, and logout support
- ❤️ **Favorites / Wishlist**: Add/remove products from favorites with swipe-to-delete gesture
- 📊 **Rich Detailed View**: Image carousel, price, discount tags, ratings, stock status, specs, customer reviews, and direct "Add to Cart" action
- ✨ **Shimmer Loading**: Polished shimmer placeholder animations for smooth loading states

### 🎭 UI/UX Features

- 🌊 **Smooth Animations**: Animated transitions, bottom sheet modals, and micro-interactions
- 📱 **Adaptive UI**: Responsive layouts scaling seamlessly from phones to tablets and desktop screens
- 👆 **Swipe Actions**: Swipe-to-delete favorites with background reveal
- 🌈 **Dynamic Colors**: Android 12+ Material You dynamic color extraction
- ⚡ **Performance**: Optimized with Coil3 image caching, Room query flows, and lazy composables

---

## Getting Started

### Installation 🛠️

1. Clone this repository:
   ```bash
   git clone https://github.com/Coding-Meet/CMP-MVI-Template.git
   ```
2. Open in the latest version of Android Studio or IntelliJ IDEA.
3. Build configuration with BuildConfig (via BuildKonfig):
   Create or update your `local.properties` file in the project root:
   ```properties
   Is_Debug_Build=true
   ```
   *(Note: The app is powered by the public [DummyJSON API](https://dummyjson.com/) — no external API key is required!)*

### Run the app on your device or emulator:

- **Android**: Run the `androidApp` module by selecting the `app` run configuration in Android Studio.
- **iOS**: Run the `iosApp` scheme in Xcode or via Android Studio's Kotlin Multiplatform plugin.
- **Desktop**: Run the Gradle task:
  ```bash
  ./gradlew :desktopApp:run
  ```

---

## Screenshot 💻

### Android

<table>
   <tr>
    <td><img src="screenshot/android/1_android.png" alt="android"></td>
    <td><img src="screenshot/android/2_android.png" alt="android"></td>
    <td><img src="screenshot/android/3_android.png" alt="android"></td>
    <td><img src="screenshot/android/4_android.png" alt="android"></td>
   </tr>
   <tr>
    <td><img src="screenshot/android/5_android.png" alt="android"></td>
    <td><img src="screenshot/android/6_android.png" alt="android"></td>
    <td><img src="screenshot/android/7_android.png" alt="android"></td>
    <td><img src="screenshot/android/8_android.png" alt="android"></td>
   </tr>
   <tr>
    <td><img src="screenshot/android/9_android.png" alt="android"></td>
    <td><img src="screenshot/android/10_android.png" alt="android"></td>
    <td><img src="screenshot/android/11_android.png" alt="android"></td>
    <td><img src="screenshot/android/12_android.png" alt="android"></td>
    </tr>
   <tr>
    <td><img src="screenshot/android/13_android.png" alt="android"></td>
    <td><img src="screenshot/android/14_android.png" alt="android"></td>
    <td><img src="screenshot/android/15_android.png" alt="android"></td>
   </tr>
</table>

### iOS

<table>
   <tr>
        <td><img src="screenshot/ios/1_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/2_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/3_ios.png" alt="ios"></td>
   </tr>
   <tr>
        <td><img src="screenshot/ios/4_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/5_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/6_ios.png" alt="ios"></td>
   </tr>
    <tr>
        <td><img src="screenshot/ios/7_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/8_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/9_ios.png" alt="ios"></td>
    </tr>
    <tr>
        <td><img src="screenshot/ios/10_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/11_ios.png" alt="ios"></td>
        <td><img src="screenshot/ios/12_ios.png" alt="ios"></td>
    </tr>
</table>

### Desktop

<table>
   <tr>
        <td><img src="screenshot/desktop/1_desktop.png" alt="desktop"></td>
        <td><img src="screenshot/desktop/2_desktop.png" alt="desktop"></td>
   </tr>
    <tr>
        <td><img src="screenshot/desktop/3_desktop.png" alt="desktop"></td>
        <td><img src="screenshot/desktop/4_desktop.png" alt="desktop"></td>
    </tr>
    <tr>
        <td><img src="screenshot/desktop/5_desktop.png" alt="desktop"></td>
        <td><img src="screenshot/desktop/6_desktop.png" alt="desktop"></td>
    </tr>
    <tr>
        <td><img src="screenshot/desktop/7_desktop.png" alt="desktop"></td>
        <td><img src="screenshot/desktop/8_desktop.png" alt="desktop"></td>
    </tr>
    <tr>
        <td><img src="screenshot/desktop/9_desktop.png" alt="desktop"></td>    
        <td><img src="screenshot/desktop/10_desktop.png" alt="desktop"></td>
    </tr>
    <tr>
        <td><img src="screenshot/desktop/11_desktop.png" alt="desktop"></td>
        <td><img src="screenshot/desktop/12_desktop.png" alt="desktop"></td>    
    </tr>
</table>

---

## 🏗️ Architecture

### Clean Architecture + MVI Pattern

```
📱 Presentation Layer (UI)
├── 🎭 Compose Screens (Declarative UI)
├── 🧠 ViewModels (MVI State & Effect Emitters)
└── 📊 State Management (StateFlow & SharedFlow)

💼 Domain Layer (Business Logic)  
├── 📋 Use Cases (Single-responsibility business rules)
├── 🏪 Repository Interfaces (Domain contracts)
└── 📦 Domain Models (Pure Kotlin entities)

💾 Data Layer (Data Sources)
├── 🌐 Remote (Ktor + DummyJSON API)
├── 💿 Local (Room Database + Preferences DataStore)  
└── 🔄 Repository Implementation (Data orchestration & mapping)
```

### Tech Stack

- 🎯 **UI**: Compose Multiplatform + Material 3 + Adaptive Layouts
- 🏗️ **Architecture**: MVI (Model-View-Intent) + Clean Architecture + Domain Use Cases
- 🧭 **Navigation**: Jetpack Compose Navigation + Type-safe Serialization Routes
- 🌐 **Networking**: Ktor Client (CIO / Darwin / OkHttp) + kotlinx.serialization
- 💾 **Database**: Room Multiplatform + SQLite Bundled Driver
- 🎨 **Theming & Preferences**: androidx.datastore (Preferences DataStore)
- 🔧 **Dependency Injection**: Koin Multiplatform
- 🖼️ **Image Loading**: Coil3 (Async Image Loading & Memory Caching)

---

## 📁 Project Structure

```
CMP-MVI-Template/
├── shared/                                 # ✅ KMP library — shared code for all platforms
│   ├── build.gradle.kts                    # ➕ Gradle config (com.android.kotlin.multiplatform.library)
│   ├── setting.preferences_pb              # 📦 Proto DataStore schema for user settings
│   │
│   └── src/
│       ├── androidMain/                    # 🤖 Android-specific implementations (Toast, SQLite Driver, Engine)
│       ├── iosMain/                        # 🍎 iOS-specific code (Darwin HTTP Engine, MainViewController)
│       ├── desktopMain/                    # 🖥 Desktop-specific implementations (JVM Engine, Toast)
│       │
│       ├── commonMain/                     # 🔁 Shared code between all platforms
│       │   ├── composeResources/           # 🎨 Compose Multiplatform resources (drawables, strings)
│       │   └── kotlin/com/example/cmp_mvi_template/
│       │       ├── app/                    # 🌐 Root app navigation, AppScaffold, AppViewModel
│       │       ├── di/                     # 🧩 Dependency Injection modules (AppModule, CoreModule, initKoin)
│       │       ├── core/                   # 📚 Core reusable utilities, domain errors, networking, local DB
│       │       │   ├── data/               # 🗃️ Local database, DataStore, Ktor client factory
│       │       │   ├── domain/             # 📦 Base models, ResultWrapper, DataError, Paginator
│       │       │   ├── platform/           # 🌍 Expect/actual platform bridges (Database, Toast, DataStore)
│       │       │   └── utility/            # 🔧 WindowSize, AppLogger, UiText, StateController
│       │       ├── ui/                     # 🧱 Reusable UI components, themes, and dialogs
│       │       │   ├── component/          # 🧩 15+ Material3 custom components (Buttons, TopAppBar, etc.)
│       │       │   ├── dialog/             # 💬 ToastPopup, LoadingDialog
│       │       │   ├── layout/             # 📐 ErrorMessageLayout
│       │       │   ├── navigation/         # 🧭 AdaptiveNavigation, BottomNavigationBar, Destinations
│       │       │   └── theme/              # 🎨 Material3 Theme, Dimens, and MultiPreview Annotations
│       │       └── feature/                # 🌟 Feature modules (Clean Architecture + MVI per feature)
│       │           ├── product/            # 🛍️ Catalog, Search, Categories, Sort, Details, Favorites
│       │           ├── cart/               # 🛒 Room-backed Shopping Cart & Checkout
│       │           ├── auth/               # 👤 User Authentication & Profile (DataStore JWT token)
│       │           ├── onboarding/         # 🚀 First-launch Onboarding Pager
│       │           ├── splash/             # ⏳ App Splash Flow & Initial Route Resolution
│       │           ├── setting/            # ⚙️ Theme Selection & Dynamic Colors
│       │           └── sample_example/     # 🧪 Reference MVI feature template
│       │
│       └── commonTest/                     # 🧪 Shared unit tests
│
├── androidApp/                             # 🤖 Android entry point module
│   ├── build.gradle.kts                    # ➕ Gradle config (com.android.application)
│   └── src/main/kotlin/                    # 🚀 MainActivity & MyApplication (Koin setup)
│
├── desktopApp/                             # 🖥 Desktop entry point module
│   ├── build.gradle.kts                    # ➕ Gradle config (Compose Desktop application)
│   └── src/desktopMain/kotlin/             # 💻 Desktop launcher (main.kt)
│
└── iosApp/                                 # 🍎 iOS Xcode project entry point
```

---

## 📁 Detailed Project Structure

```
└── CMP-MVI-Template/
├── androidApp/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       └── kotlin/com/example/cmp_mvi_template/
│           ├── MyApplication.kt
│           └── MainActivity.kt
├── desktopApp/
│   ├── build.gradle.kts
│   └── src/desktopMain/kotlin/com/example/cmp_mvi_template/
│       └── main.kt
├── shared/
│   ├── build.gradle.kts
│   └── src/
│       ├── androidMain/kotlin/com/example/cmp_mvi_template/core/platform/
│       │   ├── theme/PlatformTheme.android.kt
│       │   ├── datastore/createDataStore.android.kt
│       │   ├── toast/AndroidToastManager.kt
│       │   ├── http_engine/GetHttpClientEngine.android.kt
│       │   └── database/getDatabaseBuilder.android.kt
│       ├── commonMain/
│       │   ├── composeResources/
│       │   │   ├── drawable/
│       │   │   └── values/strings.xml
│       │   └── kotlin/com/example/cmp_mvi_template/
│       │       ├── app/
│       │       │   ├── presentation/
│       │       │   │   ├── App.kt
│       │       │   │   ├── AppThemeState.kt
│       │       │   │   └── AppViewModel.kt
│       │       │   └── di/AppModule.kt
│       │       ├── di/
│       │       │   ├── initKoin.kt
│       │       │   └── CoreModule.kt
│       │       ├── core/
│       │       │   ├── data/
│       │       │   │   ├── datastore/
│       │       │   │   │   ├── ThemeMode.kt
│       │       │   │   │   ├── ThemePreferences.kt
│       │       │   │   │   └── onboarding/OnboardingPreferences.kt
│       │       │   │   ├── local/
│       │       │   │   │   ├── AppDatabase.kt
│       │       │   │   │   ├── AppDatabaseConstructor.kt
│       │       │   │   │   └── dao/
│       │       │   │   │       ├── ProductDao.kt
│       │       │   │   │       └── CartDao.kt
│       │       │   │   └── network/
│       │       │   │       ├── HttpClientFactory.kt
│       │       │   │       └── NetworkExtensions.kt
│       │       │   ├── domain/
│       │       │   │   ├── DataError.kt
│       │       │   │   ├── DataErrorToUiTextExtension.kt
│       │       │   │   ├── Error.kt
│       │       │   │   ├── Paginator.kt
│       │       │   │   └── ResultWrapper.kt
│       │       │   ├── platform/
│       │       │   │   ├── database/getDatabaseBuilder.kt
│       │       │   │   ├── datastore/AppSettings.kt
│       │       │   │   ├── datastore/createDataStore.kt
│       │       │   │   ├── http_engine/GetHttpClientEngine.kt
│       │       │   │   ├── theme/PlatformTheme.kt
│       │       │   │   └── toast/
│       │       │   │       ├── ToastDuration.kt
│       │       │   │       ├── ToastManager.kt
│       │       │   │       └── ToastManagerFactory.kt
│       │       │   └── utility/
│       │       │       ├── AppLogger.kt
│       │       │       ├── ComposableExtensions.kt
│       │       │       ├── IconResource.kt
│       │       │       ├── StateController.kt
│       │       │       ├── UiText.kt
│       │       │       └── WindowSize.kt
│       │       ├── ui/
│       │       │   ├── component/
│       │       │   │   ├── badges/Badges.kt
│       │       │   │   ├── bottom_app_bar/BottomAppBar.kt
│       │       │   │   ├── button/Button.kt
│       │       │   │   ├── checkbox/Checkbox.kt
│       │       │   │   ├── divider/Divider.kt
│       │       │   │   ├── fab/Fab.kt
│       │       │   │   ├── icon_button/IconButton.kt
│       │       │   │   ├── navigation_bar/NavigationBar.kt
│       │       │   │   ├── progress_indicator/ProgressIndicators.kt
│       │       │   │   ├── radio_button/RadioButton.kt
│       │       │   │   ├── segmented_button/SegmentedButton.kt
│       │       │   │   ├── slider/Slider.kt
│       │       │   │   ├── switch_custom/Switch.kt
│       │       │   │   ├── text/Text.kt
│       │       │   │   └── top_app_bar/TopAppBar.kt
│       │       │   ├── dialog/
│       │       │   │   ├── LoadingDialog.kt
│       │       │   │   └── ToastPopup.kt
│       │       │   ├── layout/ErrorMessageLayout.kt
│       │       │   ├── navigation/
│       │       │   │   ├── AdaptiveNavigation.kt
│       │       │   │   ├── AppDestination.kt
│       │       │   │   ├── NavigationExtensions.kt
│       │       │   │   └── bottom_navigation_bar/
│       │       │   │       ├── BottomNavigationBar.kt
│       │       │   │       └── NavigationItem.kt
│       │       │   └── theme/
│       │       │       ├── AnnotationPreview.kt
│       │       │       ├── Color.kt
│       │       │       ├── Dimens.kt
│       │       │       ├── Theme.kt
│       │       │       └── Type.kt
│       │       └── feature/
│       │           ├── product/
│       │           │   ├── di/ProductModule.kt
│       │           │   ├── data/
│       │           │   │   ├── mapper/toDomain.kt
│       │           │   │   ├── remote/
│       │           │   │   │   ├── api/
│       │           │   │   │   │   ├── ProductApiService.kt
│       │           │   │   │   │   └── ProductApiServiceImpl.kt
│       │           │   │   │   └── dto/ProductDto.kt
│       │           │   │   └── repository/ProductRepositoryImpl.kt
│       │           │   ├── domain/
│       │           │   │   ├── entity/
│       │           │   │   │   ├── Product.kt
│       │           │   │   │   └── ProductEntity.kt
│       │           │   │   ├── repository/ProductRepository.kt
│       │           │   │   └── usecase/
│       │           │   │       ├── CheckIfProductFavoriteUseCase.kt
│       │           │   │       ├── GetCategoriesUseCase.kt
│       │           │   │       ├── GetFavoriteProductsUseCase.kt
│       │           │   │       ├── GetProductDetailsUseCase.kt
│       │           │   │       ├── GetProductListUseCase.kt
│       │           │   │       └── ToggleFavoriteProductUseCase.kt
│       │           │   └── presentation/
│       │           │       ├── component/
│       │           │       │   ├── ProductCard.kt
│       │           │       │   └── ShimmerEffect.kt
│       │           │       ├── product_list/
│       │           │       │   ├── component/
│       │           │       │   │   ├── CategoryChipsRow.kt
│       │           │       │   │   ├── ProductListContent.kt
│       │           │       │   │   └── SortBottomSheet.kt
│       │           │       │   └── screen/
│       │           │       │       ├── ProductListEffect.kt
│       │           │       │       ├── ProductListEvent.kt
│       │           │       │       ├── ProductListScreen.kt
│       │           │       │       ├── ProductListState.kt
│       │           │       │       └── ProductListViewModel.kt
│       │           │       ├── product_details/
│       │           │       │   ├── component/
│       │           │       │   │   ├── ProductDetailsContent.kt
│       │           │       │   │   ├── ProductImageCarousel.kt
│       │           │       │   │   ├── ProductInfoSection.kt
│       │           │       │   │   ├── ProductReviewsSection.kt
│       │           │       │   │   └── ProductSpecsSection.kt
│       │           │       │   └── screen/
│       │           │       │       ├── ProductDetailsEffect.kt
│       │           │       │       ├── ProductDetailsEvent.kt
│       │           │       │       ├── ProductDetailsScreen.kt
│       │           │       │       ├── ProductDetailsState.kt
│       │           │       │       └── ProductDetailsViewModel.kt
│       │           │       └── favorites/
│       │           │           ├── component/
│       │           │           │   ├── EmptyFavoritesScreen.kt
│       │           │           │   ├── FavoritesListContent.kt
│       │           │           │   └── SwipeToDeleteProductCard.kt
│       │           │           └── screen/
│       │           │               ├── FavoritesEffect.kt
│       │           │               ├── FavoritesEvent.kt
│       │           │               ├── FavoritesScreen.kt
│       │           │               ├── FavoritesState.kt
│       │           │               └── FavoritesViewModel.kt
│       │           ├── cart/
│       │           │   ├── di/CartModule.kt
│       │           │   ├── data/
│       │           │   │   ├── mapper/toDomain.kt
│       │           │   │   └── repository/CartRepositoryImpl.kt
│       │           │   ├── domain/
│       │           │   │   ├── entity/
│       │           │   │   │   ├── CartItem.kt
│       │           │   │   │   └── CartItemEntity.kt
│       │           │   │   ├── repository/CartRepository.kt
│       │           │   │   └── usecase/
│       │           │   │       ├── AddToCartUseCase.kt
│       │           │   │       ├── ClearCartUseCase.kt
│       │           │   │       ├── GetCartCountUseCase.kt
│       │           │   │       ├── GetCartItemsUseCase.kt
│       │           │   │       ├── RemoveFromCartUseCase.kt
│       │           │   │       └── UpdateCartQuantityUseCase.kt
│       │           │   └── presentation/
│       │           │       ├── component/
│       │           │       │   ├── CartItemCard.kt
│       │           │       │   ├── CartSummaryCard.kt
│       │           │       │   ├── CheckoutSuccessDialog.kt
│       │           │       │   └── EmptyCartScreen.kt
│       │           │       └── screen/
│       │           │           ├── CartEffect.kt
│       │           │           ├── CartEvent.kt
│       │           │           ├── CartScreen.kt
│       │           │           ├── CartState.kt
│       │           │           └── CartViewModel.kt
│       │           ├── auth/
│       │           │   ├── di/AuthModule.kt
│       │           │   ├── data/
│       │           │   │   ├── datastore/AuthPreferences.kt
│       │           │   │   ├── mapper/toDomain.kt
│       │           │   │   ├── remote/
│       │           │   │   │   ├── api/
│       │           │   │   │   │   ├── AuthApiService.kt
│       │           │   │   │   │   └── AuthApiServiceImpl.kt
│       │           │   │   │   └── dto/AuthDto.kt
│       │           │   │   └── repository/AuthRepositoryImpl.kt
│       │           │   ├── domain/
│       │           │   │   ├── entity/AuthUser.kt
│       │           │   │   ├── repository/AuthRepository.kt
│       │           │   │   └── usecase/
│       │           │   │       ├── GetAuthStateUseCase.kt
│       │           │   │       ├── LoginUseCase.kt
│       │           │   │       └── LogoutUseCase.kt
│       │           │   └── presentation/screen/
│       │           │       ├── AuthEffect.kt
│       │           │       ├── AuthEvent.kt
│       │           │       ├── AuthState.kt
│       │           │       ├── AuthViewModel.kt
│       │           │       └── ProfileScreen.kt
│       │           ├── onboarding/
│       │           │   ├── di/OnboardingModule.kt
│       │           │   └── presentation/
│       │           │       ├── model/OnboardingPage.kt
│       │           │       ├── component/
│       │           │       │   ├── OnboardingPageContent.kt
│       │           │       │   └── OnboardingPageIndicator.kt
│       │           │       └── screen/
│       │           │           ├── OnboardingEffect.kt
│       │           │           ├── OnboardingEvent.kt
│       │           │           ├── OnboardingScreen.kt
│       │           │           ├── OnboardingState.kt
│       │           │           └── OnboardingViewModel.kt
│       │           ├── splash/
│       │           │   ├── di/SplashModule.kt
│       │           │   └── presentation/screen/
│       │           │       ├── SplashEffect.kt
│       │           │       ├── SplashScreen.kt
│       │           │       └── SplashViewModel.kt
│       │           ├── setting/
│       │           │   ├── di/SettingModule.kt
│       │           │   └── presentation/
│       │           │       ├── component/
│       │           │       │   ├── DynamicThemeToggleAndroidOnly.kt
│       │           │       │   ├── ThemePreview.kt
│       │           │       │   ├── ThemeSelectionDialog.kt
│       │           │       │   └── ThemeSelectionRow.kt
│       │           │       └── screen/
│       │           │           ├── SettingEffect.kt
│       │           │           ├── SettingEvent.kt
│       │           │           ├── SettingScreen.kt
│       │           │           ├── SettingState.kt
│       │           │           └── SettingViewModel.kt
│       │           └── sample_example/
│       │               ├── di/SampleExampleModule.kt
│       │               └── presentation/screen/
│       │                   ├── SampleEffect.kt
│       │                   ├── SampleEvent.kt
│       │                   ├── SampleExampleScreen.kt
│       │                   ├── SampleExampleViewModel.kt
│       │                   └── SampleState.kt
│       ├── desktopMain/kotlin/com/example/cmp_mvi_template/core/platform/
│       │   ├── database/getDatabaseBuilder.desktop.kt
│       │   ├── datastore/createDataStore.desktop.kt
│       │   ├── http_engine/GetHttpClientEngine.desktop.kt
│       │   ├── theme/PlatformTheme.desktop.kt
│       │   └── toast/
│       │       ├── DesktopToastManager.kt
│       │       ├── RoundedPanel.kt
│       │       └── ToastManager.desktop.kt
│       ├── iosMain/kotlin/com/example/cmp_mvi_template/
│       │   ├── MainViewController.kt
│       │   └── core/platform/
│       │       ├── database/getDatabaseBuilder.ios.kt
│       │       ├── datastore/createDataStore.ios.kt
│       │       ├── http_engine/GetHttpClientEngine.ios.kt
│       │       ├── theme/PlatformTheme.ios.kt
│       │       └── toast/
│       │           ├── IosToastManager.kt
│       │           └── ToastManager.ios.kt
│       └── commonTest/kotlin/com/example/cmp_mvi_template/
│           └── ComposeAppCommonTest.kt
```

---

## 🎯 Next Goals

Here are upcoming feature enhancements and engineering improvements planned for the project:

- 🏷️ **Koin Annotations Integration**
  - Integrate Koin Annotations processing (`@Single`, `@Factory`) to reduce manual DI module boilerplate.

- 💳 **Payment & Checkout Gateway**
  - Integrate simulated/live payment flows (Stripe / Mock Gateway) into the checkout experience.

- 🔔 **Push Notifications**
  - Multiplatform push notifications for order updates and flash sale alerts.

- ⚙️ **Dev Tooling & Automation Scripts**
  - Create Gradle scripts for automatic feature module scaffolding (generating MVI contracts, ViewModel, and screen templates).
  - Package renaming automation scripts.

- 🧪 **Unit & UI Flow Testing**
  - Comprehensive unit testing for UseCases, ViewModels, and Room DAOs.
  - Integration with Maestro for end-to-end multiplatform UI flow tests.

---

## Contributing 🤝

Feel free to contribute to this project by submitting issues, feature suggestions, or pull requests. Your contributions are always welcome! 🙌

## ❤ Show your support

Give a ⭐️ if this project helped you!

<a href="https://www.buymeacoffee.com/codingmeet" target="_blank">
<img src="https://cdn.buymeacoffee.com/buttons/v2/default-yellow.png" alt="Buy Me A Coffee" width="160">
</a>

Your generosity is greatly appreciated! Thank you for supporting this project.

## Connect with me

[![](https://img.shields.io/badge/Youtube-red?style=for-the-badge&logo=youtube&logoColor=white)](https://youtube.com/@CodingMeet26?si=FuKwU-aBaf_5kukR)
[![](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/coding-meet-a74933273/)
[![](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/CodingMeet)

## Author

**Meet**
