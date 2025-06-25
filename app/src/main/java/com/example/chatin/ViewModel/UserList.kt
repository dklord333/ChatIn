package com.example.chatin.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.chatin.ModelClass.UserModel
import com.example.chatin.Repository.UserRepository

class UserList:ViewModel() {
private val repository=UserRepository()
    val userlist by mutableStateOf<List<UserModel>>(emptyList())

    var addUserResult by mutableStateOf<String?>(null)
     init {
         
     }



}