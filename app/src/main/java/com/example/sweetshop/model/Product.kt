package com.example.sweetshop.model

data class Product(

    var id:Int,
    var productshelfLife:String,
    var description:String,
    var imageUrl:String?,
    var price:Int,
    var productCode:String,
    var productName:String
)