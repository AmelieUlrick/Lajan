package com.example.lajan.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lajan.Fragment.CarteFragment
import com.example.lajan.Fragment.HomeFragment
import com.example.lajan.Fragment.ProfilFragment
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_menu.*
import java.lang.System.exit


class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val homeFragment = HomeFragment()
        val profilFragment = ProfilFragment()
        val CarteFragment = CarteFragment()



        //Récupère les données envoyées
        val intent : Intent = intent
        val idUser = intent.getIntExtra("idUser", 0)

        bottomNav.setOnNavigationItemSelectedListener{

            //Envoie l'id de l'utilisateur dans chaque fragment du menu
            intent.putExtra("idUser", idUser)
            when (it.itemId){
                R.id.navigation_home -> {
                    makeCurrentFragment(homeFragment)
                }
                R.id.navigation_profil -> {
                    makeCurrentFragment(profilFragment)
                }
                R.id.navigation_carte -> {
                    makeCurrentFragment(CarteFragment)

                }
            }
            true
        }

        imgcb.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
                makeCurrentFragment(homeFragment)
               bottomNav.selectedItemId = R.id.navigation_home
            }
        })

        imgLog.setOnClickListener(object : View.OnClickListener {

            override fun onClick(v: View?) {
               exit(0)
            }
        })

        }


    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()

        }

}