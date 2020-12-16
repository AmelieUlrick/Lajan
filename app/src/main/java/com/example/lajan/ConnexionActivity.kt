package com.example.lajan


import android.annotation.SuppressLint
import android.app.ActionBar
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

    @SuppressLint("WrongConstant")
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

        if(db.getLogin(cnx_login.text.toString().trim()) == false || cnx_login.text.toString().isEmpty())
        {
            login_co_layout.error = getString(R.string.error_message_Login)
            mdp_layout.isErrorEnabled = true
        }
        if(db.MdpForCo(cnx_mdp.text.toString().trim()) == false || cnx_mdp.text.toString().isEmpty())
        {
            mdp_layout.error = getString(R.string.error_message_Password2)
            login_co_layout.isErrorEnabled = true
        }
        if(db.connexion(cnx_login.text.toString().trim(),cnx_mdp.text.toString().trim())){
            mdp_layout.isErrorEnabled = false
            val nav = Intent(this, MenuActivity::class.java)
            nav.putExtra("idUser",idUser)
            startActivity(nav)
            // Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
        }

    }
}