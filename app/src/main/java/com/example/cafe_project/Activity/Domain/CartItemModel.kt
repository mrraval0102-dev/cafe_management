package com.example.cafe_project.Activity.Domain

import com.example.cafe_project.Activity.common.CoffeeSize


data class CartItemModel(
    val name: String,
    var quantity: Int,
    val price: Double,
    val coffeeSize:CoffeeSize
)

