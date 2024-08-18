package com.example.deber_autos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Inicio_Concesionario : AppCompatActivity() {

    val arreglo1= BaseDatosCompania.arrgloBaseCompania
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inicio_concesionario)

        val botonChe = findViewById<ImageButton>(R.id.btnChevrolet)
        botonChe
            .setOnClickListener{
                val intent = Intent(this,DatosDeCompania::class.java)
                val compania = arreglo1[2]
                intent.putExtra("ID_COMPANIA", compania.id)
                intent.putExtra("NOMBRE_COMPANIA", compania.nombre)
                intent.putExtra("DESCRIPCION_COMPANIA", compania.descripcion)
                intent.putExtra("Posicion",2)
                startActivity(intent)

            }
        val botonNis = findViewById<ImageButton>(R.id.btnNissan)
        botonNis
            .setOnClickListener{
                val intent = Intent(this,DatosDeCompania::class.java)
                val compania = arreglo1[1]
                intent.putExtra("ID_COMPANIA", compania.id)
                intent.putExtra("NOMBRE_COMPANIA", compania.nombre)
                intent.putExtra("DESCRIPCION_COMPANIA", compania.descripcion)
                intent.putExtra("Posicion",1)
                startActivity(intent)


            }
        val botonHyun = findViewById<ImageButton>(R.id.btnHyundai)
        botonHyun
            .setOnClickListener{
                val intent = Intent(this,DatosDeCompania::class.java)
                val compania = arreglo1[0]
                intent.putExtra("ID_COMPANIA", compania.id)
                intent.putExtra("NOMBRE_COMPANIA", compania.nombre)
                intent.putExtra("DESCRIPCION_COMPANIA", compania.descripcion)
                intent.putExtra("Posicion",0)
                startActivity(intent)



            }
        val botonWolsk = findViewById<ImageButton>(R.id.btnWolsk)
        botonWolsk
            .setOnClickListener{
                irActividad(DatosDeCompania::class.java)
            }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}