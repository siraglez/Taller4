package com.example.taller4

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class SensorActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var acelerometro: Sensor? = null
    private lateinit var layoutSensor: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        // Inicializar el SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // Referencia al layout para cambiar el color de fondo
        layoutSensor = findViewById(R.id.layoutSensor)

        // Botón para volver a la Pantalla Principal
        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish() // Cierra esta actividad y vuelve a la anterior
        }
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
        // Desregistrar el listener para ahorrar batería
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
        // No es necesario manejar cambios de precisión
    }

    private fun cambiarColorFondo() {
        val colores = listOf(
            Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA
        )
        layoutSensor.setBackgroundColor(colores.random())
    }
}
