@file:Suppress("DEPRECATION")

package com.wipro.wiproassignment.view


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wipro.wiproassignment.R
import com.wipro.wiproassignment.base.view.BaseFragment
import com.wipro.wiproassignment.databinding.FragmentListBinding
import com.wipro.wiproassignment.utils.isNetworkAvailable
import com.wipro.wiproassignment.viewmodel.ListFragmentViewModel
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * Created by Girish Sahu on 2/26/2020.
 */
class ListFragment : BaseFragment<FragmentListBinding, ListFragmentViewModel>() {


    private val mListViewModel: ListFragmentViewModel by lazy {
        ViewModelProviders.of(this).get(ListFragmentViewModel::class.java)
    }

    override val layoutId: Int
        get() = R.layout.fragment_list


    override val viewModel: ListFragmentViewModel
        get() = mListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!mRotationStatus!!) {
            mListViewModel.fetchData()
        }

    }


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
            networkAvailabilityUpdate(it)
        })


        //Displaying toast initially when network not available
        mListViewModel.isNetwork.observe(this, Observer {
            if (!it) {
                Toast.makeText(
                    context,
                    getString(R.string.network_not_available),
                    Toast.LENGTH_SHORT
                ).show()
                pbList.visibility = View.GONE
            }
        })

        //Listeninng data refresh changes
        swipeRefresh.setOnRefreshListener {
            mListViewModel.fetchData()
            swipeRefresh.isRefreshing = false
        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerNetworkBroadcast()
    }

    private fun networkAvailabilityUpdate(isNetwork: Boolean) {
        if (isNetwork)
            pbList.visibility = View.GONE
        else
            pbList.visibility = View.VISIBLE
    }

    private fun registerNetworkBroadcast() {
        context?.registerReceiver(
            mBroadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    private fun unregisterNetworkChanges() {
        try {
            context?.unregisterReceiver(mBroadcastReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

    }

    private val mBroadcastReceiver: NetworkReceiver = NetworkReceiver()


    inner class NetworkReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action.equals(ConnectivityManager.CONNECTIVITY_ACTION, true)) {
                mListViewModel.checkNetwork()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChanges()
    }


    companion object {

        //Instance of ListFragment
        fun newInstance(): ListFragment = ListFragment()

        //TAG of fragment
        fun fragmentTag(): String = ListFragment::class.java.name

    }


}






