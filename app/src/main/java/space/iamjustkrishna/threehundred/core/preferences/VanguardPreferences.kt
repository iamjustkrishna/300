package space.iamjustkrishna.threehundred.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import space.iamjustkrishna.threehundred.core.model.UserPreferences
import java.io.IOException

val Context.vanguardDataStore: DataStore<Preferences> by preferencesDataStore(name = "vanguard_preferences")

/**
 * Persistent user preferences manager powered by Android Jetpack DataStore.
 * Enforces offline-first discipline preferences, Vanguard pass status, and AMOLED power saving modes.
 */
class VanguardPreferences(
    private val dataStore: DataStore<Preferences>
) {
    constructor(context: Context) : this(context.vanguardDataStore)

    private object PreferencesKeys {
        val HAS_COMPLETED_ONBOARDING = booleanPreferencesKey("has_completed_onboarding")
        val IS_VANGUARD_UNLOCKED = booleanPreferencesKey("is_vanguard_unlocked")
        val IS_PURE_BLACK_OLED = booleanPreferencesKey("is_pure_black_oled")
        val IS_LOW_POWER_MODE = booleanPreferencesKey("is_low_power_mode")
        val ACTIVE_THEME_ID = stringPreferencesKey("active_theme_id")
    }

    private val safePreferencesFlow: Flow<Preferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }

    val hasCompletedOnboarding: Flow<Boolean> = safePreferencesFlow
        .map { preferences ->
            preferences[PreferencesKeys.HAS_COMPLETED_ONBOARDING] ?: false
        }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_COMPLETED_ONBOARDING] = completed
        }
    }

    val isVanguardUnlocked: Flow<Boolean> = safePreferencesFlow
        .map { preferences ->
            preferences[PreferencesKeys.IS_VANGUARD_UNLOCKED] ?: false
        }

    suspend fun setVanguardUnlocked(unlocked: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_VANGUARD_UNLOCKED] = unlocked
        }
    }

    val isPureBlackOled: Flow<Boolean> = safePreferencesFlow
        .map { preferences ->
            preferences[PreferencesKeys.IS_PURE_BLACK_OLED] ?: true
        }

    suspend fun setPureBlackOled(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_PURE_BLACK_OLED] = enabled
        }
    }

    val isLowPowerMode: Flow<Boolean> = safePreferencesFlow
        .map { preferences ->
            preferences[PreferencesKeys.IS_LOW_POWER_MODE] ?: false
        }

    suspend fun setLowPowerMode(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_LOW_POWER_MODE] = enabled
        }
    }

    val activeThemeId: Flow<String> = safePreferencesFlow
        .map { preferences ->
            preferences[PreferencesKeys.ACTIVE_THEME_ID] ?: DEFAULT_THEME_ID
        }

    suspend fun setActiveThemeId(themeId: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ACTIVE_THEME_ID] = themeId
        }
    }

    val userPreferences: Flow<UserPreferences> = safePreferencesFlow
        .map { preferences ->
            UserPreferences(
                hasCompletedOnboarding = preferences[PreferencesKeys.HAS_COMPLETED_ONBOARDING] ?: false,
                isVanguardUnlocked = preferences[PreferencesKeys.IS_VANGUARD_UNLOCKED] ?: false,
                isPureBlackOled = preferences[PreferencesKeys.IS_PURE_BLACK_OLED] ?: true,
                isLowPowerMode = preferences[PreferencesKeys.IS_LOW_POWER_MODE] ?: false,
                activeThemeId = preferences[PreferencesKeys.ACTIVE_THEME_ID] ?: DEFAULT_THEME_ID
            )
        }

    companion object {
        const val DEFAULT_THEME_ID = "obsidian_ritual"
    }
}
