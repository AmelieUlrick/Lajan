package com.example.lajan.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.R
import kotlinx.android.synthetic.main.fragment_virement.*
import kotlinx.android.synthetic.main.fragment_virement.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [VirementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VirementFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_virement, container, false)

        val args = arguments
        val dataSolde = args!!.getDouble("solde",0.0)
        val dataIdCpt = args!!.getInt("idCpt",0)
        val dataIdUser = args!!.getInt("idUser", 0)

        val db = DatabaseHelper(activity!!)

        val listIntCpt = db.afficheIntCpt(dataIdUser,dataIdCpt)

        val spinner = view.findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(activity!!,
                android.R.layout.simple_spinner_item, listIntCpt)
            spinner.adapter = adapter


            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    Toast.makeText(activity, getString(R.string.app_name) + " " + listIntCpt[position], Toast.LENGTH_SHORT).show()
                    afficheSpinner.text = listIntCpt[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }


            }

            val montant = view.findViewById<EditText>(R.id.virement_montant)
            view.virement_btn.setOnClickListener {

                Toast.makeText(activity!!, "regarde : " + afficheSpinner.text, Toast.LENGTH_LONG).show()
                //Débiter le compte qui vire l'argent
                val resDebit = debiter(montant.text.toString().toDouble(),dataSolde)
                db.depense(resDebit,dataIdCpt)

                //Chercher le solde du compte chosit dans le spinner
                //Créditer le solde du montant entrer et update le compte
                val trans = db.afficheA(dataIdUser ,afficheSpinner.text.toString())
                val soldeACrediter = db.recupSoldeCompte(trans)
                val resCredit = crediterCompte(montant.text.toString().toDouble(),soldeACrediter)
                db.crediter(resCredit,trans)

            }

        }

        return view
    }

    fun debiter(sommeADebiter:Double,solde:Double):Double{
        return solde - sommeADebiter
    }
    fun crediterCompte(sommeACrediter:Double,solde:Double): Double {
        return sommeACrediter + solde
    }

}