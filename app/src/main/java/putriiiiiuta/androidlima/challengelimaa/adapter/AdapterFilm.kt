package putriiiiiuta.androidlima.challengelimaa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_adapter.view.*
import putriiiiiuta.androidlima.challengelimaa.R
import putriiiiiuta.androidlima.challengelimaa.model.remote.FilmResponse

class AdapterFilm (private var onclick : (FilmResponse)->Unit) : RecyclerView.Adapter<AdapterFilm.ViewHolder>(){

    private var dataFilm : List<FilmResponse>? = null

    fun setDataFilm(film : List<FilmResponse>){
        this.dataFilm = film
    }

    class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_judulfilm.text = dataFilm!![position].title
        holder.itemView.tv_tanggalfilm.text = dataFilm!![position].releaseDate
        holder.itemView.tv_sutradarafilm.text = dataFilm!![position].director

        Glide.with(holder.itemView.context)
            .load(dataFilm!![position].image)
            .into(holder.itemView.img_film)

        holder.itemView.img_film.setOnClickListener {
            onclick(dataFilm!![position])
        }
    }

    override fun getItemCount(): Int {
        if (dataFilm == null){
            return 0
        }else{
            return dataFilm!!.size
        }
    }

}

