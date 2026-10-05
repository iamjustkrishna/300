package space.iamjustkrishna.threehundred.feature.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.iamjustkrishna.threehundred.core.data.ThemeRepository

class HomeViewModel(
    application: Application,
    private val themeRepository: ThemeRepository
) : AndroidViewModel(application) {

    constructor(application: Application) : this(application, ThemeRepository(application))

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadThemes()
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
        loadThemes(category)
    }

    fun retry() {
        loadThemes(_uiState.value.selectedCategory)
    }

    private fun loadThemes(category: String = _uiState.value.selectedCategory) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val themesFlow = if (category == "Featured") {
                themeRepository.getAllThemes()
            } else {
                themeRepository.getThemesByCategory(category)
            }

            themesFlow
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.localizedMessage ?: "Failed to load Spartan themes"
                        )
                    }
                }
                .collect { dbThemes ->
                    val curated = if (dbThemes.isEmpty()) {
                        if (category == "Featured") SampleArchiveThemes
                        else SampleArchiveThemes.filter { it.category == category }
                    } else {
                        dbThemes.map { theme ->
                            val artwork = when (theme.id) {
                                "spartan_dawn" -> ThemeArtworkType.SPARTAN_DAWN
                                "helm" -> ThemeArtworkType.HELM
                                "aegean_noir" -> ThemeArtworkType.AEGEAN_NOIR
                                "last_light" -> ThemeArtworkType.LAST_LIGHT
                                else -> ThemeArtworkType.COLONNADE_PORTAL
                            }
                            HomeThemeItem(
                                id = theme.id,
                                title = theme.title,
                                subtitle = theme.stoicQuote,
                                blackPercentage = "${theme.blackPercent} Black",
                                artworkType = artwork,
                                category = theme.category
                            )
                        }
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            curatedThemes = curated
                        )
                    }
                }
        }
    }
}
