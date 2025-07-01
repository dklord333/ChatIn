package com.example.chatin.Customs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.navigation.compose.currentBackStackEntryAsState

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController



data class NavItem(val route: String, val icon: ImageVector)

@Composable
fun navbar(navController: NavController){
    val items=listOf(NavItem("home", Icons.Default.Home),
        NavItem("profile", Icons.Default.Person),
        NavItem("settings", Icons.Default.Settings),
        NavItem("Chat", Icons.Default.MailOutline))
    val observeScreen by navController.currentBackStackEntryAsState()
    val currentroute=observeScreen?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                Color.Blue, RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.TopCenter
    ) {
//        (0xff20FFFFFF)
        Row(
            modifier = Modifier
                .background(Color.Transparent)
                .clip(RoundedCornerShape(30.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly

        ) {

            items.forEach { item ->
                val isSelected = currentroute == item.route

                IconButton(
                    onClick = {
                        if (item.route != currentroute) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = false
                                    saveState = false
                                }
                                popUpTo(item.route) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                                restoreState = false
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.route,
                        tint = if (isSelected) Color.Green else Color.Gray
                    )
                }
            }

        }


    }

}


//@Preview(showBackground = true)
//@Composable
//fun ppreview(){
//    navbar(navController = NavController(LocalContext.current))
//
//}
//
