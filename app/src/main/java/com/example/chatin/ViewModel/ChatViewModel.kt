package com.example.chatin.ViewModel

import androidx.lifecycle.ViewModel
import com.example.chatin.ModelClass.MessageData
import com.example.chatin.Repository.MessageRepo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel:ViewModel() {
    private val messageRepo = MessageRepo()
    private val _messages = MutableStateFlow<List<MessageData>>(emptyList())
    val messages: StateFlow<List<MessageData>> = _messages

    fun startListening(receiverId: String) {
        messageRepo.receiveMessages(receiverId) {
            _messages.value = it
        }
    }

    fun sendMessage(receiverId: String, text: String) {
        val senderId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val message = MessageData(senderId, receiverId, text, System.currentTimeMillis())
        messageRepo.sendMessage(receiverId, message)
    }
}