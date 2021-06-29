package com.jrgames.practica3.ui.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrgames.practica3.R
import com.jrgames.practica3.domain.response.SpotifyObject
import com.jrgames.practica3.ui.base.BaseFragment
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener
import com.jrgames.practica3.ui.recyclers.SpotifyAdapter

class FragmentRecycler : BaseFragment(), OnAdapterClickListener<SpotifyObject>{

    private var rootView: View? = null
    private var adapter: SpotifyAdapter? = null
    private lateinit var rvSpotify: RecyclerView
    var indicador = "RECYCLER"

    companion object{

        val TAG = FragmentRecycler::class.java.simpleName

        fun getInstance(bundle: Bundle?): FragmentRecycler?{
            val fragmentHome = FragmentRecycler()
            if(bundle != null){
                fragmentHome.arguments= bundle
            }
            return fragmentHome
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_recycler,container,false)
        initUI()
        return  rootView

    }


    fun initUI(){

        rvSpotify = rootView!!.findViewById(R.id.rvSpotify)//casteamos el elemento (Recycelerview)
        adapter = SpotifyAdapter(this) //Inicializamos el Adaptador

        rvSpotify.layoutManager = LinearLayoutManager(requireContext())// Le pasamos a a nestro RecyclerView su linearlayoutManager
        rvSpotify.setHasFixedSize(true)// ayuda a estandariczar el tamaño de nuestro item y no se distorcione
        rvSpotify.adapter = adapter // Le pasamos a nuestro Recycler su respectivo Adaptador

        adapter!!.updateData(returnList())

    }


    /**
     * Retirna una lista de Spotify de manera hardcodeada
     */

    private fun returnList(): ArrayList<SpotifyObject>{

        var lista = ArrayList<SpotifyObject>()

        var heroes = SpotifyObject(R.drawable.heroes_silencio, "Heroes del Silencio", "Maldito Duende")
        lista.add(heroes)

        var sia = SpotifyObject(R.drawable.sia_thunder, "Sia", "ThunderClouds")
        lista.add(sia)

        var blink = SpotifyObject(R.drawable.blink182, "BLink 182", "I Miss You")
        lista.add(blink)

        var tones = SpotifyObject(R.drawable.dance_monkey, "Tones and I", "Dance Monkey")
        lista.add(tones)

        return lista

    }

    //Escuchamos el onclick presionado en nuestro lienzo principal
    override fun onClickItem(objeto: SpotifyObject, position: Int) {
        showLog(indicador, "Has seleccionado al artista ${objeto.interprete} y la canción ${objeto.cancion}")
    }


}