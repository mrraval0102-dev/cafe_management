package com.example.cafe_project.Activity.Domain

import java.io.Serializable

data class OrderModel(
    var orderId: String = "",
    var itemName: String = "",
    var price: Double = 0.0,
    var status: String = ""
) : Serializable
