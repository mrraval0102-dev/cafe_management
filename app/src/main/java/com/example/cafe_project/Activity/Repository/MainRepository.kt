package com.example.cafe_project.Activity.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafe_project.Activity.Domain.BannerModel
import com.example.cafe_project.Activity.Domain.CategoryModel
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.example.cafe_project.Activity.Domain.OrderModel

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
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

    fun loadItemCategory(categoryId:String): MutableLiveData<MutableList<ItemsModel>> {
        val itemsLiveData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabse.getReference("Items")
        val query : Query = ref.orderByChild("categoryId").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val item= childSnapshot.getValue(ItemsModel::class.java)
                    item?.let { list.add(it) }
                }
                itemsLiveData.value = list
            }

            override fun onCancelled(snapshot: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return  itemsLiveData
    }
    fun createOrder(orderId:String, itemName:String,price:Double,status:String, onResult: (Boolean) -> Unit) {
        val ref = firebaseDatabse.getReference("Orders")
        val orId = ref.push().key ?: return onResult(false)

        val order = OrderModel(orderId,itemName, price, status)

        ref.child(orId).setValue(order)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }


    fun listOrder(): MutableLiveData<MutableList<OrderModel>> {
        val listOrdersData=MutableLiveData<MutableList<OrderModel>>()
        val ref = firebaseDatabse.getReference("Orders")
        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list= mutableListOf<OrderModel>()
                for (childSnapshot in snapshot.children){
                    val item= childSnapshot.getValue(OrderModel::class.java)
                    item?.let { list.add(it) }
                }
                listOrdersData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return listOrdersData
    }

}