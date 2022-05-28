package putriiiiiuta.androidlima.challengelimaa.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import putriiiiiuta.androidlima.challengelimaa.model.local.FilmFavorite

@Database(entities = [FilmFavorite::class], version = 1)
abstract class FilmDatabase: RoomDatabase() {

    abstract fun filmDao(): FilmDao

    companion object{
        private var INSTANCE: FilmDatabase? = null
        fun getDatabase(context: Context): FilmDatabase{
            if (INSTANCE == null) {
                synchronized(FilmDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FilmDatabase::class.java, "film_db")
                        .build()
                }
            }
            return INSTANCE as FilmDatabase
        }
    }
}