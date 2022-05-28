package putriiiiiuta.androidlima.challengelimaa.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_favorite.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import putriiiiiuta.androidlima.challengelimaa.HalamanFavorite
import putriiiiiuta.androidlima.challengelimaa.R
import putriiiiiuta.androidlima.challengelimaa.database.FilmDatabase
import putriiiiiuta.androidlima.challengelimaa.model.local.FilmFavorite

class AdapterFaforite (val listFilmFavourite: List<FilmFavorite>) : RecyclerView.Adapter<AdapterFaforite.ViewHolder>() {

    var filmfavDb : FilmDatabase? = null

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_list_favorite, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.let {
            Glide.with(holder.itemView.context).load(listFilmFavourite[position].image).into(holder.itemView.img_film)
        }
        holder.itemView.tv_sutradarafilm.text = listFilmFavourite[position].director.toString()
        holder.itemView.tv_judulfilm.text = listFilmFavourite[position].title.toString()
        holder.itemView.tv_tanggalfilm.text = listFilmFavourite[position].released
        holder.itemView.btn_favdelete.setOnClickListener {
            filmfavDb = FilmDatabase.getDatabase(it.context)

            val ADBuilder = AlertDialog.Builder(it.context)
                .setTitle("Hapus Data")
                .setMessage("Yakin Hapus?")
                .setPositiveButton("Ya"){ dialogInterface: DialogInterface, i: Int ->
                    GlobalScope.async {

                        val deleteResult = filmfavDb?.filmDao()?.delete(listFilmFavourite[position])

                        (holder.itemView.context as HalamanFavorite).runOnUiThread {
                            if(deleteResult != null){
                                Toast.makeText(it.context, "Data Berhasil dihapus", Toast.LENGTH_LONG).show()
                                (holder.itemView.context as HalamanFavorite).recreate()
                            }else{
                                Toast.makeText(it.context, "Data Gagal dihapus", Toast.LENGTH_LONG).show()
                            }
                        }

                    }
                }
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()

        }
    }

    override fun getItemCount(): Int {
        return listFilmFavourite.size
    }


}