package com.example.nwaster.ui.Lists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "ADIOS"
    }
    val text: LiveData<String> = _text
}