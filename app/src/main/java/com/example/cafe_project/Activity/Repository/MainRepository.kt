package com.example.cafe_project.Activity.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafe_project.Activity.Domain.BannerModel
import com.example.cafe_project.Activity.Domain.CategoryModel
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainRepository {
    private val firebaseDatabse = FirebaseDatabase.getInstance()

    fun loadbanner():LiveData<MutableList<BannerModel>>{
        val listData=MutableLiveData<MutableList<BannerModel>>()
        val ref = firebaseDatabse.getReference("Banner")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<BannerModel>()
                 for (childSnapshot in snapshot.children){
                     val item= childSnapshot.getValue(BannerModel::class.java)
                     item?.let { list.add(it) }
                 }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }

    fun loadcategory():LiveData<MutableList<CategoryModel>>{
        val listData=MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabse.getReference("Category")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val item= childSnapshot.getValue(CategoryModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }

    fun loadpopular():LiveData<MutableList<ItemsModel>>{
        val listData=MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabse.getReference("Popular")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val item= childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listData
    }


}