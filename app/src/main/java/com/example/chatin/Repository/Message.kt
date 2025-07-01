package com.example.chatin.Repository

import androidx.compose.runtime.snapshots.Snapshot
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class Message {
    val auth=FirebaseAuth.getInstance().currentUser?.uid
    val senderid=auth
    val receiverid=""
    val chatid=""
    suspend fun SendMessage(Chatid:String, message:Message){
        FirebaseFirestore.getInstance().collection("chats").document(Chatid).collection("messages").add(message).await()

    }
    fun receiveMessage(Chatid: String,onChange:(List<Message>)->Unit){
        FirebaseFirestore.getInstance().collection("chats").orderBy("timestamp",Query.Direction.ASCENDING).addSnapshotListener{
            snapshot,_->if(snapshot!=null){
                val message=snapshot.documents.mapNotNull {
                    it.toObject(Message::class.java)
                }
            onChange(message)



            }
        }
    }
}