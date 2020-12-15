package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.R
import kotlinx.android.synthetic.main.fragment_crediter.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CrediterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CrediterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_crediter, container, false)

        //Récupération des données envoyées du RecyclerView
        val args = arguments
        val dataSolde = args!!.getDouble("solde",0.0)
        val dataIdCpt = args!!.getInt("idCpt",0)

        val db = DatabaseHelper(activity!!)

        view.crediter_btn.setOnClickListener{
            val resultat = additionner(view.crediter.text.toString().toDouble(), dataSolde)
            val msgRecap = db.crediter(resultat, dataIdCpt)
            db.creerRecap(dataIdCpt, msgRecap)
            Toast.makeText(activity, "Compte crédité", Toast.LENGTH_SHORT).show()
        }

        return view
    }
    fun additionner(sommeCrediter: Double, solde : Double) : Double{
        return sommeCrediter + solde
    }

}