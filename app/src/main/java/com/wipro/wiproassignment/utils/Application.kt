package com.wipro.wiproassignment.utils

import android.app.Application
import com.wipro.wiproassignment.network.RestAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Girish Sahu on 2/26/2020.
 */
class ListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: ListApp
            private set
    }


}