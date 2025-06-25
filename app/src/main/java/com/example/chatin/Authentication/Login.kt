package com.example.chatin.Authentication

import AuthViewModel
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter

import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.chatin.Animation.Bordeanimation
import com.example.chatin.Animation.BoxBordeanimation
import com.example.chatin.Authentication.ui.theme.ChatInTheme
import com.example.chatin.Home
import com.example.chatin.MainActivity
import com.example.chatin.ModelClass.UserModel
import com.example.chatin.OnboardingScreen
import com.example.chatin.R
import com.example.chatin.ViewModel.CurrentAuth
import com.google.firebase.messaging.FirebaseMessagingService

import java.nio.file.WatchEvent

import androidx.compose.material3.Text as Text1

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {




                ChatInTheme {
                    Box(modifier = Modifier.fillMaxSize()) {
                        // Background Image
                        Image(
                            painter = painterResource(id = R.drawable.loginback),
                            contentDescription = "login background",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.fillMaxSize()
                        )

                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            containerColor = Color.Transparent
                        ) { innerPadding ->

                            Greeting(
                                name = "Android",
                                modifier = Modifier.padding(innerPadding),
                                viewModel = CurrentAuth(),
                                context = this@Login

                            )
                        }

                }
            }
        }
    }

    @Composable
    fun Greeting(
        name: String,
        modifier: Modifier = Modifier,
        viewModel: CurrentAuth,
        context: Context
    ) {
        val gradient = Brush.verticalGradient(colors = listOf(Color(0xFFDA3068), Color(0xFF14469F)))

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Text1(
                text = "App",
                textAlign = TextAlign.Center,


                style = TextStyle(
                    color = Color.Black,
                    fontSize = 44.sp
                ),
                modifier = modifier.padding(top = 20.dp, bottom = 20.dp),
            )
            Frag(name = "android", viewModel = viewModel, context = context)


        }
    }

    @Composable

    fun Frag(
        name: String,
        modifier: Modifier = Modifier,
        viewModel: CurrentAuth,
        context: Context
    ) {
        val infiniteTransition = rememberInfiniteTransition()

        val animatedOffset by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 5f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            )
        )

        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(height = screenHeight * 0.7f, width = screenWidth * 0.88f)
                .drawBehind {
                    val strokeWidth = 6.dp.toPx()
                    val cornerRadiusPx = 24.dp.toPx()

                    // Static Blue Border
                    drawRoundRect(
                        color = Color(0xFF14469F),
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadiusPx),
                        style = Stroke(width = strokeWidth)
                    )

                    // Animated Pink shimmer (over border only)
                    val shimmerBrush = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xFFDA3068),
                            Color.Transparent
                        ),
                        start = Offset(
                            x = size.width * animatedOffset - size.width,
                            y = 0f
                        ),
                        end = Offset(
                            x = size.width * animatedOffset,
                            y = size.height
                        )
                    )

                    drawRoundRect(
                        color = Color.White,
                        topLeft = Offset.Zero,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadiusPx),
                        style = Stroke(width = strokeWidth)
                    )
                }
                .background(Color.White, shape = RoundedCornerShape(24.dp))
        ) {
            if (viewModel.Login.value) {
                Loginfun(modifier = Modifier, viewModel = viewModel, context = context)
            } else {
                Signup(viewModel = viewModel, context = context)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Loginfun(
        modifier: Modifier = Modifier,
        viewModel: CurrentAuth,
        context: Context,
        authViewModel: AuthViewModel = AuthViewModel()
    ) {
        val config = LocalConfiguration.current
        val ScreenWidth = config.screenWidthDp.dp
        val ScreenHeight = config.screenHeightDp.dp


        val gradient = Brush.verticalGradient(colors = listOf(Color(0xFFDA3068), Color(0xFF14469F)))
        var textmail by remember { mutableStateOf("") }
        var textpass by remember { mutableStateOf("") }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text1(
                text = "Login",
                textAlign = TextAlign.Center,


                style = TextStyle(
                    color = Color.Black,
                    fontSize = 44.sp
                ),
                modifier = modifier.padding(top = 10.dp),
            )
            TextField(

                value = textmail,
                onValueChange = { textmail = it },

                label = {
                    Text1("Email",)
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(Color(0xFFa3a19e), RoundedCornerShape(16.dp),).border(
                        color = Color(0xFFe9e9e9),
                        width = 0.7.dp,
                        shape = RoundedCornerShape(16.dp)
                    )

                    .clip(RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,

                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White,


                    )

            )

            TextField(
                value = textpass,
                onValueChange = { textpass = it },
                label = {
                    Text1("Pasword")
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(Color(0xFFa3a19e), RoundedCornerShape(16.dp),).border(
                        color = Color(0xFFe9e9e9),
                        width = 0.7.dp,
                        shape = RoundedCornerShape(16.dp)
                    )

                    .clip(RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White


                )


            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text1(text = "Already have an account?")


                Text1(
                    text = "Login",
                    modifier = Modifier.clickable {
                        viewModel.Login.value = !viewModel.Login.value
                    })
            }



            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .background(Color(0xFFF8BB3D), RoundedCornerShape(24.dp))

                        .clip(RoundedCornerShape(24.dp))
                        .width(ScreenWidth * 0.67f)
                        .height(ScreenHeight * 0.08f).clickable {
                            authViewModel.login(
                                email = textmail,
                                password = textpass
                            ) { success, error ->
                                if (success) {

                                    context.startActivity(Intent(context, Home::class.java))
                                } else {
                                    Toast.makeText(
                                        context,
                                        error ?: "Login failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        },


                    contentAlignment = Alignment.Center,


                    ) {
                    Row {
                        Text1(
                            text = "Login",
                            textAlign = TextAlign.Center,


                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 22.sp
                            ),
                            modifier = modifier.padding(top = 20.dp, bottom = 20.dp),
                        )
                    }


                }
            }


        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Signup(
        modifier: Modifier = Modifier,
        viewModel: CurrentAuth,
        authViewModel: AuthViewModel = AuthViewModel(),
        context: Context
    ) {
        val config = LocalConfiguration.current
        val ScreenWidth = config.screenWidthDp.dp
        val ScreenHeight = config.screenHeightDp.dp


        val gradient = Brush.verticalGradient(colors = listOf(Color(0xFFDA3068), Color(0xFF14469F)))
        var textmail by remember { mutableStateOf("") }
        var textpass by remember { mutableStateOf("") }
        var textname by remember { mutableStateOf("") }
        var textphone by remember { mutableStateOf("") }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text1(
                text = "Sign Up",
                textAlign = TextAlign.Center,


                style = TextStyle(
                    color = Color.Black,
                    fontSize = 44.sp
                ),
                modifier = modifier.padding(top = 10.dp),
            )
            TextField(

                value = textname,
                onValueChange = { textname = it },

                label = {
                    Text1("Name",)
                },
                modifier = Modifier
                    .padding(bottom = 20.dp, top = 50.dp)
                    .background(Color(0xFFa3a19e), RoundedCornerShape(16.dp),).border(
                        color = Color(0xFFe9e9e9),
                        width = 0.7.dp,
                        shape = RoundedCornerShape(16.dp)
                    )

                    .clip(RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,

                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White,

                    )

            )

            TextField(
                value = textphone,
                onValueChange = { textphone = it },
                label = {
                    Text1("phone number")
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(Color(0xFFa3a19e), RoundedCornerShape(16.dp),).border(
                        color = Color(0xFFe9e9e9),
                        width = 0.7.dp,
                        shape = RoundedCornerShape(16.dp)
                    )

                    .clip(RoundedCornerShape(16.dp)),

                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White

                )

            )
            TextField(

                value = textmail,
                onValueChange = { textmail = it },
                label = {
                    Text1("Email")
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(Color(0xFFa3a19e), RoundedCornerShape(16.dp),).border(
                        color = Color(0xFFe9e9e9),
                        width = 0.7.dp,
                        shape = RoundedCornerShape(16.dp)
                    )

                    .clip(RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White

                )

            )
            TextField(
                value = textpass,
                onValueChange = { textpass = it },
                label = {
                    Text1("Password")
                },
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .background(Color(0xFFa3a19e), RoundedCornerShape(16.dp),).border(
                        color = Color(0xFFe9e9e9),
                        width = 0.7.dp,
                        shape = RoundedCornerShape(16.dp)
                    )

                    .clip(RoundedCornerShape(16.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.White


                )

            )




            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text1(text = "Dont have an account?")


                Text1(
                    text = "Signup",
                    modifier = Modifier.clickable {
                        viewModel.Login.value = !viewModel.Login.value
                    })
            }



            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .background(Color(0xFFF8BB3D), RoundedCornerShape(24.dp))

                        .clip(RoundedCornerShape(24.dp))
                        .width(ScreenWidth * 0.67f)
                        .height(ScreenHeight * 0.08f).clickable {
                            val credentials = UserModel(
                                email = textmail,
                                password = textpass,
                                name = textname,
                                phNo = textphone,
                                id = ""
                            )

                            authViewModel.register(credentials) { success, error ->
                                if (success) {
                                    Toast.makeText(context, "User Created", Toast.LENGTH_SHORT)
                                        .show()
                                } else {
                                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                                }
                            }
                        },

                    contentAlignment = Alignment.Center


                ) {
                    Row {
                        Text1(
                            text = "Signup",
                            textAlign = TextAlign.Center,


                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 22.sp
                            ),
                            modifier = modifier.padding(top = 20.dp, bottom = 20.dp),
                        )
                    }


                }
            }


        }
    }

}

//
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview2() {
//    ChatInTheme {
//        Signup(modifier = Modifier, viewModel = CurrentAuth(), context = LocalContext.current)
//
//
//    }
//}}
