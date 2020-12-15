package com.example.lajan.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.lajan.Fragment.HomeFragment
import com.example.lajan.Fragment.ProfilFragment
import com.example.lajan.Fragment.CarteFragment
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val homeFragment = HomeFragment()
        val profilFragment = ProfilFragment()
        val CarteFragment = CarteFragment()

        //val Nom = intent.getStringExtra("nom")


        makeCurrentFragment(homeFragment)

        //Récupère les données envoyées
        val intent : Intent = intent
        val idUser = intent.getIntExtra("idUser", 0)
        //intent.getIntExtra("idUser", 0)

        bottomNav.setOnNavigationItemSelectedListener{

            //Envoie l'id de l'utilisateur dans chaque fragment du menu
            intent.putExtra("idUser", idUser)
            when (it.itemId){
                R.id.navigation_home -> {
                    makeCurrentFragment(homeFragment)
                    intent.putExtra("idUser", idUser)
                }
                R.id.navigation_profil -> {
                    makeCurrentFragment(profilFragment)
                    intent.putExtra("idUser", idUser)
                }
                R.id.navigation_carte -> {
                    makeCurrentFragment(CarteFragment)
                    intent.putExtra("idUser", idUser)
                    //intent.getStringExtra("nom")
                }
            }
            true
        }
        }


    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()

        }

}