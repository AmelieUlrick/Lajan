package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lajan.R

/**
 * A simple [Fragment] subclass.
 * Use the [ListeCarte.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListeCarte : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_liste_carte, container, false)
        return view
    }

}