package com.wipro.wiproassignment.network

import com.wipro.wiproassignment.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {

    private object HOLDER {
        val INSTANCE = RestClient()
    }

    companion object {

        val instance: RestClient by lazy { HOLDER.INSTANCE }


    }

    fun provideRetrofitInterface(): RestAPI = synchronized(this) {
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