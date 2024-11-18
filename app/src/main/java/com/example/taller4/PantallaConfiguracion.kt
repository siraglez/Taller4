package com.example.taller4

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PantallaConfiguracion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_configuracion)

        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val colorGuardado = sharedPref.getInt("color_fondo", Color.LTGRAY)

        val btnColorRojo = findViewById<Button>(R.id.btnColorRojo)
        val btnColorAzul = findViewById<Button>(R.id.btnColorAzul)
        val btnColorVerde = findViewById<Button>(R.id.btnColorVerde)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // Botón para cambiar el color a rojo
        btnColorRojo.setOnClickListener {
            guardarColor(Color.RED)
        }

        // Botón para cambiar el color a azul
        btnColorAzul.setOnClickListener {
            guardarColor(Color.BLUE)
        }

        // Botón para cambiar el color a verde
        btnColorVerde.setOnClickListener {
            guardarColor(Color.GREEN)
        }

        // Botón para volver a la actividad principal
        btnVolver.setOnClickListener {
            navigateToMain()
        }
    }

    private fun guardarColor(color: Int) {
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("color_fondo", color)
            apply()
        }
    }

    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
