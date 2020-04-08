package com.suika.koindemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(val name:String) : ViewModel() {
    val someName = MutableLiveData<String>()

    init {
        someName.postValue("sss")
    }
}