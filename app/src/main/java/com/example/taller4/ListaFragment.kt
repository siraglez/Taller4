package com.example.taller4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListaFragment(private val onItemSelected: (String, String) -> Unit) : Fragment() {

    private val elementos = mutableListOf<Pair<String, String>>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)

        val listView = view.findViewById<ListView>(R.id.listView)
        val inputTitulo = view.findViewById<EditText>(R.id.inputTitulo)
        val inputDescripcion = view.findViewById<EditText>(R.id.inputDescripcion)
        val btnAgregar = view.findViewById<Button>(R.id.btnAgregar)

        // Cargar elementos almacenados en SharedPreferences
        cargarElementos()

        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, elementos.map { it.first })
        listView.adapter = adapter

        btnAgregar.setOnClickListener {
            val titulo = inputTitulo.text.toString()
            val descripcion = inputDescripcion.text.toString()
            if (titulo.isNotBlank() && descripcion.isNotBlank()) {
                elementos.add(Pair(titulo, descripcion))
                guardarElementos()
                actualizarWidget()
                adapter.notifyDataSetChanged()
                inputTitulo.text.clear()
                inputDescripcion.text.clear()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val (titulo, descripcion) = elementos[position]
            onItemSelected(titulo, descripcion)
        }

        return view
    }

    private fun guardarElementos() {
        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        val elementosString = elementos.joinToString("|") { "${it.first}:${it.second}" }
        editor.putString("lista_elementos", elementosString)
        editor.apply()
    }

    private fun cargarElementos() {
        val sharedPref = requireContext().getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val elementosString = sharedPref.getString("lista_elementos", "") ?: ""
        if (elementosString.isNotEmpty()) {
            elementos.clear()
            elementos.addAll(
                elementosString.split("|").map {
                    val (titulo, descripcion) = it.split(":")
                    Pair(titulo, descripcion)
                }
            )
        }
    }

    private fun actualizarWidget() {
        val intent = Intent(requireContext(), WidgetProvider::class.java).apply {
            action = "android.appwidget.action.APPWIDGET_UPDATE"
        }
        requireContext().sendBroadcast(intent)
    }
}
