package com.example.chatin.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel

class PositionVM:ViewModel() {
    var buttonOffset= mutableStateOf<Offset>(Offset(0f,0f))
}