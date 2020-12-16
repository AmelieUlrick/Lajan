package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        val intent = requireActivity().intent
        val idUser = intent.getIntExtra("idUser", 0)

        view.recycler_addCard.setOnClickListener{

            intent.putExtra("idUser", idUser)
            val creerCarte = FormulaireCarte()
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.container,creerCarte)
                commit()
            }
        }

        view.carte_affiche.setOnClickListener{
            intent.putExtra("idUser", idUser)
            val afficheCarte = ListeCarte()
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.container, afficheCarte)
                commit()
            }
        }

        return view
    }


}