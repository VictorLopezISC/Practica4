package com.jrgames.practica3.ui.bottomdialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.jrgames.practica3.R
import com.jrgames.practica3.io.entities.Negocio
import com.jrgames.practica3.ui.base.interfaces.OnAdapterClickListener
import kotlinx.android.synthetic.main.zmr_bottom_complete.*

class ZMRBottomCompleteQR : BottomSheetDialogFragment(), DialogInterface.OnShowListener, CompoundButton.OnCheckedChangeListener {

    var rootView: View? =  null
    var negocio: Negocio? = null
    lateinit var chipProducto: Chip
    lateinit var chipServicio: Chip
    lateinit var chipDisponible: Chip
    lateinit var chipPersonalizar: Chip
    lateinit var spinner: Spinner


    lateinit var listener: OnAdapterClickListener<Negocio>


    companion object{
        fun newInstance(item: Negocio?)=
            ZMRBottomCompleteQR().apply {
                this.negocio = item
            }
    }


    /**
     * Nos permite servir com puente y referenciarlo desde donde se invoque el BottomSheet
     */
    fun onAdapterBottomClick(listener: OnAdapterClickListener<Negocio>){
        this.listener = listener
    }


    /**
     * Dentro del onCreate Seteamos el estilo de mi BottomSheet
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.z_bottom_sheet_style)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.zmr_bottom_complete, container,false)
        return rootView
    }

    /**
     * Necesario para el onShowListener
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener(this)
        return dialog
    }

    /**
     * Inicializamos la interfaz del usuario
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI(){

        chipProducto = rootView?.findViewById(R.id.chipProductos)!!
        chipProducto.isChecked = true  // encendemos el chip inicialmente
        chipProducto.setOnCheckedChangeListener(this)

        chipServicio = rootView?.findViewById(R.id.chipServicios)!!
        chipServicio.setOnCheckedChangeListener(this)

        chipDisponible = rootView?.findViewById(R.id.chipDisponible)!!
        chipDisponible.setOnCheckedChangeListener(this)

        chipPersonalizar = rootView?.findViewById(R.id.chipPersonaliza)!!
        chipPersonalizar.setOnCheckedChangeListener(this)

        spinner = rootView?.findViewById(R.id.spinner)!!
        var categoryList = ArrayList<String>()
        categoryList.add("Electrónicos y linea blanca")
        categoryList.add("Belleza y cuidados de piel")
        categoryList.add("Computadoras")
        categoryList.add("Celulares")

        spinner.adapter =  ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, categoryList)

    }



    override fun onShow(p0: DialogInterface?) {

    }

    override fun onCheckedChanged(button: CompoundButton?, isChecked: Boolean) {
        when(button?.id) {

            R.id.chipProductos->{
                if(isChecked){
                    chipServicio.isChecked = false
                    chipProducto.isChecked = true
                    negocio!!.idOffer = 0
                }
            }
            R.id.chipServicios->{
                if(isChecked){
                    chipServicio.isChecked = false
                    chipProducto.isChecked = true
                    negocio!!.idOffer = 1
                }
            }


            R.id.chipDisponible->{
                if(isChecked){
                    chipDisponible.isChecked = false
                    chipPersonalizar.isChecked = true
                }
            }
            R.id.chipPersonaliza->{
                if(isChecked){
                    chipDisponible.isChecked = false
                    chipPersonalizar.isChecked = true
                }
            }

        }

    }
}