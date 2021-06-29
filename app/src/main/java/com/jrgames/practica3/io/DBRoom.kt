package com.jrgames.practica3.io

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jrgames.practica3.io.dao.ContactoDao
import com.jrgames.practica3.io.entities.Contactos

@Database(entities = [Contactos::class],version = 1, exportSchema = false)
abstract class DBRoom: RoomDatabase() {


    abstract fun contactoDao(): ContactoDao?

    companion object{

        private var INSTANCE: DBRoom? = null

        fun getRoomDatabase(context: Context): DBRoom?{

            if(INSTANCE == null){

                INSTANCE = Room.databaseBuilder(context.applicationContext,
                DBRoom::class.java, "Practice.DB")
                    .allowMainThreadQueries()
                    .build()
            }

            return  INSTANCE
        }


        fun destroyIntance(){
            INSTANCE = null
        }

    }

}