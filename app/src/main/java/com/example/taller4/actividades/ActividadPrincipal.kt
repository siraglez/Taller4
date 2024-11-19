package com.example.taller4.actividades

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.taller4.R
import com.example.taller4.fragmentos.DetalleFragment
import com.example.taller4.fragmentos.ListaFragment

class ActividadPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        // Cargar el fragmento de lista al inicio
        val listaFragment = ListaFragment { titulo, descripcion ->
            mostrarDetalles(titulo, descripcion)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLista, listaFragment)
            .commit()
    }

    private fun mostrarDetalles(titulo: String, descripcion: String) {
        val detalleFragment = supportFragmentManager.findFragmentById(R.id.fragmentDetalle) as? DetalleFragment
            ?: DetalleFragment().also {
                supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentDetalle, it)
                    .commitNow() // Asegura que se carga antes de usarlo
            }

        // Mostrar los detalles en el fragmento
        detalleFragment.mostrarDetalle(titulo, descripcion)

        // Hacer visible el contenedor del fragmento de detalles
        findViewById<View>(R.id.fragmentDetalle).visibility = View.VISIBLE

        findViewById<Button>(R.id.btnSensor).setOnClickListener {
            startActivity(Intent(this, SensorActivity::class.java))
        }
    }
}
