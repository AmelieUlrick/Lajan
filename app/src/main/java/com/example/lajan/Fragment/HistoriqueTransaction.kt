package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.Adapter.CustomHistorique
import com.example.lajan.Class.Recapitulatif
import com.example.lajan.R



/**
 * A simple [Fragment] subclass.
 * Use the [HistoriqueTransaction.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoriqueTransaction : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_historique_transaction, container, false)

        //Récupération des données envoyées du RecyclerView
        val args = arguments
        val dataIdCpt = args!!.getInt("idCpt",0)

        val db = DatabaseHelper(activity!!)
        var list = mutableListOf<Recapitulatif>()

        fun afficheHistorique(){
            list.clear()
            val res = db.affichRecapCompte(dataIdCpt)
            if(res.count == 0){
                Toast.makeText(activity, "no record :", Toast.LENGTH_LONG).show()
            }
            while (res.moveToNext()){
                list.add(
                    Recapitulatif(res.getInt(0),
                        res.getString(1),
                        res.getInt(2))
                )
            }
        }

        val listView : ListView = view.findViewById(R.id.listeHistorique)
        afficheHistorique()
        val adapter = CustomHistorique(activity!!,R.layout.custom_liste_historique,list)
        listView.adapter = adapter

        return view
    }


}