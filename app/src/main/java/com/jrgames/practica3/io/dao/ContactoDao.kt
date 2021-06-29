package com.jrgames.practica3.io.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jrgames.practica3.io.entities.Contactos

@Dao
interface ContactoDao {


    //TODO Create
    /**
     * ALta de contactos
     */
    @Insert
    fun insertContact(vararg conacto: Contactos?)

    //TODO Read
    //Obtenemos una lista de tipo Contactos
    @Query("SELECT * FROM CONTACTOS")
    fun getAllContacts():List<Contactos?>?

    //TODO Update
    @Query("UPDATE CONTACTOS set NOMBRE = :nombre, AP_PATERNO = :apPaterno, AP_MATERNO = :apMaterno WHERE TELEFONO = :phone")
    fun updateContact(nombre: String, apPaterno: String, apMaterno:String, phone:String)

    //TODO DELETE
    @Query("DELETE FROM CONTACTOS WHERE TELEFONO = :tel")//QUery delete
    fun deleteContact(vararg tel: String)

}