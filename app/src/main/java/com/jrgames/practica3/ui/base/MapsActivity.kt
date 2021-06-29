package com.jrgames.practica3.ui.base

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jrgames.practica3.R
import com.jrgames.practica3.domain.response.ConductorUberObject
import com.jrgames.practica3.utils.EnumCamionetas
import com.jrgames.practica3.utils.Utils
import java.lang.Exception

/**
 *
 * 1.- Crear ApiKey en la consola de Google Platform
 *
 * 2.- Modificar el lienozo xml
 *
 * 3.- Validar el permiso de acceso a la ubicacion este en nuestro Manifest (Otorgrlo)
 *
 *4.- Agregar al Gradle la libreria de ubicacion de Google
 *
 */
class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap // Variable global del mapa
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var mapFragment: SupportMapFragment
    private var latitud = 0.0
    private var longitud = 0.0
    lateinit var btnShowVans:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(   R.layout.activity_maps)

        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment //Inicializar y vincular el maapa de Google con el fragment de nuestro lienzo .xml
        getLocation()

    }

    /**
     * Nuestro mapa está listo
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        if(latitud != 0.0 && longitud != 0.0){

           val miUbicacion = LatLng(latitud, longitud)  //Creamos la ubicacion

           val cameraPosition = CameraPosition.Builder() //Creamos un cameraPosition para mover la ubicacion del mapa a nuestra posicion
               .target(miUbicacion)
               .zoom(10f)
               .build()

            mMap.addMarker(MarkerOptions().position(miUbicacion).title("Mi Ubicación")) //Agregamos el marcador a nuestro
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))//Movemos el mapa a nuestra ubicacion

            //Definir el tipo de mapa
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            //InitUI()
              initUI()
        }
    }

    /**
     * Acceder a la ubicacion de nuestro gps
     */
    @SuppressLint("MissingPermission")
    fun getLocation(){

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->

            if(location == null){

                try {

                    val service: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    val criteria = Criteria()// Clase que indcia los criterios de la app para seleccionar un proveedor de ubicacion
                    val provider: String? = service.getBestProvider(criteria, false)
                    val locationProvider: Location = service.getLastKnownLocation(provider!!)!!

                    if(locationProvider == null){

                        getLocation()
                        return@addOnSuccessListener

                    }else{ // Ya podemos obtener la ubicacion
                        latitud = location!!.latitude
                        longitud = location!!.longitude
                        mapFragment.getMapAsync(this)
                    }

                }catch (e: Exception){
                   Log.d(javaClass.simpleName, "Exception ${e.cause} ${e.localizedMessage}")
                }
            }else{// el location no es nulo ya podemos obtener la ubicacion
                latitud = location.latitude
                longitud = location.longitude
                mapFragment.getMapAsync(this)
            }
        }
    }


    /**
     * Inicializamos la interfaz del usuario
     */
    fun initUI(){

        btnShowVans = findViewById(R.id.btnCamionetas)//Casteo del widget

        //TODO escuchamos el click del boton que esta dentro del mapa
        btnShowVans.setOnClickListener {

             startMultipleMarkers()

        }
    }


    /**
     * Implementamos varios macadores dentro del mapa
     */
    fun startMultipleMarkers(){

        var list =  Utils.getUberList()

        if(mMap != null){

            for(item: ConductorUberObject in list ){


                var enumCamioneta = EnumCamionetas.getFromId(item.idCamioneta)//Enum Inicializado

                //TODO Agregamos el marcador al mapa

                mMap.addMarker(MarkerOptions()
                        .position(LatLng(item.lat, item.lng))
                        .icon(Utils.bitamDescriptor(this, EnumCamionetas.getRecurso(enumCamioneta)))
                        .title("Conductor: ${item.nombreConductor} , Calificación: ${item.calificacion.toString()}")
                )

            }


        }else{
            Toast.makeText(this, "Algo fallo al inicializar el mapa", Toast.LENGTH_LONG).show()
        }
    }





}