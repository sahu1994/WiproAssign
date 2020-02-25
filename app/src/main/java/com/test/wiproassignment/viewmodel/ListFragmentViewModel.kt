package com.test.wiproassignment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.test.wiproassignment.base.viewmodel.BaseViewModel
import com.test.wiproassignment.model.List
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


    //Fetching information from Cloud
     fun fetchData() {
        isLoaded.value = false
        if (isNetworkAvailable(ListApp.instance)) {
            isNetwork.value = true
            ListApp.instance.provideRetrofitInterface().getListItems()
                .enqueue(object : Callback<List> {
                    override fun onResponse(
                        call: Call<List>,
                        response: Response<List>
                    ) {
                        listData.value = response.body() as List
                        isLoaded.value = true

                    }

                    override fun onFailure(call: Call<List>?, t: Throwable?) {
                        //
                        isLoaded.value = true
                    }
                })
        }else{
            isNetwork.value = false
        }

    }


}