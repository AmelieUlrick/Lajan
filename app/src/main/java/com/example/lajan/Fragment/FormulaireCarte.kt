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
    //Création d'une Carte Bancaire et d'un compte ou celui-ci sera associé à la carte
    fun ajouterLaCarte() {
        //Récupère les données envoyées
        val intent = requireActivity().intent
        val dataIdUser = intent.getIntExtra("idUser", 0)

        val num: String = form_numCarte.getText().toString().trim()
        //val num2 = form_numCarte.getText().toString().toLong()
        val date: String = form_dateExpiration.getText().toString().trim()
        val type: String = form_type.getText().toString().trim()
        val name: String = form_name.getText().toString().trim()

        if(num.isEmpty() || num.equals("") || num == null) {
            form_numCarte_layout.error = getString(R.string.error_message_carte)
            form_name_layout.isErrorEnabled = true
            form_type_layout.isErrorEnabled = true
            form_dateExpiration_layout.isErrorEnabled = true

        }
        else
        {
            form_numCarte_layout.isErrorEnabled = false
        }

        if (date.isEmpty() || date.equals("") || date == null ) {
            form_dateExpiration_layout.error = getString(R.string.error_message_carte)
            form_name_layout.isErrorEnabled = true
            form_type_layout.isErrorEnabled = true
            form_numCarte_layout.isErrorEnabled = true
        }
        else
        {
            form_dateExpiration_layout.isErrorEnabled = false

        }


        if (type.isEmpty() || type.equals("") || type == null) {
            form_type_layout.error = getString(R.string.error_message_carte)
            form_name_layout.isErrorEnabled = true
            form_numCarte_layout.isErrorEnabled = true
            form_dateExpiration_layout.isErrorEnabled = true
        }
        else
        {
            form_type_layout.isErrorEnabled =false
        }


        if (name.isEmpty() || name.equals("") || name == null) {
            form_name_layout.error = getString(R.string.error_message_carte)
            form_numCarte_layout.isErrorEnabled = true
            form_type_layout.isErrorEnabled = true
            form_dateExpiration_layout.isErrorEnabled = true

        }
        else
        {
            form_name_layout.isErrorEnabled =false
        }
        if( form_name_layout.isErrorEnabled == false && form_type_layout.isErrorEnabled == false && form_dateExpiration_layout.isErrorEnabled == false && form_numCarte_layout.isErrorEnabled == false){
            val db = DatabaseHelper(activity!!)

            val carte = CarteBancaire(numeroCarte = form_numCarte.text.toString().toLong(),
                    dateExpiration = form_dateExpiration.text.toString().toInt(),
                    typeCarte = form_type.text.toString().trim(),
                    keyUserCarte = dataIdUser)
            val compte = Compte(solde = 0.0, decouvert = 0.0, keyCarte = db.getIdCarte(), keyUserCpt = dataIdUser, name_cpt = form_name.text.toString().trim())
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


}