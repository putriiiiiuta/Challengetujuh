package putriiiiiuta.androidlima.challengelimaa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_halaman_favorite.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import putriiiiiuta.androidlima.challengelimaa.adapter.AdapterFaforite
import putriiiiiuta.androidlima.challengelimaa.database.FilmDatabase

@AndroidEntryPoint
class HalamanFavorite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_favorite)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "Favorite List"

        val appDatabase = FilmDatabase.getDatabase(this)

        GlobalScope.async {
            val res = appDatabase.filmDao().getFilmFav()
            runOnUiThread {
                rv_favfilm.layoutManager = LinearLayoutManager(this@HalamanFavorite)
                rv_favfilm.adapter = AdapterFaforite(res)
            }
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
}
