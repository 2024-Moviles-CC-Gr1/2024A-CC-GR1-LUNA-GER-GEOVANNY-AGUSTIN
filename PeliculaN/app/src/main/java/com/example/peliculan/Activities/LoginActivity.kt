package com.example.peliculan.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.peliculan.R
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.editTextText)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val loginBtn= findViewById<Button>(R.id.LoginBtn)

        loginBtn.setOnClickListener{
            if (username.text.toString().isEmpty() || password.text.toString().isEmpty()) {
                mostrarSnackbar("Please fill in your username and password")
            } else if (username.text.toString() == "test" && password.text.toString() == "test") {
                irActividad(MainActivity::class.java)
            } else {
                mostrarSnackbar("Your username and password are not correct")
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