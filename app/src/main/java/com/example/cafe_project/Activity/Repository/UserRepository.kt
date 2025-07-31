package com.example.cafe_project.Activity.Repository

import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafe_project.Activity.Domain.ItemsModel
import com.example.cafe_project.Activity.Domain.UserModel
import com.example.cafe_project.Activity.ViewModel.UserViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.log


class UserRepository {
    private val database = FirebaseDatabase.getInstance()
    private val usersRef = database.getReference("Users")

    fun signup(firstName: String, lastName: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        val userId = usersRef.push().key ?: return onResult(false)

        val user = UserModel(firstName, lastName, email, password)

        usersRef.child(userId).setValue(user)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }
    }

    fun login(email: String, password: String, sharedPref: SharedPreferences, onResult: (Boolean) -> Unit) {
        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(UserModel::class.java)
                            if (user != null && user.password == password) {
                                sharedPref.edit {
                                    putString("userId", userSnapshot.key)
                                }
                                onResult(true)
                                return
                            }
                        }
                    }
                    onResult(false)
                }

                override fun onCancelled(error: DatabaseError) {
                    onResult(false)
                }
            })
    }


    fun profile(id: String): LiveData<UserModel?> {
        val userData = MutableLiveData<UserModel?>()

        usersRef.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(UserModel::class.java)
                    Log.d("Firebase", "User: $user")
                    userData.value = user
                } else {
                    userData.value = null
                }
            }

            override fun onCancelled(error: DatabaseError) {
                userData.value = null
            }
        })

        return userData
    }

}
