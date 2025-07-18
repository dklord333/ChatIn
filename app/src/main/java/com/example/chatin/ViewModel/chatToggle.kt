package com.example.chatin.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class chatToggler : ViewModel() {
    val chatToggled = mutableStateOf(false)
}