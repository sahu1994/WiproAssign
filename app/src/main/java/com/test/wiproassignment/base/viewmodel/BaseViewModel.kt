package com.test.wiproassignment.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){
    var isSaved = MutableLiveData<Boolean>()

    init {
        isSaved.value = false
    }

}