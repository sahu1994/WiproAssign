package com.test.wiproassignment.network

import com.test.wiproassignment.model.List
import retrofit2.Call
import retrofit2.http.GET


/**
 * Created by Girish Sahu on 2/26/2020.
 */
interface RestAPI {

    /**
     * Get the list of the Items from the API
     */
    @GET("facts.json")
    fun getListItems(): Call<List>

}