package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lajan.R

/**
 * A simple [Fragment] subclass.
 * Use the [FormulaireCarte.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormulaireCarte : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_formulaire_carte, container, false)
    }


}