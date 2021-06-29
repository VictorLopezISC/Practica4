package com.jrgames.practica3.ui.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jrgames.practica3.domain.response.SpotifyObject
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected var itemSpotify: T? = null
    protected var mItemList: MutableList<T>? = null
    protected var mListener: OnAdapterClickListener<T>? = null
    protected var mContext: Context = itemView.context
    protected var mPosition: Int = 0

    fun onBindItem(itemList: MutableList<T>, listener: OnAdapterClickListener<T>, item: T?, position: Int ) {
         itemSpotify = item
         mListener = listener
         mItemList = itemList
         mPosition = position
         fillViewHolder()
     }

    protected abstract fun fillViewHolder()

    init {
        mContext = itemView.context
    }
}
