package com.jrgames.practica3.ui.view.bottomsheet

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

class ZMRBottomSheetFragment : Fragment(){

    private var rootView: View? = null
    private var bottomSheet: ZMRBottomComplete? = null
    lateinit var zmrButtom: Button

    companion object{

        val TAG = ZMRBottomSheetFragment::class.java.simpleName

        fun getInstance(bundle: Bundle?): ZMRBottomSheetFragment?{
            val fragmentHome = ZMRBottomSheetFragment()
            if(bundle != null){
                fragmentHome.arguments= bundle
            }
            return fragmentHome
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.z_fragment_bottom, container, false)
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

                //TODO llenar el negocio
                bottomSheet =  ZMRBottomComplete.newInstance(Negocio())

                bottomSheet?.show(requireActivity().supportFragmentManager, TAG)//Mostramos el bottomSheet

            }

        }

        }




