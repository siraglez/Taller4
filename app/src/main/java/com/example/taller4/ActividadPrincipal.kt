package com.example.taller4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActividadPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        val listaFragment = ListaFragment { titulo, descripcion ->
            val detalleFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentDetalle) as? DetalleFragment
            detalleFragment?.mostrarDetalle(titulo, descripcion)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLista, listaFragment)
            .commit()

        findViewById<Button>(R.id.btnSensor).setOnClickListener {
            startActivity(Intent(this, SensorActivity::class.java))
        }
    }
}
