package com.example.sample.network

import com.example.sample.views.model.PicDataModel
import com.example.sample.views.model.PictureData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("?method=flickr.photos.search&api_key=33778bdf808dfab090e8b8cee4040c10&format=json&nojsoncallback=1&text=12%20Rounds&per_page=10")
    fun getMoviePictureModels(): Call<PicDataModel>

    companion object {

        var BASE_URL =
            "https://api.flickr.com/services/rest/"

        fun create(): ApiInterface {

            val gson: Gson by lazy {
                GsonBuilder().setLenient().create()
            }


            val httpClient: OkHttpClient by lazy {
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
                    .build()
            }


            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .client(httpClient)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}