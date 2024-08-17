package com.example.deber_autos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class ListCompanias : AppCompatActivity() {
    val arreglo= BaseDatosCompania.arrgloBaseCompania
    lateinit var adaptador: ArrayAdapter<BaseCompania>



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_companias)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_companias)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listView = findViewById<ListView>(R.id.lv_list_compania)

        //val adaptador= ArrayAdapter(
        adaptador= ArrayAdapter(
            this,//ListCompanias : AppCompatActivity()
            android.R.layout.simple_list_item_1, //layput xml a usar
            arreglo
        )
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged() // refrescar la interface cuando eliminamos o añadimos registros

        val botonAnadirListCompania= findViewById<Button>(R.id.btn_anadir_compania)
        botonAnadirListCompania.setOnClickListener {
            anadirCompania(adaptador)
        }

        registerForContextMenu(listView)
    }

    var posicionItemSeleccionado =0

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu,v,menuInfo)
        // llenamos opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        // Obtener id
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
    }


    override fun onContextItemSelected(
        item: MenuItem
    ): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                val intent = Intent(this,DatosDeCompania::class.java)
               // intent.putExtra("ID_COMPANIA", arreglo[posicionItemSeleccionado].id) // Pasa el ID o cualquier dato necesario
                val compania = arreglo[posicionItemSeleccionado]
                intent.putExtra("ID_COMPANIA", compania.id)
                intent.putExtra("NOMBRE_COMPANIA", compania.nombre)
                intent.putExtra("DESCRIPCION_COMPANIA", compania.descripcion)
                intent.putExtra("Posicion",posicionItemSeleccionado)
                startActivity(intent)
               // mostrarSnackbar("Editar $posicionItemSeleccionado")
                return true
            }
            R.id.mi_eliminar -> {
                arreglo.removeAt(posicionItemSeleccionado) // Eliminar el item de la lista
                adaptador.notifyDataSetChanged() // Notificar al adaptador del cambio
                mostrarSnackbar("Eliminar $posicionItemSeleccionado")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun anadirCompania(adaptador: ArrayAdapter<BaseCompania>){
        arreglo.add(
            BaseCompania(4,"VOLKSWAGEN","Trailer")
        )
        adaptador.notifyDataSetChanged()
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.lv_list_compania),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}