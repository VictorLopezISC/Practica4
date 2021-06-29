package com.jrgames.practica3.ui.base.interfaces

/**
 * Clase permite escuchar los click de nuestro Adapter y sirve como un puente que notifica a la visa
 */
interface OnAdapterClickListener<T> {

    fun onClickItem(objeto:T, position: Int)//Funcion generica donde solicitamos el objeto y la posicion de la lista



}