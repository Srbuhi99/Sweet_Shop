package com.example.sweetshop.repository

import com.example.sweetshop.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getCategories() = apiHelper.getCategories()

    suspend fun getMostPopularProducts() = apiHelper.getMostPopularProduct()

    suspend fun getProductById(id :Int) = apiHelper.getProductById(id)

}