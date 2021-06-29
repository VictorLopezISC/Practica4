package com.jrgames.practica3.ui.recyclers


import android.view.ViewGroup
import com.jrgames.practica3.R
import com.jrgames.practica3.domain.response.SpotifyObject
import com.jrgames.practica3.ui.base.BaseAdapter
import com.jrgames.practica3.ui.base.BaseViewHolder
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener

/**
 * 1.- Clase de tipo Recu}yclñerView.Adapter es un adaptador que usaremos para la lista tipo Spotify
 *
 * 2.-  Crear  lienzo
 *
 */
class SpotifyAdapter(mLister: OnAdapterClickListener<SpotifyObject>) : BaseAdapter<SpotifyObject>(mLister) {

    override fun onBindViewHolder(holder: BaseViewHolder<SpotifyObject>, position: Int) {
        val item  = mItems[position]
        holder.onBindItem(mItems, mListener, item, position)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder<SpotifyObject> {
        val view = getViewFromLayout(p0, R.layout.item_spotify)
        return SpotifyViewHolder(view!!)// Retornamos nuestro ViewHolder
    }
}