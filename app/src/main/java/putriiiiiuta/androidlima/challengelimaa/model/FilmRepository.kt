package putriiiiiuta.androidlima.challengelimaa.model

import kotlinx.coroutines.flow.Flow
import putriiiiiuta.androidlima.challengelimaa.database.FilmDao
import putriiiiiuta.androidlima.challengelimaa.model.local.FilmFavorite

class FilmRepository (private val filmDao: FilmDao){
    fun addFavorite(favorite: FilmFavorite) = filmDao.insert(favorite)
    fun deleteFavorite(favorite: FilmFavorite) = filmDao.delete(favorite)
    fun getFavFilm(): List<FilmFavorite> = filmDao.getFilmFav()
}