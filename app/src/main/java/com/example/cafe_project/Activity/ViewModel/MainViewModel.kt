package com.example.cafe_project.Activity.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cafe_project.Activity.Domain.BannerModel
import com.example.cafe_project.Activity.Repository.MainRepository

class MainViewModel:ViewModel() {
    private val repository=MainRepository()

    fun loadBanner(): LiveData<MutableList<BannerModel>>{
        return repository.loadbanner()
    }

}