package com.test.wiproassignment.base.viewmodel

import android.content.Context

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.test.wiproassignment.viewmodel.ListFragmentViewModel


/**
 * Created by Girish Sahu on 1/13/19.
 */

class ViewModelProviderFactory(private val mContext: Context) :
    ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ListFragmentViewModel::class.java!!)) {

            return ListFragmentViewModel() as T

        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}