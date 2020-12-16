package com.example.lajan.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_modife.*
import kotlinx.android.synthetic.main.fragment_modife.view.*
import kotlinx.android.synthetic.main.fragment_profil.*

class ModifeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_modife, container, false)
        val databaseHandler: DatabaseHelper = DatabaseHelper(activity!!)
        val newIntent2: Intent = requireActivity().intent
        val iduserP2 = newIntent2.getIntExtra("idUser", 0)

        val info = arrayOf("Nom","Prénom","Adresse mail","Password","Login")
        val adp = ArrayAdapter(activity!!,android.R.layout.simple_spinner_dropdown_item,info)
        view.spinnerb.adapter = adp

        view.spinnerb.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                val label: String = parent.getItemAtPosition(position).toString()


                if(label == "Nom")
                {
                    nom_layout.visibility = View.VISIBLE
                    adresse_mail_layout.visibility = View.GONE
                    prenom_layout.visibility = View.GONE
                    password_layout.visibility = View.GONE

                }
                if(label == "Prénom")
                {
                    prenom_layout.visibility = View.VISIBLE
                    nom_layout.visibility = View.GONE
                    adresse_mail_layout.visibility = View.GONE
                    password_layout.visibility = View.GONE
                    login_layout.visibility =View.GONE
                }
                if(label == "Adresse mail")
                {
                    adresse_mail_layout.visibility = View.VISIBLE
                    nom_layout.visibility = View.GONE
                    prenom_layout.visibility = View.GONE
                    password_layout.visibility = View.GONE
                    login_layout.visibility =View.GONE
                }
                if(label == "Password")
                {
                    password_layout.visibility = View.VISIBLE
                    nom_layout.visibility = View.GONE
                    prenom_layout.visibility = View.GONE
                    adresse_mail_layout.visibility = View.GONE
                    login_layout.visibility =View.GONE
                }
                if(label == "Login")
                {
                    login_layout.visibility =View.VISIBLE
                    password_layout.visibility =View.GONE
                    nom_layout.visibility = View.GONE
                    prenom_layout.visibility = View.GONE
                    adresse_mail_layout.visibility = View.GONE
                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        view.modifieInfo.setOnClickListener(){
            if ( nom_layout.visibility == View.VISIBLE) {
                databaseHandler.updateNom(nom_ed.text.toString(), iduserP2)
                Toast.makeText(activity, " Nom mis à jour", Toast.LENGTH_SHORT).show()
                passFragment()
            }
            if ( prenom_layout.visibility == View.VISIBLE) {
                databaseHandler.updatePrenom(prenom_ed.text.toString(), iduserP2)
                Toast.makeText(activity, " Prénom mis à jour", Toast.LENGTH_SHORT).show()
                passFragment()
            }
            if( adresse_mail_layout.visibility == View.VISIBLE){
                databaseHandler.updateAdresseM(adresse_mail_ed.text.toString(), iduserP2)
                Toast.makeText(activity, " Adresse mail mis à jour", Toast.LENGTH_SHORT).show()
                passFragment()
            }
            if( password_layout.visibility == View.VISIBLE){
                databaseHandler.updatePassword(password_ed.text.toString(), iduserP2)
                Toast.makeText(activity, " Mot de passe mis à jour", Toast.LENGTH_SHORT).show()
                passFragment()
            }
            if( login_layout.visibility == View.VISIBLE){
                databaseHandler.updateLogin(login_ed.text.toString(), iduserP2)
                Toast.makeText(activity, " Login mis à jour", Toast.LENGTH_SHORT).show()
                passFragment()
            }
        }


        return view
    }

    private fun passFragment(){
        val fragmentManager = activity!!.supportFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.container, ProfilFragment())
            commit()
        }
    }


}