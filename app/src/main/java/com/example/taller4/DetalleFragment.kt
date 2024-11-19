package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetalleFragment : Fragment() {

    private lateinit var textViewTitulo: TextView
    private lateinit var textViewDescripcion: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_detalle, container, false)
        textViewTitulo = view.findViewById(R.id.textViewTitulo)
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion)
        return view
    }

    fun mostrarDetalle(titulo: String, descripcion: String) {
        textViewTitulo.text = titulo
        textViewDescripcion.text = descripcion
    }
}
