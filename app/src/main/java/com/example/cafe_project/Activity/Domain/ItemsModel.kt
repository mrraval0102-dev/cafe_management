package com.example.cafe_project.Activity.Domain

import java.io.Serializable

data class ItemsModel(
    var title:String="",
    var description:String="",
    var picUrl:ArrayList<String> = ArrayList(),
    var price:Double = 0.0,
    var rating:Double = 0.0,
    var numberInCart:Int = 0,
    var extra:String = "",
): Serializable {

    val tite: String
        get() {
            TODO()
        }
}
