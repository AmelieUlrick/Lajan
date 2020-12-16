package com.example.lajan.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lajan.Class.Recapitulatif
import com.example.lajan.R

class CustomHistorique(context : Context, var ressources : Int, var list:List<Recapitulatif>): ArrayAdapter<Recapitulatif>(context,ressources,list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = layoutInflater.inflate(ressources,null)

        val descriptif = view.findViewById<TextView>(R.id.list_descriptif)

        val recapitulatif = list[position]

        descriptif.text = recapitulatif.descriptif

        return view
    }
}