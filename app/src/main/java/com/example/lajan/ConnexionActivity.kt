package com.example.lajan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lajan.Bdd.DatabaseHelper
import kotlinx.android.synthetic.main.activity_connexion.*

class ConnexionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        cnx_btn.setOnClickListener {
            seConnecter()
        }
    }

    //Renvoie un Toast disant si la connexion a été faite ou non
    fun seConnecter(){
        val db = DatabaseHelper(this)


        val idUser = db.getUser(cnx_login.text.toString(),cnx_mdp.text.toString())
        if(db.connexion(cnx_login.text.toString(),cnx_mdp.text.toString())){
            //val nav = Intent(this,)
            //nav.putExtra("idUser",idUser)
            //startActivity(nav)
            Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "Login ou mote de passe incorrect", Toast.LENGTH_SHORT).show()
        }
    }
}