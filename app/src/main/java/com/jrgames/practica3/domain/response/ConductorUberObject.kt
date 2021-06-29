package com.jrgames.practica3.domain.response

/**
 * Clase de tipo pojo pra los conductores de uber de nuestro mapa
 */
class ConductorUberObject constructor( var nombreConductor: String? = null,
                                       var lat: Double = 0.0,
                                       var lng: Double = 0.0,
                                       var idCamioneta: Int = 0,
                                       var numeroPlaca: String? = null,
                                       var calificacion: Double = 0.0) {
}