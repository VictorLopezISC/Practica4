package com.jrgames.practica3.ui.base

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.jrgames.practica3.ui.view.home.FragmentHome
import com.jrgames.practica3.R
import com.jrgames.practica3.ui.codigoQR.ZMRcodigoQR
import com.jrgames.practica3.ui.view.configuration.FragmentConfiguration
import com.jrgames.practica3.ui.view.bottomsheet.ZMRBottomSheetFragment
import com.jrgames.practica3.ui.view.recycler.FragmentRecycler
import com.jrgames.practica3.ui.view.room.FragmentRoom
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity(),  NavigationView.OnNavigationItemSelectedListener,FragmentConfiguration.FragmentConfigCallBack{

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private var conteinerFragment: Fragment? = null

    private lateinit var headerView: View
    private lateinit var ciHeader: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

    }

    /**
     * INicializamos la interfaz del usuario
     */
    fun initUI() {


        toolbar = findViewById(R.id.toolbar)//Casteamos el toolbar
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)//El mimso declarado en el xml
        navigationView = findViewById(R.id.nav_view)//Casteamos nuestro navView

        var toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )//TODO Aqui fue el error

        drawer.addDrawerListener(toggle) // PRoporcionamos el Toogle para poder abrir y cerrar nuestro Drawer
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)//Notificamos a la activity que minavigation View escucha el metodo implementado

        headerView = navigationView.getHeaderView(0)


        ciHeader = headerView.findViewById(R.id.ciHeader)


        pushFragment(
            FragmentHome.getInstance(
                null
            )!!, FragmentHome.TAG, false
        )
 Log.e("into","hola mundo");
    }

    /**
     * Funcion permite inflar Fragmentos a mi  activity
     */
    fun pushFragment(fragment: Fragment, tag: String?, backStack: Boolean) {

        if (!isFinishing) {
            conteinerFragment = fragment
            val ft = supportFragmentManager.beginTransaction()

            ft.replace(
                R.id.frameLayout,
                conteinerFragment!!
            )//el id FrameLAyout debe ser el mismo declaradon en mi xml

            if (backStack) {
                ft.addToBackStack(tag)
            }
            ft.commitAllowingStateLoss()
        }
    }


    /**
     * escucha el return nativo del movil
     */
    override fun onBackPressed() {
        var drawer: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {//Si esta abierto el DrawerLAyout
            drawer.closeDrawer(GravityCompat.START)//lo cierra
        } else {
            super.onBackPressed()//Hace su funcion natural de return
        }

    }

    /**
     * Logica de navegacion de nuestro menu lateral (Items declarados activity_main_drawer.xml)
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        var drawer: DrawerLayout = findViewById(R.id.drawer_layout)

        if(id == R.id.nav_home){
            pushFragment(
                FragmentHome.getInstance(
                    null
                )!!, FragmentHome.TAG,false )
        }else if(id == R.id.nav_recycler){
            pushFragment(FragmentRecycler.getInstance(null)!!, FragmentRecycler.TAG,false )
        }else if(id == R.id.nav_maps) {
            startActivity(Intent(this, MapsActivity::class.java))
        }else if(id == R.id.nav_room) {
            pushFragment(FragmentRoom.getInstance(null)!!, FragmentRoom.TAG,true)
        }else if(id == R.id.nav_configuration) {//Declaramos el id en nuestro drawer listener
            pushFragment(FragmentConfiguration.getInstance(null)!!, FragmentConfiguration.TAG,true)
        } else if(id == R.id.nav_sheet) {//Declaramos el id en nuestro drawer listener
            pushFragment(ZMRBottomSheetFragment.getInstance(null)!!, ZMRBottomSheetFragment.TAG,true)
    } else if(id == R.id.nav_escaner) {//Declaramos el id en nuestro drawer listener
        pushFragment(ZMRcodigoQR.getInstance(null)!!, ZMRcodigoQR.TAG,true)
    }


        drawer.closeDrawer(GravityCompat.START)//Una vez que navegó Se cierra el Drawer
        return true
    }

    override fun sendToActivity(byteArray: ByteArray) {
        if(byteArray != null && ciHeader != null){

            val picture = BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
            ciHeader.setImageBitmap(picture)//Seteamos el bitmap convertido del byteArray en el CircleImageView

        }
    }

}