package com.jrgames.practica3.io.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "CONTACTOS")
class Contactos : Serializable{ //Permite hacer el objeto serializable

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id = 0

    @ColumnInfo(name = "NOMBRE")
    var nombre: String? = null

    @ColumnInfo(name = "AP_PATERNO")
    var aPaterno: String? = null

    @ColumnInfo(name = "AP_MATERNO")
    var aMaterno: String? = null

    @ColumnInfo(name = "TELEFONO")
    var telefono : String? = null

}