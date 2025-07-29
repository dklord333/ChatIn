package com.example.chatin.subclases

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatin.R
import com.example.chatin.ViewModel.ChatViewModel
import com.example.chatin.ViewModel.PositionVM
import com.google.firebase.auth.FirebaseAuth
import com.example.chatin.Customs.AttachPopup as AttachPopup

@Composable
fun chat ( viewModel: PositionVM ,userid:String,username:String,receiverid:String,ChatVM:ChatViewModel
) {
    val messages by ChatVM.messages.collectAsState()
val name=username
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
    LaunchedEffect(Unit) {
        ChatVM.startListening(receiverid)
    }

    Column (


    ){
        val config = LocalConfiguration.current
        val ScreenWidth = config.screenWidthDp.dp
        val ScreenHeight = config.screenHeightDp.dp
Row (
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .fillMaxWidth()
        .height(36.dp)
        .background(Color.Blue)


){
    Text(text = name, color = Color.White, fontSize = 20.sp)

}
Column(
    modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
) {
   LazyColumn {
       items(messages){
           msg->
          val curuserId=FirebaseAuth.getInstance().currentUser?.uid
           val is_sender=msg.senderId==FirebaseAuth.getInstance().currentUser?.uid
           Log.d("SenderId", "Sender: $curuserId")
           if(msg.senderId == curuserId){
               sendmessage(msg.message)
           }else{
               Receivemessage(msg.message)

           }

       }
   }
}


        Row(
            verticalAlignment = Alignment.CenterVertically,

            modifier = Modifier
                .background(Color.White, RoundedCornerShape(16.dp))
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .border(2.dp, Black, RoundedCornerShape(16.dp))
                .fillMaxWidth()


        ) {
            TextField( modifier= Modifier
                .width(ScreenWidth * 0.75f)
                .background(Color.White),
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
                            viewModel.buttonOffset.value = position

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
                    modifier = Modifier.size(44.dp).clickable { if (text.isNotBlank()) {
                        ChatVM.sendMessage(receiverid, text)
                        text = ""
                    } }
                )

            }


            }}}
@Composable
fun sendmessage(text:String,){

    Row (
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        Arrangement.End
    ){
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .background(
                    Color(0xFFFF9800),
                    RoundedCornerShape(8.dp)
                )
        ) {



            Text(
                text = "   $text ",

                fontSize = 20.sp

            )


        }
    }
}
@Composable
fun Receivemessage(text:String,){

    Row (
        modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
        Arrangement.Start
    ){
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 2.dp)
                .wrapContentHeight()
                .background(
                    Color(0xFFFAB01D),
                    RoundedCornerShape(8.dp)
                )
        ) {


            Text(
                text ="   $text ",

                fontSize = 20.sp

            )


        }
    }
}
@Preview(showBackground = true)
//@Composable
//fun previewchat() {
//    val Username="Hai"
//    val Userid="Hai"
//    val rec="sd"
//    val cvm=ChatViewModel()
//     val vm=PositionVM()
//chat(viewModel = vm,Userid,Username,rec,cvm)
//
//}
@Preview(showBackground = true)
@Composable
fun prev(){
    sendmessage("ali ...where r u")
}