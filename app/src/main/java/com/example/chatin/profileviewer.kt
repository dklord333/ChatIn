package com.example.pictureviewer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.navigation.composable
import com.example.chatin.Customs.navbar

@Composable

fun profilviewer(){
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            navbar(navController)
        }, containerColor = Color.Transparent
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("PictureViewer") { ProfileScreen() }
            composable("settings") { SettingsScreen() }
        }
    }
}
    @Composable
    fun HomeScreen(){
         Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Home Screen", color = Color.White)
        }
    }
    @Composable
    fun ProfileScreen() {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Profile", color = Color.White)
        }
    }
    @Composable
    fun SettingsScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Notification", color = Color.White)
        }
    }
@Composable
fun PictureVewer(imageList: List<Int>, modifier: Modifier = Modifier) {
    var selectedImage by remember { mutableStateOf(imageList.firstOrNull()) }

    Column(modifier = modifier.fillMaxSize()) {
        selectedImage?.let { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(imageList) { imageRes ->
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = "Thumbnail",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = if (selectedImage == imageRes) 2.dp else 1.dp,
                            color = if (selectedImage == imageRes) Color.Black else Color.Gray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            selectedImage = imageRes
                        }
                )
            }
        }
    }
}
