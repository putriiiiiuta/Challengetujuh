package putriiiiiuta.androidlima.challengelimaa.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginResponse(
    @SerializedName("email")
    val description: String,
    @SerializedName("password")
    val director: String,
    @SerializedName("id")
    val id: String
):Parcelable