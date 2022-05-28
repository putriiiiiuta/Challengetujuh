package putriiiiiuta.androidlima.challengelimaa

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_halaman_detail_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import putriiiiiuta.androidlima.challengelimaa.database.FilmDatabase
import putriiiiiuta.androidlima.challengelimaa.model.local.FilmFavorite
import putriiiiiuta.androidlima.challengelimaa.model.remote.FilmResponse
import java.util.*

@AndroidEntryPoint
class HalamanDetailList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_detail_list)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val appDatabase = FilmDatabase.getDatabase(this)

        val datafilm = intent.getParcelableExtra<FilmResponse>("DetailFilm")
        title = datafilm?.title
        detail_judulfilm.text = datafilm?.title
        detail_tglfilm.text = datafilm?.releaseDate
        detail_sutradarafilm.text = datafilm?.director
        detail_filmsinopsis.text = datafilm?.synopsis

        Glide.with(applicationContext)
            .load(datafilm?.image)
            .into(detailimg_film)

        val datafavorite = ArrayList<FilmFavorite>()
        fab_favorite.setOnClickListener{
            GlobalScope.async {
                val s = appDatabase.filmDao().insert(
                    FilmFavorite(
                        datafilm!!.id, datafilm.title, datafilm.releaseDate, datafilm.director
                        , datafilm.synopsis, datafilm.image)
                )
            }

           /* datafilm?.apply {
                val filmfavorit = FilmFavorite(
                    title = title,
                    released = releaseDate,
                    summary = synopsis,
                    director = director,
                    image = image,
                    id = id

                )

                datafavorite.add(filmfavorit)

                Log.d(TAG, "tesdatafavorite: $datafavorite ")
            }*/
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                startActivity(Intent(this, HalamanHome::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}