package space.iamjustkrishna.threehundred.feature.preview

import space.iamjustkrishna.threehundred.core.model.SpartanTheme

enum class PreviewMode {
    HOME_SCREEN,
    LOCK_SCREEN
}

/**
 * UI State for SCR-004: Theme Live Preview & 1-Tap Apply.
 */
data class ThemePreviewUiState(
    val theme: SpartanTheme? = null,
    val previewMode: PreviewMode = PreviewMode.HOME_SCREEN,
    val isFavorite: Boolean = false,
    val isApplying: Boolean = false,
    val isAppliedSuccess: Boolean = false,
    val errorMessage: String? = null
)
