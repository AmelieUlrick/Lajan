package com.example.lajan.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Fragment.CarteFragment
import com.example.lajan.Fragment.ProfilFragment
import com.example.lajan.MainActivity
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_menu.*
import java.lang.System.exit


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val profilFragment = ProfilFragment()
        val CarteFragment = CarteFragment()


        //Récupère les données envoyées
        val intent : Intent = intent
        val idUser = intent.getIntExtra("idUser", 0)

        val db = DatabaseHelper(this)

        val user = db.getAll(idUser)

        for(element in user){
            menu_nom.text = element.Nom
            menu_prenom.text = element.Prenom
        }



        bottomNav.setOnNavigationItemSelectedListener{


            showHide()

            //Envoie l'id de l'utilisateur dans chaque fragment du menu
            intent.putExtra("idUser", idUser)
            when (it.itemId){
                R.id.navigation_profil -> {
                    makeCurrentFragment(profilFragment)
                }
                R.id.navigation_carte -> {
                    makeCurrentFragment(CarteFragment)
                }
                R.id.navigation_deconnexion ->{
                    exit(0)
                }
            }
            true
        }


        }


    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()

        }
    private fun showHide(){
        val accueil = findViewById<LinearLayout>(R.id.menu)
        accueil.visibility = View.GONE
    }

}