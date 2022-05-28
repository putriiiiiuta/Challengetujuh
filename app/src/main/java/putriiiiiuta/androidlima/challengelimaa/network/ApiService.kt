package putriiiiiuta.androidlima.challengelimaa.network

import putriiiiiuta.androidlima.challengelimaa.model.remote.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("apifilm.php")
    suspend fun GetDatafilm() : List<FilmResponse>

    @POST("register.php")
    @FormUrlEncoded
    suspend fun addRegister(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") pasword: String,
    ): RegisterResponse

    @POST("login.php")
    @FormUrlEncoded
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("apiuser.php")
    suspend fun listUser(): List<UserResponse>

    @POST("detailuser.php")
    @FormUrlEncoded
    suspend fun detailUser(
        @Field("id") id: Int
    ):List<DetailUserResponse>

    @POST("updateuser.php")
    @FormUrlEncoded
    suspend fun updateUser(
        @Field("id")id : String,
        @Field("complete_name")completename : String,
        @Field("username")username : String,
        @Field("address")adress : String,
        @Field("dateofbirth")birthday : String
    ): UpdateUserResponse
}