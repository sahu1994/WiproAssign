package com.wipro.wiproassignment.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.squareup.picasso.Picasso
import com.wipro.wiproassignment.R
import com.wipro.wiproassignment.databinding.ItemInfoBinding
import com.wipro.wiproassignment.model.Item

/**
 * Created by Girish Sahu on 2/26/2020.
 */
class ListItemsAdapter : BaseAdapter() {

    private var mList = mutableListOf<Item>()

    override fun getCount(): Int {
        return mList.size
    }


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        run {
            val holder: ItemViewHolder

            val itemBinding = DataBindingUtil.inflate<ItemInfoBinding>(
                LayoutInflater.from(parent?.context),
                (R.layout.item_info),
                parent,
                false
            )

            holder = ItemViewHolder(itemBinding)
            holder.view = itemBinding.root
            holder.view.tag = holder
            Picasso.get().load(mList[position].imageHref)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .fit()
                .into(holder.binding.ivItemPhoto)
            holder.binding.ivItemPhoto
            holder.binding.model = mList[position]

            return holder.view
        }
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    private class ItemViewHolder internal constructor(val binding: ItemInfoBinding) {
        var view: View = binding.root

    }

    fun updateList(list: List<Item>) {
        mList = list as MutableList<Item>
        notifyDataSetChanged()
    }


}