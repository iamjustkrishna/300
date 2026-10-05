package space.iamjustkrishna.threehundred.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ThemeDao {

    @Query("SELECT * FROM themes ORDER BY id ASC")
    fun getAllThemes(): Flow<List<ThemeEntity>>

    @Query("SELECT * FROM themes WHERE category = :category ORDER BY id ASC")
    fun getThemesByCategory(category: String): Flow<List<ThemeEntity>>

    @Query("SELECT * FROM themes WHERE id = :id LIMIT 1")
    fun getThemeById(id: String): Flow<ThemeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertThemes(themes: List<ThemeEntity>)

    @Update
    suspend fun updateTheme(theme: ThemeEntity)

    @Query("SELECT COUNT(*) FROM themes")
    suspend fun getThemeCount(): Int

    @Query("UPDATE themes SET isFavorite = CASE WHEN isFavorite = 1 THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun toggleFavorite(id: String)

    @Query("UPDATE themes SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: String, isFavorite: Boolean)
}

@Dao
interface WallpaperDao {

    @Query("SELECT * FROM wallpapers ORDER BY id ASC")
    fun getAllWallpapers(): Flow<List<WallpaperEntity>>

    @Query("SELECT * FROM wallpapers WHERE category = :category ORDER BY id ASC")
    fun getWallpapersByCategory(category: String): Flow<List<WallpaperEntity>>

    @Query("SELECT * FROM wallpapers WHERE id = :id LIMIT 1")
    fun getWallpaperById(id: String): Flow<WallpaperEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWallpapers(wallpapers: List<WallpaperEntity>)

    @Update
    suspend fun updateWallpaper(wallpaper: WallpaperEntity)

    @Query("SELECT COUNT(*) FROM wallpapers")
    suspend fun getWallpaperCount(): Int

    @Query("UPDATE wallpapers SET isFavorite = CASE WHEN isFavorite = 1 THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun toggleFavorite(id: String)
}
