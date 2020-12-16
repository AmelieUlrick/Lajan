package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Class.CarteBancaire
import com.example.lajan.Class.Compte
import com.example.lajan.R
import kotlinx.android.synthetic.main.fragment_formulaire_carte.*
import kotlinx.android.synthetic.main.fragment_formulaire_carte.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [FormulaireCarte.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormulaireCarte : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_formulaire_carte, container, false)

        //Récupère les données envoyées
        val intent = requireActivity().intent
        val dataIdUser = intent.getIntExtra("idUser", 0)

        val db = DatabaseHelper(activity!!)

        //Création d'une Carte Bancaire et d'un compte ou celui-ci sera associé à la carte
        fun ajouterLaCarte(){
            val carte = CarteBancaire(numeroCarte = form_numCarte.text.toString().toLong(),
                    dateExpiration = form_dateExpiration.text.toString().toInt(),
                    typeCarte = form_type.text.toString().trim(),
                    keyUserCarte = dataIdUser)
            db.addCard(carte)

            val compte = Compte(solde = 0.0, decouvert = 0.0, keyCarte = db.getIdCarte(), keyUserCpt = dataIdUser, name_cpt = form_name.text.toString().trim())
            db.addCompte(compte)

            Toast.makeText(activity, "Carte crée", Toast.LENGTH_SHORT).show()
        }

        view.form_Carte.setOnClickListener {
            ajouterLaCarte()
            val pageAfficheCarte = ListeCarte()
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.container, pageAfficheCarte)
                commit()
            }
        }

        return view
    }




}