package space.iamjustkrishna.threehundred.feature.preview

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.iamjustkrishna.threehundred.core.data.ThemeRepository
import space.iamjustkrishna.threehundred.core.preferences.VanguardPreferences
import space.iamjustkrishna.threehundred.core.system.WallpaperApplier

class ThemePreviewViewModel(
    application: Application,
    private val themeRepository: ThemeRepository,
    private val wallpaperApplier: WallpaperApplier,
    private val preferences: VanguardPreferences
) : AndroidViewModel(application) {

    constructor(application: Application) : this(
        application,
        ThemeRepository(application),
        WallpaperApplier(application),
        VanguardPreferences(application)
    )

    private val _uiState = MutableStateFlow(ThemePreviewUiState())
    val uiState: StateFlow<ThemePreviewUiState> = _uiState.asStateFlow()

    fun loadTheme(themeId: String) {
        viewModelScope.launch {
            themeRepository.getThemeById(themeId).collect { theme ->
                val fallback = ThemeRepository.LAUNCH_THEMES.find { it.id == themeId }
                    ?: ThemeRepository.LAUNCH_THEMES.first()
                val selectedTheme = theme ?: fallback
                _uiState.update {
                    it.copy(
                        theme = selectedTheme,
                        isFavorite = selectedTheme.isFavorite
                    )
                }
            }
        }
    }

    fun setPreviewMode(mode: PreviewMode) {
        _uiState.update { it.copy(previewMode = mode) }
    }

    fun toggleFavorite() {
        val themeId = _uiState.value.theme?.id ?: return
        viewModelScope.launch {
            themeRepository.toggleFavorite(themeId)
            _uiState.update { it.copy(isFavorite = !it.isFavorite) }
        }
    }

    fun applyTheme() {
        val theme = _uiState.value.theme ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isApplying = true, errorMessage = null) }
            try {
                // Generate high-resolution AMOLED wallpaper bitmap
                val width = 1080
                val height = 2400
                val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                // True OLED deep void black background
                canvas.drawColor(Color.parseColor("#08080A"))

                // Draw classical Spartan accent glow
                val paint = Paint().apply {
                    isAntiAlias = true
                    color = Color.parseColor("#1AC5A869")
                    style = Paint.Style.FILL
                }
                canvas.drawCircle(width / 2f, height * 0.45f, width * 0.4f, paint)

                val targetScreen = when (_uiState.value.previewMode) {
                    PreviewMode.HOME_SCREEN -> WallpaperApplier.TargetScreen.HOME
                    PreviewMode.LOCK_SCREEN -> WallpaperApplier.TargetScreen.LOCK
                }

                val result = wallpaperApplier.applyWallpaper(bitmap, targetScreen)
                if (result.isSuccess) {
                    preferences.setActiveThemeId(theme.id)
                    _uiState.update {
                        it.copy(isApplying = false, isAppliedSuccess = true)
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isApplying = false,
                            errorMessage = result.exceptionOrNull()?.localizedMessage ?: "Failed to apply wallpaper"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isApplying = false,
                        errorMessage = e.localizedMessage ?: "Failed to apply theme"
                    )
                }
            }
        }
    }

    fun dismissSuccessModal() {
        _uiState.update { it.copy(isAppliedSuccess = false) }
    }
}
