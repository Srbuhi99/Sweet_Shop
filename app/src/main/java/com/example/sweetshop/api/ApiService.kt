package com.example.sweetshop.api

import com.example.sweetshop.model.MostPopularProductsModel
import com.example.sweetshop.model.CategoryModel
import com.example.sweetshop.model.SearchByIdModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("category/getTopCategoriesForMobile")
    suspend fun getCategories(): CategoryModel

    @GET("product/getMostPopularProducts")
    suspend fun getMostPopularProduct():MostPopularProductsModel

    @GET("product/getProductById")
    suspend fun getProductById( @Query("id") id:Int): SearchByIdModel


}