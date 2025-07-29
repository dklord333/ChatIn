package com.example.chatin.Repository

import android.util.Log
import com.example.chatin.ModelClass.MessageData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MessageRepo() {
    private val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

    private fun generateChatId(senderId: String, receiverId: String): String {
        return if (senderId < receiverId)
            "${senderId}_${receiverId}"
        else
            "${receiverId}_${senderId}"
    }
        fun sendMessage(receiverId: String, message: MessageData) {
            val senderId = currentUserId ?: return
            val chatId = generateChatId(senderId, receiverId)

            FirebaseFirestore.getInstance().collection("messages")
                .document(chatId)
                .collection("chat")
                .add(message)
                .addOnSuccessListener {
                    Log.d("Message", "Message sent successfully")
                }
                .addOnFailureListener { e ->
                    Log.d("Message", "Error sending message", e)
                }
        }

        fun receiveMessages(receiverId: String, onChange: (List<MessageData>) -> Unit) {
            val senderId = currentUserId ?: return
            val chatId = generateChatId(senderId, receiverId)

            FirebaseFirestore.getInstance().collection("messages")
                .document(chatId)
                .collection("chat")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener { snapshot, _ ->
                    if (snapshot != null) {
                        val messages = snapshot.documents.mapNotNull {
                            it.toObject(MessageData::class.java)
                        }
                        onChange(messages)
                    }
                }
        }
    }