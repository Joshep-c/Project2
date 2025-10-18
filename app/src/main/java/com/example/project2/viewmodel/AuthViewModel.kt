package com.example.project2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project2.model.UserModel
import com.google.firebase.auth.FirebaseAuth // Import FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore // Import FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance() // Get instance of FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance() // Get instance of FirebaseFirestore

    fun login(){
        // Your login logic here
    }

    fun signup(email: String, name: String, password: String, onResult: (Boolean, String?) -> Unit){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    val userId = task.result?.user?.uid

                    if (userId != null) {
                        val userModel = UserModel(name, email, userId)
                        firestore.collection("users").document(userId)
                            .set(userModel)
                            .addOnCompleteListener { dbTask ->
                                if(dbTask.isSuccessful){
                                    onResult(true, null)
                                } else {
                                    onResult(false, "Database error: ${dbTask.exception?.message}")
                                }
                            }
                    } else {
                        onResult(false, "Failed to get user ID.")
                    }
                }
                else {
                    onResult(false, "Sign-up failed: ${task.exception?.message}")
                }
            }
    }
}
