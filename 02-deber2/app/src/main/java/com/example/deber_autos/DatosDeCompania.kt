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
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class DatosDeCompania : AppCompatActivity() {

    //val idAutoCompani = intent.getIntExtra("ID_COMPANIA", -1)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datos_de_compania)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_datos_compania)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val idCompania = intent.getIntExtra("ID_COMPANIA", -1)
        val nombreCompania = intent.getStringExtra("NOMBRE_COMPANIA")
        val descipcionCompania = intent.getStringExtra("DESCRIPCION_COMPANIA")

        val id = findViewById<EditText>(R.id.input_id)
        val nombre = findViewById<EditText>(R.id.input_nombre)
        val descripcion = findViewById<EditText>(R.id.input_descripcion)

        id.setText(idCompania.toString())
        nombre.setText(nombreCompania)
        descripcion.setText(descipcionCompania)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar)
        botonActualizar.setOnClickListener {
            val nuevoNombre = nombre.text.toString()
            val nuevaDescripcion = descripcion.text.toString()

            BaseDatosCompania.actualizarCompania(idCompania, nuevoNombre, nuevaDescripcion)
            mostrarSnackbar("Datos de compania actualizada")
        }

        val inputDescripcion = findViewById<EditText>(R.id.input_descripcion)
        registerForContextMenu(inputDescripcion)

        id.setText(idCompania.toString())
        nombre.setText(nombreCompania)
        descripcion.setText(descipcionCompania)

        //mostrarSnackbar(intent.getIntExtra("ID_COMPANIA", -1).toString())
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_datos_compania),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }

    // Colocacion del menu

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        // llenamos opciones del menu
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v?.id == R.id.input_descripcion) {
            val inflater = menuInflater
            inflater.inflate(R.menu.menu, menu)
        }
    }


    override fun onContextItemSelected(
        item: MenuItem
    ): Boolean {
        return when (item.itemId){
            R.id.mi_editar -> {
                val idAutoC = intent.getIntExtra("Posicion", -1)
                val intentAutos = Intent(this, DatosAutos::class.java)
                intentAutos.putExtra("ID_AUTO",idAutoC)
                startActivity(intentAutos)
               // mostrarSnackbar("Editar datos de autos");
                return true
            }
            R.id.mi_eliminar -> {
                mostrarSnackbar("eliminado")
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


}