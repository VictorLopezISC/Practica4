package com.jrgames.practica3.ui.view.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.RoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jrgames.practica3.R
import com.jrgames.practica3.io.DBRoom
import com.jrgames.practica3.io.entities.Contactos
import com.jrgames.practica3.ui.base.BaseFragment
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener
import com.jrgames.practica3.ui.recyclers.ContactsAdapter
import com.jrgames.practica3.utils.Constants

class FragmentRoom : BaseFragment(), OnAdapterClickListener<Contactos>{

    private var rootView: View? = null
    lateinit var fabNewContact: FloatingActionButton
    private var room: DBRoom? = null
    private var adpt:ContactsAdapter? = null
    private lateinit var recycler: RecyclerView


    companion object{

        val TAG = FragmentRoom::class.java.simpleName

        fun getInstance(bundle: Bundle?): FragmentRoom?{
            val fragmentHome = FragmentRoom()
            if(bundle != null){
                fragmentHome.arguments= bundle
            }
            return fragmentHome
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_room,container,false)
        initUI()
        return  rootView

    }

    /**
     * Inicializa la interfaz de usuario
     */
    fun initUI(){

        fabNewContact = rootView!!.findViewById(R.id.fabNewContact)
        recycler = rootView!!.findViewById(R.id.rvContactos)
        room = DBRoom.getRoomDatabase(requireContext())// Hacemos la instancia de room en nuestro fragmento


        adpt = ContactsAdapter(this)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.setHasFixedSize(true)
        recycler.adapter = adpt


        fabNewContact.setOnClickListener {

            pushFragment(FragmentAlta.getInstance(null)!!, FragmentAlta.TAG, true)
        }
    }

    /**
     * Escuchamr el evento cuando retorna desde el flujo de la alta de contactos para hacer la consulta y actualizr la lista de contactos
     */
    override fun onResume() {
        super.onResume()
        getContacts()
    }


    /**
     * Metodo para obtener los contactos
     */
    private fun getContacts(){

        var list = room!!.contactoDao()!!.getAllContacts()// Obtenemos la lista de COntactos almacenados por medio de nuestro query

        if(list!!.size > 0){

            adpt!!.updateData(list as ArrayList<Contactos>)//TODO Hacemos el update al recyclerView

        }
    }


    /**
     * Escuchamos el click de nuestro boton de eliminar
     */
    override fun onClickItem(objeto: Contactos, position: Int) {

        //deleteContact(objeto)//BORRAR

        editContacto(objeto)//EDITAR

    }


    /**
     * Metodo para eliminar un contacto
     */
    private fun deleteContact(item: Contactos){
        if(item != null){

            room!!.contactoDao()!!.deleteContact(item.telefono!!)//ELiminamos el contacto
            getContacts()

        }
    }


    private fun editContacto(item: Contactos){

        var bundle = Bundle()
        bundle.putSerializable(Constants.ITEM, item)

        pushFragment(FragmentEdit.getInstance(bundle)!!, FragmentEdit.TAG, true)

    }


}