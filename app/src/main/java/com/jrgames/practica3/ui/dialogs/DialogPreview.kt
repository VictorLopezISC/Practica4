package com.jrgames.practica3.ui.dialogs

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.jrgames.practica3.R
import com.jrgames.practica3.ui.view.configuration.FragmentConfiguration
import com.jrgames.practica3.utils.Constants
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.ByteArrayOutputStream

class DialogPreview : DialogFragment() {

    private lateinit var vista: View
    private lateinit var btnReady: Button
    private lateinit var crImage: CropImageView
    private var bitmapPhoto: Bitmap? = null
    private lateinit var listener : DialogPreviewListener

    companion object{

        val TAG = FragmentConfiguration::class.java.simpleName

        fun getInstance(bundle: Bundle?): DialogPreview?{
            val dg = DialogPreview()
            if(bundle != null){
                dg.arguments= bundle
            }
            return dg
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        vista = inflater.inflate(R.layout.dg_preview, container,false)

        dialog!!.window!!.setBackgroundDrawable( ColorDrawable(Color.TRANSPARENT))
        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)

        initUI()

        return vista
    }

    private fun initUI(){

        btnReady = vista.findViewById(R.id.btnReady)
        crImage = vista.findViewById(R.id.crImage)

        var bundle = arguments
        if(bundle != null){

            var byteAR = arguments!!.getByteArray(Constants.IMAGES)
            bitmapPhoto = BitmapFactory.decodeByteArray(byteAR, 0, byteAR!!.size)
            crImage.setImageBitmap(bitmapPhoto)

        }

        btnReady.setOnClickListener {

            var imagenRecortada:  Bitmap = crImage.getCroppedImage()//El widget retorna la imagenr ecortad

            var stream = ByteArrayOutputStream()
            imagenRecortada.compress(Bitmap.CompressFormat.PNG, 100, stream)
            var baCropped = stream.toByteArray()

            listener.sendToFragment(baCropped)

        }

    }

    interface  DialogPreviewListener{

        fun sendToFragment(byteArray: ByteArray)

    }

    fun setListener(listener: DialogPreviewListener){
        this.listener = listener
    }

}