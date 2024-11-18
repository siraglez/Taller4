package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetalleFragment : Fragment() {

    private lateinit var textViewDetalle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_detalle, container, false)
        textViewDetalle = view.findViewById(R.id.textViewDetalle)
        return view
    }

    fun mostrarDetalle(detalle: String) {
        textViewDetalle.text = detalle
    }
}
