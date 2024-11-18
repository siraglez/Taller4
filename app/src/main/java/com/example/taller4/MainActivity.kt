package com.example.taller4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewSaludo = findViewById<TextView>(R.id.textViewSaludo)
        val btnIrActividadPrincipal = findViewById<Button>(R.id.btnIrActividadPrincipal)

        // Mostrar saludo basado en la hora del día
        textViewSaludo.text = calcularSaludo()

        // Botón para ir a la Actividad Principal
        btnIrActividadPrincipal.setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun calcularSaludo(): String {
        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when {
            hora in 0..11 -> "Buenos días"
            hora in 12..19 -> "Buenas tardes"
            else -> "Buenas noches"
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, ActividadPrincipal::class.java)
        startActivity(intent)
    }
}
