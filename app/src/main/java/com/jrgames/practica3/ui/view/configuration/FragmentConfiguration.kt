package com.jrgames.practica3.ui.view.configuration
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.jrgames.practica3.R
import com.jrgames.practica3.ui.base.BaseFragment
import com.jrgames.practica3.ui.dialogs.DialogPreview
import com.jrgames.practica3.utils.Constants
import com.jrgames.practica3.utils.Utils
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


/**
 *
 * 1.- Declaramos en nuestro Anifest el permiso de Camara y Galería
 *
 *
 * 2.- Declaramos en el MAnifest el permiso de Cámara
 * 3.- Declaramos el provider en nuestro manifest
 * 4.- Crear el archivo file_paths.xml
 *
 *
 */
class FragmentConfiguration : BaseFragment(){

    private var rootView: View? = null
    private lateinit var btnCamera:Button
    private lateinit var btnGallery:Button
    var listener : FragmentConfigCallBack? = null
    var mPhoto : String? = null


    companion object{

        val TAG = FragmentConfiguration::class.java.simpleName

        fun getInstance(bundle: Bundle?): FragmentConfiguration?{
            val fragment = FragmentConfiguration()
            if(bundle != null){
                fragment.arguments= bundle
            }
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_configuration,container,false)
        initUI()
        return  rootView
    }

    /*
     * Inicializamos la interfaz de Usuario (Casteo de widgets)
     */
    private fun initUI(){

        btnCamera = rootView!!.findViewById(R.id.btnCamera)
        btnGallery = rootView!!.findViewById(R.id.btnGallery)

       //evento del boton
        btnCamera.setOnClickListener {

            if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.CAMERA), Constants.CAMERA_REQUEST)//Detona la solicitud del permiso
            }else{
                //El usuario ya otorgo el permiso de almacenamiento
                initCamera()
            }

        }

        btnGallery.setOnClickListener {

            if(ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), Constants.GALLERY_REQUEST)//Detona la solicitud del permiso

            }else{

                //El usuario ya otorgo el permiso de almacenamiento
                initGallery()

            }
        }
    }


    /**
     * Logica para detonar el intent y acceder al almacaneamiento del dispositivo
     */
    private fun initGallery(){

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, Constants.GALLERY_REQUEST)// codigo esta en la constante nos permitira identificar cuando retornen los datos a nuestro fragment
        //esta abriendo la camara
    }


    /**
     * Aqui viene de regreso los datos seleccionados
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){

            Constants.CAMERA -> if(resultCode == Activity.RESULT_OK)

                setPictureFromCamera()

            Constants.GALLERY_REQUEST ->
                if(resultCode == Activity.RESULT_OK && data != null){

                    val bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), data.data)
                    showDialog(bitmap);

                }
        }
    }


    private fun showDialog(bitmap: Bitmap){


        if(bitmap != null) {
            val manager = fragmentManager
            val dg = DialogPreview()

            //Transformarlo en ByteArray
            val stream = ByteArrayOutputStream()

            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val bitmapToByteArray = stream.toByteArray()

            val bundle = Bundle()
            bundle.putByteArray(Constants.IMAGES, bitmapToByteArray)
            dg.arguments = bundle
            dg.setStyle(DialogFragment.STYLE_NORMAL, R.style.dialogFragmentTheme)//TODO importante esta linea para que nose vea recortado el DialogFragment
            dg.show(manager!!, DialogPreview.TAG)

            dg.setListener(object :
                DialogPreview.DialogPreviewListener { // Callback para escuchar cuando retorne la imagen recortada

                override fun sendToFragment(byteArray: ByteArray) {

                    Log.d("SUCCESS", byteArray.toString())
                    listener!!.sendToActivity(byteArray)
                    dg.dismiss()

                }
            })
        }
    }



    interface FragmentConfigCallBack{

        fun sendToActivity(byteArray: ByteArray)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as FragmentConfigCallBack//inicializamos el listener
    }

    private fun initCamera(){

        val intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //Creamos el archivo
        var photo: File? = null
        try {
            photo = Utils.createImageFile(requireContext())
            mPhoto = photo!!.absolutePath
        }catch (ex:IOException){
            Toast.makeText(context, "Error al crear la imagen", Toast.LENGTH_LONG).show()
        }

        if(photo != null){

            val photoUri = FileProvider.getUriForFile(requireContext(), requireContext().packageName, photo)
            intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(intentCamera, Constants.CAMERA)

        }
    }


    private fun setPictureFromCamera(){

        val bitmapPhoto = BitmapFactory.decodeFile(mPhoto)//Obtenemos la imagen por medio del path
        showDialog(bitmapPhoto)//detonamos el Dialog

    }

}