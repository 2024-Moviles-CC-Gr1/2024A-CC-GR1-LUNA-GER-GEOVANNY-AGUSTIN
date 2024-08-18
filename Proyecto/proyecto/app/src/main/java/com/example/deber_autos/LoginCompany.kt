package com.example.deber_autos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class LoginCompany : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_company)

        val username = findViewById<EditText>(R.id.editTextText)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val loginBtn= findViewById<Button>(R.id.LoginBtn)

        loginBtn.setOnClickListener{
            if (username.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                mostrarSnackbar("Por favor colocar tu nombre y password")
            } else if (username.text.toString() == "geovanny" && password.text.toString() == "geovanny") {
                irActividad(Inicio_Concesionario::class.java)
            } else {
                mostrarSnackbar("Tu nombre o password no son correctos")
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cl_login)) { v, insets ->
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
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_login),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
}