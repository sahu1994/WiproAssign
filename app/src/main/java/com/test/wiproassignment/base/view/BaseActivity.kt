package com.test.wiproassignment.base.view

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import com.test.wiproassignment.base.viewmodel.BaseViewModel
import com.test.wiproassignment.base.viewmodel.ViewModelProviderFactory
import com.test.wiproassignment.utils.SCREEN_ROTATION


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : FragmentActivity() {

    var rotation: Boolean? = false
    private var viewDataBinding: T? = null
    private var mViewModel: V? = null

    private lateinit var mViewModelProviderFactory: ViewModelProviderFactory


    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V


    /**
     * Initialize view in here
     */
    abstract fun init()


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            rotation = savedInstanceState.getBoolean(SCREEN_ROTATION)
        }
        setTheme(com.test.wiproassignment.R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mViewModelProviderFactory = ViewModelProviderFactory(this)
        performDataBinding()
        init()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(SCREEN_ROTATION, true)
        rotation = true
        Log.d("Screen rotation", "onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        Log.d("Screen rotation", "onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }


    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.executePendingBindings()
        viewDataBinding!!.lifecycleOwner = this
    }

    fun addFragment(fragment: Fragment, tag: String) {
        if (!fragment.isVisible && fragment !is ListFragment) {
            supportFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .add(com.test.wiproassignment.R.id.container, fragment, tag)
                .commit()
        }
    }


}