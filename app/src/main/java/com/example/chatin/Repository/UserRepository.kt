package com.example.chatin.Repository

import android.util.Log
import com.example.chatin.ModelClass.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    fun adduser(user: UserModel, onSucess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection("users").document(user.id).set(user).addOnSuccessListener {
            Log.d(
                "TAG",
                "Useradded: "
            )
        }.addOnFailureListener {
            Log.d(
                "TAG",
                "Error: "
            )
        }
    }

    fun fetchuser(onResult: (List<UserModel>)->Unit){
        db.collection("users").get().addOnSuccessListener {snapshot->
                val users=snapshot.toObjects(UserModel::class.java)
            onResult(users)

        }
    }

    fun getUserById(uid: String, onResult: (UserModel?) -> Unit) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val user = doc.toObject(UserModel::class.java)
                    onResult(user)
                } else {
                    onResult(null) // User not found
                }
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

}