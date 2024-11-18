package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListaFragment(private val onItemSelected: (String) -> Unit) : Fragment() {

    private val elementos = listOf("Elemento 1", "Elemento 2", "Elemento 3")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_lista, container, false)
        val listView = view.findViewById<ListView>(R.id.listView)
        listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, elementos)

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            onItemSelected(elementos[position])
        }
        return view
    }
}
