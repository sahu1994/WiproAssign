package com.test.wiproassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.test.wiproassignment.base.viewmodel.BaseViewModel
import com.test.wiproassignment.model.List
import com.test.wiproassignment.repositories.ListRepository
import com.test.wiproassignment.utils.ListApp
import com.test.wiproassignment.utils.isNetworkAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Girish Sahu on 2/26/2020.
 */
class ListFragmentViewModel : BaseViewModel() {

    var listData = MutableLiveData<List>()
    var isLoaded = MutableLiveData<Boolean>()
    var isNetwork = MutableLiveData<Boolean>()


    init {
        fetchData()
    }

    fun fetchData() {
        listData = ListRepository.instance.fetchData()
        isLoaded = ListRepository.instance.isLoaded
        isNetwork = ListRepository.instance.isNetwork
    }

}