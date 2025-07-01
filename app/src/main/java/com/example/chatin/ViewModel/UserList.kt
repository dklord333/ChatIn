package com.example.chatin.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.chatin.ModelClass.UserModel
import com.example.chatin.Repository.UserRepository
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserList(private val currentUserId: String):ViewModel() {
    private val repository = UserRepository()

    private val _users = MutableStateFlow<List<UserModel>>(emptyList())
    val users: StateFlow<List<UserModel>> = _users


    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        repository.fetchuser(currentUserId) { userList ->
            _users.value = userList
        }
    }
}
