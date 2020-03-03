package com.wipro.wiproassignment.repositories

import androidx.lifecycle.MutableLiveData
import com.wipro.wiproassignment.model.List
import com.wipro.wiproassignment.utils.ListApp
import com.wipro.wiproassignment.utils.isNetworkAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListRepository private constructor() {


    var listData = MutableLiveData<List>()
    var isLoaded = MutableLiveData<Boolean>()
    var isNetwork = MutableLiveData<Boolean>()


    private object HOLDER {
        val INSTANCE = ListRepository()
    }

    companion object {
        val instance: ListRepository by lazy { HOLDER.INSTANCE }
    }


    //Fetching information from Cloud
    fun fetchData(): MutableLiveData<List> {
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
        } else {
            isNetwork.value = false
        }
        return listData
    }


}