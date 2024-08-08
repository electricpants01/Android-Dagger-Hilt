package com.locotoinnovations.composeviewpager

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel: ViewModel() {

    private val _myData = MutableStateFlow("")
    val myData: Flow<String> = _myData


    fun getData() {

    }

}