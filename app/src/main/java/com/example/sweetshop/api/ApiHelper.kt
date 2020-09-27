package com.example.sweetshop.api


class ApiHelper(private val apiService: ApiService) {

    suspend fun getCategories() = apiService.getCategories()

    suspend fun getMostPopularProduct() = apiService.getMostPopularProduct()

    suspend fun getProductById(id:Int) = apiService.getProductById(id)
}