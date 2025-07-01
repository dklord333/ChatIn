package com.example.chatin.Repository

import android.util.Log
import com.example.chatin.ModelClass.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance()
    fun adduser(user: UserModel, onSucess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.getReference("Users").child(user.userid).setValue(user).addOnSuccessListener {
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

    fun fetchuser(currentUserId: String, onResult: (List<UserModel>) -> Unit) {
        db.getReference("Users")
            .get()
            .addOnSuccessListener { snapshot ->
                val users = snapshot.children.mapNotNull { it.getValue(UserModel::class.java) }
                val filtered_users = users.filter { it.userid != currentUserId }
                Log.d("UserListDebug", "Fetched ${users.size} users")
                onResult(filtered_users)
                Log.d("FilteredUserList", "Fetched ${filtered_users.size} users")
                users.forEach { user ->
                    Log.d("UserFetch", "User: ${user.userName}, ID: ${user.userid}")
                }
            }.addOnFailureListener {
                Log.e("UserListDebug", "Error fetching users", it)
            }
    }

//    fun getUserById(uid: String, onResult: (UserModel?) -> Unit) {
//        db.collection("users").document(uid).get()
//            .addOnSuccessListener { doc ->
//                if (doc.exists()) {
//                    val user = doc.toObject(UserModel::class.java)
//                    onResult(user)
//                } else {
//                    onResult(null) // User not found
//                }
//            }
//            .addOnFailureListener {
//                onResult(null)
//            }



}