package com.wipro.wiproassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.wipro.wiproassignment.base.viewmodel.BaseViewModel
import com.wipro.wiproassignment.model.List
import com.wipro.wiproassignment.repositories.ListRepository
import com.wipro.wiproassignment.utils.ListApp
import com.wipro.wiproassignment.utils.isNetworkAvailable
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

    fun checkNetwork() {
        isNetwork.value = isNetworkAvailable(ListApp.instance)
    }

}