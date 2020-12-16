package com.example.lajan.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Class.Utilisateur
import com.example.lajan.R
import com.example.lajan.Utils.Utils
import kotlinx.android.synthetic.main.activity_formulaire.*


class FormulaireActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulaire)

        confirmInscription.setOnClickListener(){

            Adduser()
        }
    }

    val databaseHandler: DatabaseHelper = DatabaseHelper(this)

    fun Adduser() {

        var user = Utilisateur(
                Prenom = prenom_ed.text.toString().trim(),
                Nom = nom_ed.text.toString().trim(),
                adresse_mail = adresse_mail_ed.text.toString().trim(),
                login = login_ed.text.toString().trim(),
                mdp = password_ed.text.toString().trim())

        if (NomVef() && PrenomVef() && PasswordVef() && (AdressVef() && Patterns.EMAIL_ADDRESS.matcher(adresse_mail_ed.text.toString().trim()).matches())) {
            val image = BitmapFactory.decodeResource(resources, R.drawable.image_profil)
            val image_profil = Utils.getBytes(image)
            databaseHandler.addUser(user, image_profil)
            val intent = Intent(this, ConnexionActivity::class.java)
            startActivity(intent)
        }
    }

    fun NomVef(): Boolean {
        if (nom_ed.text.toString().trim().isEmpty()) {
            nom_layout.error = getString(R.string.error_message_Nom)
            CacheClavier(nom_ed)
            return false
        } else {
            nom_layout.isErrorEnabled = false
        }
        return true
    }

    fun PrenomVef(): Boolean {
        if (prenom_ed.text.toString().trim().isEmpty()) {
            prenom_layout.error = getString(R.string.error_message_Prenom)
            CacheClavier(prenom_ed)
            return false
        } else {
            prenom_layout.isErrorEnabled = false

        }
        return true
    }

    fun PasswordVef(): Boolean {
        if (password_ed.text.toString().trim().isEmpty()) {
            password_layout.error = getString(R.string.error_message_Password)
            CacheClavier(password_ed)
            return false
        } else {
            password_layout.isErrorEnabled = false
        }
        return true
    }

    fun AdressVef(): Boolean {
        val adresseMail = adresse_mail_ed.text.toString().trim()
        if (adresseMail.isEmpty() || databaseHandler.addrForBdd(adresseMail) || !(Patterns.EMAIL_ADDRESS.matcher(adresseMail).matches())) {
            adresse_mail_layout.error = getString(R.string.error_message_Email)
            CacheClavier(adresse_mail_ed)
            return false
        } else {
            adresse_mail_layout.isErrorEnabled = false

        }
        return true
    }

    fun CacheClavier(view: View) {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun LoginsVef(): Boolean {
        val login = login_ed.text.toString().trim()
        if (NomVef() && PrenomVef() && PasswordVef() && AdressVef() &&  LoginsVef()) {
            login_layout.error = getString(R.string.error_message_Login2)
            CacheClavier(login_layout)
            return false
        } else {
            login_layout.isErrorEnabled = false

        }
        return true
    }

}

