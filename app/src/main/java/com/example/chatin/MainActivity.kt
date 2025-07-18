package com.example.chatin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.example.chatin.Authentication.Login
import com.example.chatin.Chat
import com.example.chatin.ChatScreen
import com.example.chatin.ui.theme.ChatInTheme
import com.google.firebase.auth.FirebaseAuth


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import kotlin.math.log

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth=FirebaseAuth.getInstance()
        val currentUser=auth.currentUser
        enableEdgeToEdge()


        setContent {
            ChatInTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {innerpadding->
                    ChatList(chats = samplechat(), context = this, padding = innerpadding
                    )

                }
            }
        }
    }
}

@Composable
fun ChatList(
    chats: List<Chat>, context: Context,
        padding:PaddingValues= PaddingValues()


) {
    LazyColumn (contentPadding = padding,
        ){
        items(chats) { chat ->
            ChatListitem(chat, context)
        }
    }
}

private fun samplechat() = listOf(
    Chat("Xavier", "whats up?"),
    Chat("Alexa", "whats up?"),

    )

@Composable
fun ChatListitem(chat: Chat, context: Context) {


    Row(
        modifier = Modifier
            .fillMaxWidth().padding(vertical = 8.dp, horizontal = 6.dp)
            .clickable {
               val  intent=Intent(context,ChatScreen::class.java)
                context.startActivity(intent)


            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(48.dp)
            .background(Color.Blue, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = chat.name.first().toString(),
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = chat.name)
            //  Text(text = chat.lastMessage, color = Color.Gray)
        }

//        Box(modifier = Modifier
//            .size(22.dp)
//            .background(color = Color.Green, shape = CircleShape),
//            contentAlignment = Alignment.Center) {
//            Text(text = "1",
//                color = Color.White,
//                style = MaterialTheme.typography.bodyLarge)
//        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewChatList() {
    val context = LocalContext.current

    ChatList(chats = samplechat(), context = context, padding = PaddingValues())
}



