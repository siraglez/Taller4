package com.example.taller4.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.taller4.R

class DetalleFragment : Fragment() {

    private lateinit var textViewTitulo: TextView
    private lateinit var textViewDescripcion: TextView
    private lateinit var btnCerrar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_detalle, container, false)

        textViewTitulo = view.findViewById(R.id.textViewTitulo)
        textViewDescripcion = view.findViewById(R.id.textViewDescripcion)
        btnCerrar = view.findViewById(R.id.btnCerrar)

        btnCerrar.setOnClickListener {
            requireActivity().findViewById<View>(R.id.fragmentDetalle).visibility = View.GONE
        }

        return view
    }

    fun mostrarDetalle(titulo: String, descripcion: String) {
        textViewTitulo.text = "Título: $titulo"
        textViewDescripcion.text = "Descripción: $descripcion"
    }
}
