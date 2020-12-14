package com.example.lajan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lajan.Activity.FormulaireActivity
import com.example.lajan.Activity.MenuActivity
import com.example.lajan.Activity.RecupererMdp
import com.example.lajan.Bdd.DatabaseHelper
import kotlinx.android.synthetic.main.activity_connexion.*

class ConnexionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        //Clique sur le bouton connexion
        cnx_btn.setOnClickListener {
            seConnecter()
        }

        //Clique sur le bouton inscrivez-vous
        cnx_formulaire.setOnClickListener {
            val nav = Intent(this, FormulaireActivity::class.java)
            startActivity(nav)
        }

        cnx_mdp_oublier.setOnClickListener {
            val nav = Intent(this, RecupererMdp::class.java)
            startActivity(nav)
        }
    }

    //Renvoie un Toast disant si la connexion a été faite ou non
    fun seConnecter(){
        val db = DatabaseHelper(this)


        val idUser = db.getUser(cnx_login.text.toString(),cnx_mdp.text.toString())
        if(db.connexion(cnx_login.text.toString(),cnx_mdp.text.toString())){
            val nav = Intent(this, MenuActivity::class.java)
            nav.putExtra("idUser",idUser)
            startActivity(nav)
            Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Login ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
        }
    }
}