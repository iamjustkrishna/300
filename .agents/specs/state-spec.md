# State & Data Architecture Specification

---

## 1. Domain Models & Contracts

### 1.1. Wallpaper Model
```kotlin
data class Wallpaper(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: WallpaperCategory,
    val assetPath: String,
    val isPremium: Boolean,
    val downloadUrl: String? = null,
    val tags: List<String> = emptyList()
)

enum class WallpaperCategory {
    ALL,
    SHIELDS,
    RUINS,
    BRONZE,
    BATTLEFIELD
}
```

### 1.2. Daily Challenge Model
```kotlin
data class DailyChallenge(
    val dayNumber: Int, // 1 to 300
    val title: String,
    val quote: String,
    val quoteAuthor: String,
    val taskDescription: String,
    val focusArea: String,
    val isCompleted: Boolean = false,
    val completedAtUtcMillis: Long? = null
)
```

### 1.3. Streak State Model
```kotlin
data class StreakInfo(
    val currentStreakDays: Int,
    val longestStreakDays: Int,
    val totalDaysConquered: Int,
    val lastCompletedDateUtc: String?, // YYYY-MM-DD
    val currentMilestone: MilestoneTier,
    val isTodayCompleted: Boolean
)

enum class MilestoneTier(val requiredDays: Int, val title: String) {
    THE_FIRST_STAND(3, "The First Stand"),
    THE_PHALANX(7, "The Phalanx"),
    THE_SHIELD_WALL(14, "The Shield-Wall"),
    SPARTAN_WARRIOR(30, "Spartan Warrior"),
    THE_IMMORTAL(100, "The Immortal"),
    THE_300_LEGEND(300, "The 300 Legend")
}
```

---

## 2. Persistence Contracts

### 2.1. Room Database Entities

#### `ChallengeEntity`
```kotlin
@Entity(tableName = "challenges")
data class ChallengeEntity(
    @PrimaryKey val dayNumber: Int,
    val title: String,
    val quote: String,
    val quoteAuthor: String,
    val taskDescription: String,
    val focusArea: String,
    val isCompleted: Boolean,
    val completedAtUtcMillis: Long?
)
```

#### `WallpaperEntity`
```kotlin
@Entity(tableName = "wallpapers")
data class WallpaperEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val assetPath: String,
    val isPremium: Boolean,
    val isFavorite: Boolean = false
)
```

### 2.2. Jetpack DataStore Preferences Keys
```kotlin
object PreferencesKeys {
    val IS_ONBOARDING_COMPLETED = booleanPreferencesKey("is_onboarding_completed")
    val SELECTED_IDENTITY_PATH = stringPreferencesKey("selected_identity_path")
    val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
    val NOTIFICATION_HOUR = intPreferencesKey("notification_hour") // Default 8
    val NOTIFICATION_MINUTE = intPreferencesKey("notification_minute") // Default 0
    val IS_VANGUARD_UNLOCKED = booleanPreferencesKey("is_vanguard_unlocked")
    val LAST_STREAK_CHECK_DATE = stringPreferencesKey("last_streak_check_date")
}
```

---

## 3. UI State Machines & Contracts (UDF)

### 3.1. Home Screen UI State & Intents
```kotlin
data class HomeUiState(
    val isLoading: Boolean = false,
    val todayChallenge: DailyChallenge? = null,
    val streakInfo: StreakInfo = StreakInfo(0, 0, 0, null, MilestoneTier.THE_FIRST_STAND, false),
    val featuredWallpapers: List<Wallpaper> = emptyList(),
    val errorMessage: String? = null
)

sealed interface HomeIntent {
    data object Refresh : HomeIntent
    data class CompleteTodayChallenge(val dayNumber: Int) : HomeIntent
    data class SelectWallpaper(val wallpaperId: String) : HomeIntent
    data object PinWidgetRequested : HomeIntent
}

sealed interface HomeEffect {
    data class NavigateToPreview(val wallpaperId: String) : HomeEffect
    data class ShowToast(val message: String) : HomeEffect
    data object TriggerHeavyHaptic : HomeEffect
}
```

### 3.2. Wallpaper Catalog UI State
```kotlin
data class WallpaperCatalogUiState(
    val isLoading: Boolean = false,
    val selectedCategory: WallpaperCategory = WallpaperCategory.ALL,
    val wallpapers: List<Wallpaper> = emptyList(),
    val isPremiumUser: Boolean = false
)
```

---

## 4. Repository & UseCase Architecture

### 4.1. Core Repositories
- `ChallengeRepository`: Exposes `getTodayChallenge(): Flow<DailyChallenge>`, `completeChallenge(dayNumber: Int)`, `getChallengeHistory(): Flow<List<DailyChallenge>>`.
- `StreakRepository`: Exposes `getStreakInfo(): Flow<StreakInfo>`, `recalculateStreak()`.
- `WallpaperRepository`: Exposes `getWallpapers(category: WallpaperCategory): Flow<List<Wallpaper>>`, `getWallpaperById(id: String): Flow<Wallpaper?>`.
- `UserPreferencesRepository`: Exposes onboarding status, notification preferences, and Vanguard entitlement.

### 4.2. Core UseCases
- `GetTodayChallengeUseCase`: Evaluates calendar day relative to start date and returns today's challenge.
- `CompleteDailyChallengeUseCase`: Marks challenge complete in Room, invokes `RecalculateStreakUseCase`, and dispatches widget update event.
- `ApplyWallpaperUseCase`: Downsamples bitmap to target display insets, calls `WallpaperManager.setBitmap()`, records analytics.
