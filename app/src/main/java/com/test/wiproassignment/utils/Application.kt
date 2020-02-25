package com.test.wiproassignment.utils

import android.app.Application
import com.test.wiproassignment.network.RestAPI
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


    fun provideRetrofitInterface(): RestAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
        return retrofit.create(RestAPI::class.java)
    }

    private fun getClient(): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        okHttpClient.connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .retryOnConnectionFailure(false)

        return okHttpClient.build()
    }


}