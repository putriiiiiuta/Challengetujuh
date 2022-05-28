package putriiiiiuta.androidlima.challengelimaa.model.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "film")
@Parcelize
data class FilmFavorite(
    @PrimaryKey() @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "released") val released: String,
    @ColumnInfo(name = "director") val director: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "image") val image: String
):Parcelable