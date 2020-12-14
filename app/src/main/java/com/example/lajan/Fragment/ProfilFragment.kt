package com.example.lajan.Fragment

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.R
import com.example.lajan.Utils.Utils
import kotlinx.android.synthetic.main.fragment_profil.*


class ProfilFragment : Fragment() {

    private val SELECT_PHOTO =  3000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        val databaseHandler: DatabaseHelper = DatabaseHelper(activity!!)
        val newIntent: Intent = requireActivity().intent
        val IduserP = newIntent.getIntExtra("id", 0)
        val NomP = newIntent.getStringExtra("nom")

        val bitmap = Utils.getImage(databaseHandler.getImage(IduserP)!!)
        imgProfil.setImageBitmap(bitmap)

        save_img.setOnClickListener()
        {
            val SavPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(SavPhoto,SELECT_PHOTO)
        }
        return view
    }

}