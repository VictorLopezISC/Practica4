package com.jrgames.practica3.ui.recyclers

import android.view.View
import androidx.core.content.ContextCompat
import com.jrgames.practica3.domain.response.SpotifyObject
import com.jrgames.practica3.io.entities.Contactos
import com.jrgames.practica3.ui.base.BaseViewHolder
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener
import kotlinx.android.synthetic.main.item_contactos.view.*
import kotlinx.android.synthetic.main.item_spotify.view.*

/**
 * Clase de tipo viewholder necesaria para nuestro Adaptador de el recyclerView
 */
class ContactsViewHolder(itemView: View): BaseViewHolder<Contactos>(itemView), OnAdapterClickListener<Contactos> {

    /**
     * Seteamos los datos proporcionados por el adapter
     */
    override fun fillViewHolder() {

        //comenzamos a setear los datos
       itemView.tvContacto.text = "${itemSpotify!!.nombre} ${itemSpotify!!.aPaterno} ${itemSpotify!!.aMaterno}"
       itemView.tvPhone.text = itemSpotify!!.telefono

       //Implementamos el click en nuestro adaptador
        itemView.ivEdit.setOnClickListener {
            mListener!!.onClickItem(itemSpotify!!, mPosition )
        }

    }



    override fun onClickItem(objeto: Contactos, position: Int) {
        mListener!!.onClickItem(objeto, position)
    }
}