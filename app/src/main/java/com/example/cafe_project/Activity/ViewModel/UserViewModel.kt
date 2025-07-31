package com.example.cafe_project.Activity.ViewModel

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.example.cafe_project.Activity.Domain.UserModel
import com.example.cafe_project.Activity.Repository.UserRepository


class UserViewModel: ViewModel() {
    private val repository= UserRepository()

    private val _signupStatus = MutableLiveData<Boolean>()
    val signupStatus: LiveData<Boolean> = _signupStatus

    fun signup(firstName: String, lastName: String, email: String, password: String) {
        repository.signup(firstName, lastName, email, password) { success ->
            _signupStatus.postValue(success)
        }
    }

    fun login(email: String, password: String, sharedPref: SharedPreferences) {
        repository.login(email, password,sharedPref) { success ->
            _signupStatus.postValue(success)
        }
    }

    fun loadProfile(id: String): LiveData<UserModel?> {
        return repository.profile(id)
    }
}