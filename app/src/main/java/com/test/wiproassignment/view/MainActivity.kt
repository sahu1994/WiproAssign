@file:Suppress("DEPRECATION")

package com.test.wiproassignment.view

import androidx.lifecycle.ViewModelProviders
import com.test.wiproassignment.R
import com.test.wiproassignment.base.view.BaseActivity
import com.test.wiproassignment.databinding.ActivityMainBinding
import com.test.wiproassignment.viewmodel.MainActivityViewModel

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
        addFragment(ListFragment.newInstance(), ListFragment.fragmentTag())
    }


}
