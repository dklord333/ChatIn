package com.example.chatin
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.chatin.Customs.navbar
import com.example.chatin.ViewModel.PositionVM
import com.example.chatin.subclases.chat


class Home : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var vm=PositionVM()
        setContent {
            val navController = rememberNavController()
            var showOnboarding by remember { mutableStateOf(true) }

            if (showOnboarding) {
                OnboardingScreen {
                    showOnboarding = false
                }
            } else {
            MaterialTheme {


                Scaffold(
                    topBar = { navbar(navController) },  
                    containerColor = Color.White // Changed from Transparent
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",

                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") { HomeScreen() } // Using the HomeScreen composable
                        composable("profile") { ProfileScreen() }
                        composable("settings") { SettingsScreen()}
                            composable("Chat") { chat(viewModel = vm) }

                    }
                }
            }}
        }
    }
}


@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile", color = Color.Red)
    }
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings", color = Color.Black)
    }
}


