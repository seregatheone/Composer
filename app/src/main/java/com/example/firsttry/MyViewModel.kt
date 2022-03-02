package com.example.firsttry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel: ViewModel(){
    private val _count : MutableLiveData<Int> = MutableLiveData()
    val count : LiveData<Int> = _count

    //check ViewModel work
    fun releaseNewInteger(integer: Int){
        _count.value = integer
    }

}