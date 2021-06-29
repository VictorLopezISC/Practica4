package com.jrgames.practica3.ui.codigoQR

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jrgames.practica3.R
import com.jrgames.practica3.io.entities.Negocio
import com.jrgames.practica3.ui.bottomdialog.ZMRBottomComplete

class ZMRcodigoQR : Fragment(){

    private var rootView: View? = null
    private var bottomSheet: ZMRBottomComplete? = null
    lateinit var zmrButtom: Button

    companion object{

        val TAG = ZMRcodigoQR::class.java.simpleName

        fun getInstance(bundle: Bundle?): ZMRcodigoQR?{
            val fragmentHome = ZMRcodigoQR()
            if(bundle != null){
                fragmentHome.arguments= bundle
            }
            return fragmentHome
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_qr, container, false)
       initUI()
        return rootView

        }

        private fun initUI() {
            zmrButtom = rootView!!.findViewById(R.id.zmrButtonStart)
            zmrButtom.setOnClickListener {
                startBottomSheet()
            }
        }
        private fun startBottomSheet(){

            if(bottomSheet == null || !bottomSheet?.isAdded!!){

                //CERRAR LA APLICACION
                bottomSheet =  ZMRBottomComplete.newInstance(Negocio())

                bottomSheet?.show(requireActivity().supportFragmentManager, TAG)//Mostramos el bottomSheet

            }

        }

        }




