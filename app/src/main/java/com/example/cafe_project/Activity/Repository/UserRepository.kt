package com.example.cafe_project.Activity.Repository

import com.example.cafe_project.Activity.Domain.UserModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


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

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        usersRef.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (userSnapshot in snapshot.children) {
                            val user = userSnapshot.getValue(UserModel::class.java)
                            if (user != null && user.password == password) {
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

}
