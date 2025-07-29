package com.example.cafe_project.Activity.Domain

import java.io.Serializable

data class UserModel(
    var firstName:String="",
    var lastName:String="",
    var email:String = "",
    var password:String=""
): Serializable
