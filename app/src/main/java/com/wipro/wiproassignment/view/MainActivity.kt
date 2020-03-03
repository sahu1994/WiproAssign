@file:Suppress("DEPRECATION")

package com.wipro.wiproassignment.view

import androidx.lifecycle.ViewModelProviders
import com.test.wiproassignment.R
import com.test.wiproassignment.databinding.ActivityMainBinding
import com.wipro.wiproassignment.base.view.BaseActivity
import com.wipro.wiproassignment.viewmodel.MainActivityViewModel


/**
 * Created by Girish Sahu on 2/26/2020.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {


    private val mMainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }


    override val layoutId: Int
        get() = R.layout.activity_main


    override val viewModel: MainActivityViewModel
        get() = mMainActivityViewModel


    override fun init() {
        setActionBar(findViewById(R.id.toolbar))
        if (!mRotationStatus!!)
            addFragment(ListFragment.newInstance(), ListFragment.fragmentTag())
    }


}
