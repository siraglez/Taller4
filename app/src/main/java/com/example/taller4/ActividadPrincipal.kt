package com.example.taller4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ActividadPrincipal : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        // Inicializar el helper de la base de datos
        dbHelper = DatabaseHelper(this)

        // Recuperar el nombre almacenado desde SharedPreferences
        val sharedPref = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val nombreGuardado = sharedPref.getString("nombre_usuario", "") ?: ""

        val editTextNombre = findViewById<EditText>(R.id.editTextNombre)
        val textViewNombresSQLite = findViewById<TextView>(R.id.textViewNombresSQLite)
        val btnGuardarSQLite = findViewById<Button>(R.id.btnGuardarSQLite)
        val btnCargarSQLite = findViewById<Button>(R.id.btnCargarSQLite)
        val btnConfig = findViewById<Button>(R.id.btnConfig)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val textViewProgreso = findViewById<TextView>(R.id.textViewProgreso)

        // Mostrar el nombre guardado
        editTextNombre.setText(nombreGuardado)

        // Botón para guardar el nombre en SQLite
        btnGuardarSQLite.setOnClickListener {
            val nombre = editTextNombre.text.toString()
            guardarEnBaseDeDatos(nombre)
            editTextNombre.text.clear()
            Toast.makeText(this, "Nombre guardado en SQLite", Toast.LENGTH_SHORT).show()
        }

        // Botón para cargar nombres desde SQLite
        btnCargarSQLite.setOnClickListener {
            val nombres = cargarDesdeBaseDeDatos()
            textViewNombresSQLite.text = "Nombres en SQLite:\n${nombres.joinToString("\n")}"
        }

        // Botón para ir a la configuración
        btnConfig.setOnClickListener {
            navigateToConfig()
        }

        // Indicador de progreso
        progressBar.visibility = ProgressBar.GONE
        textViewProgreso.visibility = TextView.GONE
    }

    private fun guardarEnBaseDeDatos(nombre: String) {
        dbHelper.insertName(nombre)
    }

    private fun cargarDesdeBaseDeDatos(): List<String> {
        val nombres = dbHelper.getAllNames()
        Log.d("ActividadPrincipal", "Nombres cargados: $nombres")
        return nombres
    }

    private fun navigateToConfig() {
        val intent = Intent(this, PantallaConfiguracion::class.java)
        startActivity(intent)
    }
}
