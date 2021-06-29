package com.jrgames.practica3.ui.view.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jrgames.practica3.R
import com.jrgames.practica3.io.DBRoom
import com.jrgames.practica3.io.entities.Contactos
import com.jrgames.practica3.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_alta.*

class FragmentAlta : BaseFragment(){

    private var rootView: View? = null
    lateinit var btnSave: Button

    lateinit var etName: EditText
    lateinit var etPAterno: EditText
    lateinit var etMaterno: EditText
    lateinit var etPhone: EditText

    companion object{

        val TAG = FragmentAlta::class.java.simpleName

        fun getInstance(bundle: Bundle?): FragmentAlta?{
            val fragmentHome = FragmentAlta()
            if(bundle != null){
                fragmentHome.arguments= bundle
            }
            return fragmentHome
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_alta,container,false)
        initUI()
        return  rootView

    }

    /**
     * Inicializa la interfaz de usuario
     */
    fun initUI(){


        etName = rootView!!.findViewById(R.id.etName)
        etPAterno = rootView!!.findViewById(R.id.etPaterno)
        etMaterno = rootView!!.findViewById(R.id.etMaterno)
        etPhone = rootView!!.findViewById(R.id.etPhone)
        btnSave = rootView!!.findViewById(R.id.btnSave)


        btnSave.setOnClickListener {

            saveContact(etName.text.toString(), etPaterno.text.toString(),etMaterno.text.toString(), etPhone.text.toString())

        }

    }


    /**
     * Método para almacenar el contacto en Room
     */
    fun saveContact( nombre: String?, apPaterno: String?, apMaterno: String?, phone:String?){

        if(nombre != null && apPaterno != null && apMaterno != null && phone != null){

            var room = DBRoom.getRoomDatabase(requireContext())//Variable compartida de Room
            var entity = Contactos() //Creamos un objeto de tipo entity(Necesario para Room)
            entity.nombre = nombre
            entity.aPaterno = apPaterno
            entity.aMaterno = apMaterno
            entity.telefono = phone


            //TODO invocamos a room e insertamos el objeto por medio de Dao

            room!!.contactoDao()!!.insertContact(entity)

            //Una Vez insertado el entity Limpiamos los campos
            etName.setText("")
            etPAterno.setText("")
            etMaterno.setText("")
            etPhone.setText("")

            Toast.makeText(requireContext(), "Se inserto el contacto satisfactoriamente", Toast.LENGTH_LONG).show()
            showLog("ROOM", "Insert Success")

        }else{
            showLog("ROOM", "Algo falló")
        }

    }



}