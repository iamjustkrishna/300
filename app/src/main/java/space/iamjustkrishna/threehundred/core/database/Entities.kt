package space.iamjustkrishna.threehundred.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import space.iamjustkrishna.threehundred.core.model.SpartanTheme
import space.iamjustkrishna.threehundred.core.model.SpartanWallpaper

@Entity(tableName = "themes")
data class ThemeEntity(
    @PrimaryKey val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val previewUrl: String,
    val blackPercent: String,
    val isVanguardExclusive: Boolean,
    val stoicQuote: String,
    val isFavorite: Boolean = false
)

fun ThemeEntity.toExternalModel(): SpartanTheme = SpartanTheme(
    id = id,
    title = title,
    subtitle = subtitle,
    category = category,
    previewUrl = previewUrl,
    blackPercent = blackPercent,
    isVanguardExclusive = isVanguardExclusive,
    stoicQuote = stoicQuote,
    isFavorite = isFavorite
)

fun SpartanTheme.toEntity(): ThemeEntity = ThemeEntity(
    id = id,
    title = title,
    subtitle = subtitle,
    category = category,
    previewUrl = previewUrl,
    blackPercent = blackPercent,
    isVanguardExclusive = isVanguardExclusive,
    stoicQuote = stoicQuote,
    isFavorite = isFavorite
)

@Entity(tableName = "wallpapers")
data class WallpaperEntity(
    @PrimaryKey val id: String,
    val title: String,
    val category: String,
    val resUrl: String,
    val isVanguardExclusive: Boolean,
    val isFavorite: Boolean = false
)

fun WallpaperEntity.toExternalModel(): SpartanWallpaper = SpartanWallpaper(
    id = id,
    title = title,
    category = category,
    resUrl = resUrl,
    isVanguardExclusive = isVanguardExclusive,
    isFavorite = isFavorite
)

fun SpartanWallpaper.toEntity(): WallpaperEntity = WallpaperEntity(
    id = id,
    title = title,
    category = category,
    resUrl = resUrl,
    isVanguardExclusive = isVanguardExclusive,
    isFavorite = isFavorite
)
