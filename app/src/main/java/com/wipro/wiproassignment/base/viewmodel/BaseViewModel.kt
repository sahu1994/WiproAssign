package com.wipro.wiproassignment.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Girish Sahu on 2/26/2020.
 */
abstract class BaseViewModel : ViewModel(){
    var isSaved = MutableLiveData<Boolean>()

    init {
        isSaved.value = false
    }

}