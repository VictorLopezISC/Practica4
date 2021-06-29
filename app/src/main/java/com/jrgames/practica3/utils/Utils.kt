package com.jrgames.practica3.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Environment
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.jrgames.practica3.domain.response.ConductorUberObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Clase de utileria para no terner to do en la clase principal
 */
class Utils {

    companion object{

        fun getUberList(): ArrayList<ConductorUberObject>{

            var listUber: ArrayList<ConductorUberObject> = ArrayList()

            var jesus = ConductorUberObject("Jesús Romero", 18.8730156,-97.110409, 1, "XZT3299", 3.5)
            listUber.add(jesus)

            var carlosAntonio = ConductorUberObject("Carlos Antonio", 18.8752389,-97.1110528, 2, "XZT1234", 4.0)
            listUber.add(carlosAntonio)


            var alondra = ConductorUberObject("Alondra Abrego", 18.8752389,-97.111052, 3, "XZT4321", 5.0)
            listUber.add(alondra)

            var amanda = ConductorUberObject("Amanda Gabriela", 18.8752389,-97.1110528, 4, "XZT8521", 4.9)
            listUber.add(amanda)

            var jonatan = ConductorUberObject("Jonatan de la Cruz", 18.8704065,-97.1127265, 5, "XZT2522", 4.6)
            listUber.add(jonatan)

            var victor = ConductorUberObject("Victor ?", 18.8784062,-97.1138852, 6, "XZT4466", 4.8)
            listUber.add(victor)

            var miguel = ConductorUberObject("Miguel Angel", 18.8697568,-97.111954, 7, "XZT9988", 3.8)
            listUber.add(miguel)

            return listUber
        }

        /**
         * Nor permite cambiar el un Vector (Recurso xml) en Bitmap para setearlo en nuestro marker del mapa
         */
        fun bitamDescriptor(context: Context, @DrawableRes vectorResId: Int): BitmapDescriptor?{

            val vectorDrawable = ContextCompat.getDrawable(context, vectorResId) //Creamos el Drawable apartir del recurso


            vectorDrawable!!.setBounds(0,0,vectorDrawable!!.intrinsicWidth, vectorDrawable.intrinsicHeight )//Obtiene las dimensiones

            val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth,vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888) //Cremoa un bitmap base

            val canvas = Canvas(bitmap) //Linezo

            vectorDrawable.draw(canvas)//seteamos en el lienzo el  bitmap

            return BitmapDescriptorFactory.fromBitmap(bitmap)

        }
        fun createImageFile(context: Context): File?{
        val timeStamp = SimpleDateFormat("yyyyyMMdd_HHmmss").format(Date())
            val imageFileName = "JPEG_" +timeStamp+ "_"
            val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

            val image = File.createTempFile(imageFileName,".jpg",storageDir)

            return image
        }

    }

}