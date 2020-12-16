package com.example.lajan.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Class.CarteBancaire
import com.example.lajan.Class.Compte
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_connexion.*
import kotlinx.android.synthetic.main.activity_formulaire.*
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



        view.form_Carte.setOnClickListener {

            ajouterLaCarte()
        }


        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    //Création d'une Carte Bancaire et d'un compte ou celui-ci sera associé à la carte
    fun ajouterLaCarte(){
        //Récupère les données envoyées
        val intent = requireActivity().intent
        val dataIdUser = intent.getIntExtra("idUser", 0)


        val db = DatabaseHelper(activity!!)

        val carte = CarteBancaire(numeroCarte = form_numCarte.text.toString().toLong(),
                dateExpiration = form_dateExpiration.text.toString().toInt(),
                typeCarte = form_type.text.toString().trim(),
                keyUserCarte = dataIdUser)
        val compte = Compte(solde = 0.0, decouvert = 0.0, keyCarte = db.getIdCarte(), keyUserCpt = dataIdUser, name_cpt = form_name.text.toString().trim())

        if(!numVerif() || form_numCarte.text.toString() =="")
        {
            form_numCarte_layout.error = getString(R.string.error_message_carte)
        }

        if(!dateVerif() || form_dateExpiration.text.toString() =="")
        {
            form_dateExpiration_layout.error = getString(R.string.error_message_carte)
        }
        if(!typeVerif() || form_type.text.toString() =="")
        {
            form_type_layout.error = getString(R.string.error_message_carte)
        }
        if(!nameVerif() || form_dateExpiration.text.toString() =="")
        {
            form_name_layout.error = getString(R.string.error_message_carte)
        }
        else{

            db.addCard(carte)
            db.addCompte(compte)
            val pageAfficheCarte = ListeCarte()
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.container, pageAfficheCarte)
                commit()
            }
        }


    }

    fun numVerif (): Boolean {
        if (form_numCarte.text.toString().isEmpty() ) {
            form_numCarte_layout.error = getString(R.string.error_message_carte)
            return false
        } else {
            form_numCarte_layout.isErrorEnabled = false
        }
        return true
    }
    fun dateVerif (): Boolean {
        if (form_dateExpiration.text.toString() == "") {
            form_dateExpiration_layout.error = getString(R.string.error_message_carte)
            return false
        } else {
            form_dateExpiration_layout.isErrorEnabled = false
        }
        return true
    }

    fun typeVerif (): Boolean {
        if (form_type.text.toString().trim().isEmpty()) {
            form_type_layout.error = getString(R.string.error_message_carte)
            return false
        } else {
            form_type_layout.isErrorEnabled = false
        }
        return true
    }
    fun nameVerif (): Boolean {
        if (form_name.text.toString().trim()== "") {
            form_name_layout.error = getString(R.string.error_message_carte)
            return false
        } else {
            form_name_layout.isErrorEnabled = false
        }
        return true
    }


}