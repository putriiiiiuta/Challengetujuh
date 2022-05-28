package putriiiiiuta.androidlima.challengelimaa.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import putriiiiiuta.androidlima.challengelimaa.model.local.FilmFavorite

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun getFilmFav(): List<FilmFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(filmfav: FilmFavorite)

    @Update
    fun update(filmfav: FilmFavorite)

    @Delete
    fun delete(filmfav: FilmFavorite)

}