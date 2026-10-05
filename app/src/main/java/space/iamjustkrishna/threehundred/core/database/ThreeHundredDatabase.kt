package space.iamjustkrishna.threehundred.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [ThemeEntity::class, WallpaperEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ThreeHundredDatabase : RoomDatabase() {

    abstract fun themeDao(): ThemeDao
    abstract fun wallpaperDao(): WallpaperDao

    companion object {
        private const val DATABASE_NAME = "three_hundred_database"

        @Volatile
        private var INSTANCE: ThreeHundredDatabase? = null

        fun getInstance(context: Context): ThreeHundredDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ThreeHundredDatabase::class.java,
                    DATABASE_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
            }
        }
    }
}
