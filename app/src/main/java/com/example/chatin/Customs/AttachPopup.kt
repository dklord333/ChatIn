package com.example.chatin.Customs

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.chatin.R
import com.example.chatin.ViewModel.PositionVM

@Composable
public fun AttachPopup(
    modifier: Modifier,
    Showpopup:Boolean,
    onDismiss:()->Unit,
    oncamera: ()->Unit,
    onfile:()->Unit,
    viewModel: PositionVM
) {

    val density = androidx.compose.ui.platform.LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = with(density){configuration.screenHeightDp.dp.toPx()}
    val popupHeight = with(density) { 60.dp.toPx() }
    val offset = with(density) {
        IntOffset(
            viewModel.buttonOffset.value.x.toInt(),
            (viewModel.buttonOffset.value.y - 40.dp.toPx()).toInt()
        )
    }
    val offsetX = with(density) { viewModel.buttonOffset.value.x.toDp().roundToPx() }
    if (Showpopup) {
        val offsetY = if(viewModel.buttonOffset.value.y>popupHeight-100f){
           ( viewModel.buttonOffset.value.y-popupHeight-60f).toInt()
        }
        else{
            ( viewModel.buttonOffset.value.y+60f).toInt()
        }
        Popup(
            offset = offset,
                    onDismissRequest = onDismiss
        )



        {
            Box(
                modifier = Modifier
                    .background(Color.Cyan, RoundedCornerShape(28.dp))
                    .clickable { /* absorbs click to prevent dismiss on internal click */ }
            ) {
                Row(
                    modifier = Modifier.padding(12.dp)
                ) {
                    IconButton(onClick = {
                        onDismiss()
                        oncamera()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.camera),
                            contentDescription = "Camera",
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    IconButton(onClick = {
                        onDismiss()
                        onfile()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.file),
                            contentDescription = "File",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun preview(){
    AttachPopup(
        modifier = Modifier,
        Showpopup = true,
        onDismiss = { /* Do nothing for preview */ },
        oncamera = { println("Camera clicked") },
        onfile = { println("File clicked") },
        viewModel = PositionVM()
    )

}