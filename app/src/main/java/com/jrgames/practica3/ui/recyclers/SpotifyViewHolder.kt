package com.jrgames.practica3.ui.recyclers

import android.view.View
import androidx.core.content.ContextCompat
import com.jrgames.practica3.domain.response.SpotifyObject
import com.jrgames.practica3.ui.base.BaseViewHolder
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener
import kotlinx.android.synthetic.main.item_spotify.view.*

/**
 * Clase de tipo viewholder necesaria para nuestro Adaptador de el recyclerView
 */
class SpotifyViewHolder(itemView: View): BaseViewHolder<SpotifyObject>(itemView), OnAdapterClickListener<SpotifyObject> {

    /**
     * Seteamos los datos proporcionados por el adapter
     */
    override fun fillViewHolder() {

        //comenzamos a setear los datos
       itemView.tvCancion.text = itemSpotify!!.cancion
       itemView.tvInterprete.text = itemSpotify!!.interprete
       itemView.ivSpotify.background = ContextCompat.getDrawable(mContext, itemSpotify!!.imagenAlbum)

       //Implementamos el click en nuestro adaptador
        itemView.clPrincipal.setOnClickListener {
            mListener!!.onClickItem(itemSpotify!!, mPosition )
        }

    }



    override fun onClickItem(objeto: SpotifyObject, position: Int) {
        mListener!!.onClickItem(objeto, position)
    }
}