package com.wipro.wiproassignment.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import com.test.wiproassignment.R
import com.wipro.wiproassignment.base.viewmodel.BaseViewModel
import com.wipro.wiproassignment.base.viewmodel.ViewModelProviderFactory
import com.wipro.wiproassignment.utils.SCREEN_ROTATION

/**
 * Created by Girish Sahu on 2/26/2020.
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : FragmentActivity() {

    var mRotationStatus: Boolean? = false
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
            mRotationStatus = savedInstanceState.getBoolean(SCREEN_ROTATION)
        }
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        mViewModelProviderFactory = ViewModelProviderFactory(this)
        performDataBinding()
        init()


    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(SCREEN_ROTATION, true)
        super.onSaveInstanceState(outState)
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
                .add(R.id.container, fragment, tag)
                .commit()
        }
    }


}