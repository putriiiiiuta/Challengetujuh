package putriiiiiuta.androidlima.challengelimaa.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterResponse (
    @SerializedName("email")
    val description: String,
    @SerializedName("password")
    val director: String,
    @SerializedName("username")
    val username: String
): Parcelable
