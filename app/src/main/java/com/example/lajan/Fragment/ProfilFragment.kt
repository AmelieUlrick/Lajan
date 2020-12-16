package com.example.lajan.Fragment

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.lajan.Activity.MenuActivity
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Class.Compte
import com.example.lajan.Class.Utilisateur
import com.example.lajan.R
import com.example.lajan.Utils.Utils
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil.view.*
import java.lang.System.exit


class ProfilFragment : Fragment() {

    private val SELECT_PHOTO =  3000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profil, container, false)

        val databaseHandler: DatabaseHelper = DatabaseHelper(activity!!)
        val newIntent: Intent = requireActivity().intent
        val iduserP = newIntent.getIntExtra("idUser", 0)
        val NomP = newIntent.getStringExtra("nom")
        newIntent.putExtra("idUser",iduserP)

        val bitmap = Utils.getImage(databaseHandler.getImage2(iduserP)!!)
        view.imgProfil.setImageBitmap(bitmap)

        view.select_img.setOnClickListener()
        {
            val SelectPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(SelectPhoto,SELECT_PHOTO)
        }
        view.save_img.setOnClickListener()
        {
            val bitmap = (imgProfil.drawable as BitmapDrawable).bitmap
            val imgByte = Utils.getBytes(bitmap)
            DatabaseHelper(activity!!).updateImage(imgByte,iduserP)
        }
        view.modifie.setOnClickListener()
        {
            val fragment = ModifeFragment()
            val fragmentManager = activity!!.supportFragmentManager
            fragmentManager.beginTransaction().apply { replace(R.id.container,fragment)
                commit() }
        }

        val tt: List<Utilisateur> = databaseHandler.getAll(iduserP)
        for(e in tt){
            Toast.makeText(activity, "${e.Prenom}", Toast.LENGTH_SHORT).show()
        }

        return view
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val pickedImage = data?.data
        if(requestCode == SELECT_PHOTO  && resultCode == Activity.RESULT_OK && data != null)
            imgProfil.setImageURI(pickedImage)
        if (save_img != null) {
            save_img.isEnabled = true
        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }


}