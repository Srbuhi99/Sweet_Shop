package com.example.sweetshop.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sweetshop.api.ApiHelper
import com.example.sweetshop.repository.MainRepository
import com.example.sweetshop.viewmodel.HomeViewModel
import com.example.sweetshop.viewmodel.ProductDetailsViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(MainRepository(apiHelper)) as T

        }

        if(modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)){
            return  ProductDetailsViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }



}