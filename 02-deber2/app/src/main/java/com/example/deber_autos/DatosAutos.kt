package com.example.deber_autos

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class DatosAutos : AppCompatActivity() {

    val arregloModeloAuto = BaseDatosAutos.arregloAutos
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_datos_autos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_autos)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val idArregloauto = intent.getIntExtra("ID_AUTO", -1)


        val nombreAuto = findViewById<EditText>(R.id.id_nombreModelo)

        val precioauto = findViewById<EditText>(R.id.id_precioModelo)
        val tipoModeloAuto = findViewById<EditText>(R.id.id_tipoModelo)
        val tipoCarro = findViewById<EditText>(R.id.id_tipCarro)

        nombreAuto.setText(arregloModeloAuto[idArregloauto].nombreModelo)
        precioauto.setText((arregloModeloAuto[idArregloauto].precioModelo).toString())
        tipoModeloAuto.setText(arregloModeloAuto[idArregloauto].tipomodelo)
        tipoCarro.setText(arregloModeloAuto[idArregloauto].carro)

        val botonActualizar = findViewById<Button>(R.id.btn_actualizar_auto)
        botonActualizar.setOnClickListener {
            val nuevoNombre = nombreAuto.text.toString()
            val nuevoPrecioAuto = precioauto.text.toString()
            val nuevoModelo = tipoModeloAuto.text.toString()
            val nuevoCarro = tipoCarro.text.toString()

            BaseDatosAutos.actualizarAuto(idArregloauto, nuevoNombre, nuevoPrecioAuto.toInt(),
                nuevoModelo,nuevoCarro)
            mostrarSnackbar("Modelo Actualizado + ${idArregloauto}")
        }

        nombreAuto.setText(arregloModeloAuto[idArregloauto].nombreModelo)
        precioauto.setText((arregloModeloAuto[idArregloauto].precioModelo).toString())
        tipoModeloAuto.setText(arregloModeloAuto[idArregloauto].tipomodelo)
        tipoCarro.setText(arregloModeloAuto[idArregloauto].carro)

        //mostrarSnackbar(arregloModeloAuto[idArregloauto].nombreModelo)
        //mostrarSnackbar(intent.getStringExtra("NOMBRE_COMPANIA").toString())
    };

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_autos),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}