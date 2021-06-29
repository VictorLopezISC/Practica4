package com.jrgames.practica3.ui.recyclers


import android.view.ViewGroup
import com.jrgames.practica3.R
import com.jrgames.practica3.domain.response.SpotifyObject
import com.jrgames.practica3.io.entities.Contactos
import com.jrgames.practica3.ui.base.BaseAdapter
import com.jrgames.practica3.ui.base.BaseViewHolder
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener

/**
 * 1.- Clase de tipo Recu}yclñerView.Adapter es un adaptador que usaremos para la lista tipo Spotify
 *
 * 2.-  Crear  lienzo
 *
 */
class ContactsAdapter(mLister: OnAdapterClickListener<Contactos>) : BaseAdapter<Contactos>(mLister) {

    override fun onBindViewHolder(holder: BaseViewHolder<Contactos>, position: Int) {
        val item  = mItems[position]
        holder.onBindItem(mItems, mListener, item, position)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder<Contactos> {
        val view = getViewFromLayout(p0, R.layout.item_contactos)// Sabemos a que lienzo va a puntar nuestro adapter
        return ContactsViewHolder(view!!)// Retornamos nuestro ViewHolder
    }
}