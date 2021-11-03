package yayasan.idn.sholatreminder.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class Config {

    fun getRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.pray.zone/v2/times/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(ModelWaktu::class.java)

}

interface ModelWaktu{
    @GET("this_month.json?")
    fun getModelWaktu(
        @Query("longitude") lng: String,
        @Query("latitude") lat: String,
        @Query("elevation") ev: String,
        @Query("month") mo: String
    ) : Call<Model>
}