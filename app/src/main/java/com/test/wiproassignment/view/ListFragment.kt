@file:Suppress("DEPRECATION")

package com.test.wiproassignment.view


import android.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.test.wiproassignment.R
import com.test.wiproassignment.base.view.BaseFragment
import com.test.wiproassignment.databinding.FragmentListBinding
import com.test.wiproassignment.model.Item
import com.test.wiproassignment.model.List
import com.test.wiproassignment.viewmodel.ListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : BaseFragment<FragmentListBinding, ListFragmentViewModel>() {


    private val mListViewModel: ListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.fragment_list


    override val viewModel: ListFragmentViewModel
        get() = mListViewModel


    override fun initializeUI() {
        lvItems.adapter = ListItemsAdapter()

        //Observing list here
        mListViewModel.listData.observe(this, Observer {
            print(it.toString())
            (lvItems.adapter as ListItemsAdapter).updateList(it.rows)
            activity?.actionBar?.title = it.title
        })

        //Checking data loaded status
        mListViewModel.isLoaded.observe(this, Observer {
            if (it)
                pbList.visibility = View.GONE
            else
                pbList.visibility = View.VISIBLE
        })


        //Displaying toast initially when network not available
        mListViewModel.isNetwork.observe(this, Observer {
            if (!it){
                Toast.makeText(context,getString(R.string.network_not_available),Toast.LENGTH_SHORT).show()
            }
        })

        //Listeninng data refresh changes
        swipeRefresh.setOnRefreshListener {
            mListViewModel.fetchData()
            swipeRefresh.isRefreshing = false
        }


    }


    companion object {

        //Instance of ListFragment
        fun newInstance(): ListFragment = ListFragment()

        //TAG of fragment
        fun fragmentTag(): String = ListFragment::class.java.name

    }


}
