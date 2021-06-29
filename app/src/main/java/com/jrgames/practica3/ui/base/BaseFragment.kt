package com.jrgames.practica3.ui.base

import android.util.Log
import androidx.fragment.app.Fragment
import com.jrgames.practica3.io.DBRoom

open class BaseFragment : Fragment(){



    fun showLog(tag: String, mensaje: String){
        Log.d(tag, mensaje)
    }

    /**
     * Permite cambiar de Fragmento
     */
    fun pushFragment(fragment: Fragment, tag: String, backstack:Boolean){
        if(activity != null){
            var act = activity as MainActivity
            act.pushFragment(fragment,tag,backstack)
        }
    }

}