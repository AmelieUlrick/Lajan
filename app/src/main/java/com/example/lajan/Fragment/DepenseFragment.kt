package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.R
import kotlinx.android.synthetic.main.fragment_depense.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [DepenseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DepenseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_depense, container, false)

        //Récupération des données envoyées du RecyclerView
        val args = arguments
        val dataSolde = args!!.getDouble("solde",0.0)
        val dataIdCpt = args!!.getInt("idCpt",0)

        val db = DatabaseHelper(activity!!)


        view.depense_btn.setOnClickListener{

            val sommeDebiter = view.depense.text.toString().toDouble()
            val resultat = deduire(sommeDebiter,dataSolde)
            db.depense(resultat,dataIdCpt)
            val msg = "Dépense de $sommeDebiter euros"
            db.creerRecap(dataIdCpt,msg)

            Toast.makeText(activity, "Dépense enregistré", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    fun deduire(montantDeduire : Double, solde : Double) : Double{
        return solde - montantDeduire
    }

}