package com.example.sweetshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.sweetshop.common.Resource
import com.example.sweetshop.repository.MainRepository
import kotlinx.coroutines.Dispatchers

class ProductDetailsViewModel(private val mainRepository: MainRepository):ViewModel() {


    var _count : MutableLiveData<Int> = MutableLiveData(0)

    var count: LiveData<Int> = _count


    fun getProductById(id:Int)  = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getProductById(id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }



     fun increment(){
         _count.value = (_count.value?:0) + 1
     }

    fun decrement(){

        if(_count.value != 0)
        _count.value = (_count.value?:0) - 1
    }
}