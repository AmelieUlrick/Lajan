package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lajan.R
import kotlinx.android.synthetic.main.fragment_carte.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [CarteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarteFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_carte, container, false)

        view.recycler_addCard.setOnClickListener{
            val creerCarte = FormulaireCarte()
            val fragmenManager = activity!!.supportFragmentManager
            fragmenManager.beginTransaction().apply {
                replace(R.id.container,creerCarte)
                commit()
            }
        }
        return view
    }


}