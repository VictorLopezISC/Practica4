package com.jrgames.practica3.domain.response

/**
 * Es nuestro objeto con los parametros para nuestra lista de Spotify
 */
class SpotifyObject {

    var cancion: String? = null
    var interprete: String? = null
    var imagenAlbum : Int = 0

    constructor()

    constructor(imagen : Int, interprete: String, cancion: String){
        this.imagenAlbum = imagen
        this.cancion = cancion
        this.interprete = interprete
    }

}