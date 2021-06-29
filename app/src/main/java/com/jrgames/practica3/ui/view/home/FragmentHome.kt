package com.jrgames.practica3.ui.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jrgames.practica3.R

class FragmentHome : Fragment(){

    private var rootView: View? = null

    companion object{

        val TAG = FragmentHome::class.java.simpleName

        fun getInstance(bundle: Bundle?): FragmentHome?{
            val fragmentHome = FragmentHome()
            if(bundle != null){
                fragmentHome.arguments= bundle
            }
            return fragmentHome
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_home,container,false)
        return  rootView

    }
}