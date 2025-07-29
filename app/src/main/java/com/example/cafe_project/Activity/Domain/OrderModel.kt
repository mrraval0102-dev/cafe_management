package com.example.cafe_project.Activity.Domain


data class OrderModel(
    val orderId: String,
    val itemName: String,
    val price: Double,
    val status: String
)
