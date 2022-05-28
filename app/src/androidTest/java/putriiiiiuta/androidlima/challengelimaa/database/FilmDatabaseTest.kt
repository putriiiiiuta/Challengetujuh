package putriiiiiuta.androidlima.challengelimaa.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import putriiiiiuta.androidlima.challengelimaa.model.local.FilmFavorite

@RunWith(AndroidJUnit4::class)
class FilmDatabaseTest : TestCase(){
    private lateinit var db: FilmDatabase
    private lateinit var dao: FilmDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FilmDatabase::class.java).build()
        dao = db.filmDao()
    }

    @After
    public override fun tearDown() {
        db.close()
    }

    @Test
    fun filmDao() {
        val film = FilmFavorite("1", "b", "b", "b", "b", "b")
        val res1 = dao.getFilmFav()
        val res2 = dao.insert(film)
        val res3 = dao.delete(film)
        val res4 = dao.update(film)
    }
}