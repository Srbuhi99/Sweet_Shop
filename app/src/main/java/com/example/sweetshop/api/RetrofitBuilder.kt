package com.example.sweetshop.api




import com.example.sweetshop.utils.AuthTokenInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitBuilder {

    private const val BASE_URL = "http://212.42.196.110:6121/api/"


    val httpClient =  OkHttpClient.Builder()
            .addInterceptor(AuthTokenInterceptor())


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)


}