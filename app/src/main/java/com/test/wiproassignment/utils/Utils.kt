package com.test.wiproassignment.utils

import android.content.Context
import android.net.ConnectivityManager


const val BASE_URL: String = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
const val SCREEN_ROTATION: String = "SCREEN_ROTATION"




fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
}