package com.example.chatin.subclases

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomAppBarDefaults.ContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.chatin.R
import com.example.chatin.ViewModel.PositionVM
import com.example.chatin.ui.theme.ChatInTheme
import com.example.chatin.Customs.AttachPopup as AttachPopup

@Composable
fun chat ( viewModel: PositionVM
) {
    var showpopup by remember {
        mutableStateOf(false)
    }
    var buttonoffset by remember {
        mutableStateOf(Offset.Zero)
    }

    val context= LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    var text by remember {
        mutableStateOf( "")
    }

    Column (modifier = Modifier
    .fillMaxSize(),

    verticalArrangement = Arrangement.Bottom


    ){

        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .background(Color.LightGray, RoundedCornerShape(16.dp))
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .border(2.dp, Black, RoundedCornerShape(16.dp))
                .fillMaxWidth()


        ) {
            TextField( modifier= Modifier.width(width = screenWidth*0.8f),
                value = text, onValueChange = { newtext ->
                    text = newtext
                },
                maxLines = 3

            )
            Row (
                modifier= Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically

            ){

                Icon(
                    painter = painterResource(id = R.drawable.attachment),
                    contentDescription = "send",
                    modifier = Modifier
                        .size(38.dp)
                        .onGloballyPositioned { LayoutCoordinates ->
                            var position = LayoutCoordinates.positionInRoot()
                            viewModel.buttonOffset.value=position

                        }
                        .clickable { showpopup = true })
                AttachPopup(
                    modifier = Modifier,
                    Showpopup =showpopup ,
                    onDismiss = {showpopup=false },
                    viewModel = viewModel,
                    onfile = { Toast.makeText(context,"cameraclicked", Toast.LENGTH_SHORT).show()

                    },
                    oncamera = { Toast.makeText(context,"cameraclicked", Toast.LENGTH_SHORT).show()

                    })
                Icon(
                    painter = painterResource(id = R.drawable.sendicon),
                    contentDescription = "send",
                    modifier = Modifier.size(44.dp)
                )

            }


            }}}
