package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Class.CarteBancaire
import com.example.lajan.Class.Compte
import com.example.lajan.Class.ListCarteAdapter
import com.example.lajan.R
import kotlinx.android.synthetic.main.fragment_liste_carte.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [ListeCarte.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListeCarte : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_liste_carte, container, false)

        val intent = requireActivity().intent
        val dataIdUser = intent.getIntExtra("idUser", 0)

        val db = DatabaseHelper(activity!!)

        var list = mutableListOf<Compte>()
        var list2 = mutableListOf<CarteBancaire>()

        fun afficheCompte(){
            list.clear()
            val res = db.readCompte(dataIdUser)
            if(res.count == 0){
                Toast.makeText(activity, "no record :", Toast.LENGTH_LONG).show()
            }
            while (res.moveToNext()){
                list.add(Compte(res.getInt(0),
                    res.getDouble(1),
                    res.getDouble(2),
                    res.getInt(3),
                    res.getInt(4)))
            }
        }

        fun afficheCarte(){
            list2.clear()
            val res = db.readCarte(dataIdUser)
            if(res.count == 0){
                Toast.makeText(activity, "no record :", Toast.LENGTH_LONG).show()
            }
            while(res.moveToNext()){
                list2.add(CarteBancaire(res.getInt(0),
                    res.getInt(1),
                    res.getInt(2),
                    res.getString(3),
                    res.getInt(4)))
            }
        }

        afficheCarte()
        afficheCompte()

        view.listeCompte.layoutManager = LinearLayoutManager(activity)
        view.listeCompte.addItemDecoration(DividerItemDecoration(activity,1))
        view.listeCompte.adapter = ListCarteAdapter(list,list2)

        return view
    }

}