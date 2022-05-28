package putriiiiiuta.androidlima.challengelimaa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_halaman_home.*
import putriiiiiuta.androidlima.challengelimaa.adapter.AdapterFilm
import putriiiiiuta.androidlima.challengelimaa.model.remote.UserResponse
import putriiiiiuta.androidlima.challengelimaa.utils.AppDataStore
import putriiiiiuta.androidlima.challengelimaa.utils.ViewModelFactory
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelFilm
import putriiiiiuta.androidlima.challengelimaa.vm.ViewModelPreference

@AndroidEntryPoint
class HalamanHome : AppCompatActivity() {

    lateinit var adapterfilm: AdapterFilm
    private lateinit var preferencevm: ViewModelPreference
    private val Context.dataStore by preferencesDataStore(name = "settings")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_home)

        title = "Home"

        val detailUser = intent.getParcelableExtra<UserResponse>("DetailUser")
        btn_editProfile.setOnClickListener {
            val parsing = Intent(this, HalamanProfile::class.java)
            parsing.putExtra("DetailUser", detailUser)
            startActivity(parsing)
        }

        val pref = AppDataStore.getInstance(dataStore)

        preferencevm = ViewModelProvider(this, ViewModelFactory(pref))[ViewModelPreference::class.java]

        preferencevm.getEmail().observe(this) { email ->
            home_username_text.text = "Welcome, $email"
        }
        getDataFilm()
        inirecyler()
    }

    private fun inirecyler() {
        rv_film.layoutManager = LinearLayoutManager(this)
        adapterfilm = AdapterFilm() {
            val pindah = Intent(this, HalamanDetailList::class.java)
            pindah.putExtra("DetailFilm", it)
            startActivity(pindah)
        }
        rv_film.adapter = adapterfilm
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorite -> {
                startActivity(Intent(this, HalamanFavorite::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFilm() {
        val viewModel = ViewModelProvider(this)[ViewModelFilm::class.java]
        viewModel.getLiveFilmObserver().observe(this, Observer {
            if (it != null) {
                adapterfilm.setDataFilm(it)
                adapterfilm.notifyDataSetChanged()
            }
        })
        viewModel.makeApiFilm()
    }

    override fun onResume() {
        super.onResume()
        getDataFilm()
    }
}
