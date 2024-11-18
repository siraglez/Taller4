package com.example.taller4

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class ActividadPrincipal : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private lateinit var mainLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        // Inicializar el SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Referencia al layout principal para cambiar el color de fondo
        mainLayout = findViewById(R.id.layoutPrincipal)

        // Fragmentos
        val listaFragment = ListaFragment { elementoSeleccionado ->
            val detalleFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentDetalle) as? DetalleFragment
            detalleFragment?.mostrarDetalle(elementoSeleccionado)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLista, listaFragment)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        // Registrar el listener del acelerómetro
        acelerometro?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()
        // Detener el listener para ahorrar batería
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // Calcular la magnitud del vector
            val aceleracion = sqrt(x * x + y * y + z * z)
            if (aceleracion > 15) { // Umbral para detectar movimiento
                cambiarColorFondo()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No se necesita manejar cambios de precisión
    }

    private fun cambiarColorFondo() {
        val colores = listOf(
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA
        )
        mainLayout.setBackgroundColor(colores.random())
    }
}
