package com.jrgames.practica3.io.entities

import java.io.Serializable
class Negocio : Serializable {

    //BottomSheet 1
    var nombre: String? = null
    var direccion: ZMRDireccion? = null
    var id: Int = 0
    var tipoNegocio: Int = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var numeroCelular: String? = null
    var email: String? = null
    var descripcion: String? = null


    //TODO BottomSheetComplete 2
    var idOffer: Int = 0
    var category: ZMRCategoria? = null
    var tipoHorario: Int = 0 //  0 == 24/7  || 1 ==  Horario personalizado
    var listDias: ArrayList<ZMRHorario>? = null

}