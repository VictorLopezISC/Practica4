package com.jrgames.practica3.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener

/**
 * Integra funciones necesarias para nuestro Adaptador del recyclerview y evitar re escribir código
 */
abstract class BaseAdapter<T>(val mListener: OnAdapterClickListener<T>): RecyclerView.Adapter<BaseViewHolder<T>>() {

    protected var mItems: MutableList<T> = ArrayList()

    /**
     * Actualizar los datos del adaptador
     */
    fun updateData(listaMutable: MutableList<T>?){

        if( listaMutable != null){
             mItems.clear()//borrado de datos de la lista
             mItems.addAll(listaMutable)//Agregamos los nuevos datos proporcionados
             notifyDataSetChanged()
        }
    }

    /**
     * Funcion generica para inflar el lienzo que usen nuestros items
     */
    protected open fun getViewFromLayout(viewGroup: ViewGroup, layout: Int): View?{
        return LayoutInflater.from(viewGroup.context).inflate(layout, viewGroup, false)
    }


    /**
     * Funcion sobr3e escrita que permte retornar el total de elementos que contiene nuestra listaMutable
     */
    override fun getItemCount(): Int {
        return mItems.size
    }


    /**
     * Funcion generica para obtener la lista Mutable
     */
    fun getItems() = mItems


}