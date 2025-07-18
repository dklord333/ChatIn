package com.example.chatin
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.chatin.Customs.navbar
import com.example.chatin.ViewModel.PositionVM
import com.example.chatin.subclases.chat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.chatin.ViewModel.UserList as UserList


class Home : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val auth= FirebaseAuth.getInstance().currentUser?.uid
        super.onCreate(savedInstanceState)
        var vm=PositionVM()
        var UVM=UserList(auth.toString())
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
                        composable("profile") {

                            UserListScreen(viewModel = UVM,navController) }
                        composable("settings") { SettingsScreen()}
                            composable("Chat/{userId}/{userName}") { backStackEntry ->
                                val userId=backStackEntry.arguments?.getString("userId")?:""
                                val userName=backStackEntry.arguments?.getString("userName")?:""
                                chat(viewModel = vm,userid=userId,username=userName)
                            }

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
@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun UserListScreen(viewModel: UserList,navController: NavController) {

    var selectedUser by remember { mutableStateOf<Pair<String, String>?>(null)  }
    if (selectedUser==null){
        val users by viewModel.users.collectAsState()

    if (users.isEmpty()) {
        Text("No users found", color = Color.Red)
    } else {
        LazyColumn {
            items(users) { user ->
                val userid=user.userid
                val username=user.userName
                var vm=PositionVM()
                Box(
                    modifier = Modifier.fillMaxWidth().padding(8.dp).background(Color.Blue,
                        RectangleShape).clickable {  /*"Chat/${user.userid}/${user.userName}"*/

                        selectedUser = user.userid to username

                        }
                ) {
                    Text(text = user.userName, fontSize = 20.sp, color = Color.White)
                }
            }

    }
}}
else{
     val(userid,username)=selectedUser!!
    chat(viewModel = PositionVM(),userid,username)
}
}



