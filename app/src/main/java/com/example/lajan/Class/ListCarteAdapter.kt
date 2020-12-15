package com.example.lajan.Class

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.lajan.Fragment.CrediterFragment
import com.example.lajan.Fragment.DepenseFragment
import com.example.lajan.Fragment.HistoriqueTransaction
import com.example.lajan.Fragment.VirementFragment
import com.example.lajan.R

class ListCarteAdapter(var list:List<Compte>, var listCB:List<CarteBancaire>) : RecyclerView.Adapter<ListCarteAdapter.ListViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_liste_carte, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listCpt = list[position]
        val listCB = listCB[position]

        holder.solde.text = listCpt.solde.toString()
        holder.decouvert.text = listCpt.decouvert.toString()

        holder.idCarte.text = listCB.idCb.toString()
        holder.numeroCarte.text = listCB.numeroCarte.toString()
        holder.dateExpiration.text = listCB.dateExpiration.toString()
        holder.type.text = listCB.typeCarte


        //Liste de carte cliquable
        holder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                PopupMenu(holder.itemView.context, holder.itemView).apply {
                    inflate(R.menu.menu_fct_compte)
                    show()
                    setOnMenuItemClickListener {
                        val solde = listCpt.solde
                        val idCpt = listCpt.idCompte
                        val idUser = listCpt.keyUserCpt

                        val activity = v!!.context as AppCompatActivity

                        val bundle = Bundle()
                        bundle.putDouble("solde", solde)
                        bundle.putInt("idCpt", idCpt)
                        bundle.putInt("idUser",idUser)

                        when(it.itemId){
                            R.id.menu_fct_virement ->{
                                val pageVirement = VirementFragment()
                                pageVirement.setArguments(bundle)
                                activity.supportFragmentManager.beginTransaction().replace(R.id.container, pageVirement).commit()
                                true
                            }
                            R.id.menu_fct_depense ->{
                                val pageDepense = DepenseFragment()
                                pageDepense.setArguments(bundle)
                                activity.supportFragmentManager.beginTransaction().replace(R.id.container, pageDepense).commit()
                                true
                            }
                            R.id.menu_fct_crediter ->{
                                val pageCrediter = CrediterFragment()
                                pageCrediter.setArguments(bundle)
                                activity.supportFragmentManager.beginTransaction().replace(R.id.container, pageCrediter).commit()
                                true
                            }
                            R.id.menu_fct_recap ->{
                                val historique = HistoriqueTransaction()
                                historique.setArguments(bundle)
                                activity.supportFragmentManager.beginTransaction().replace(R.id.container, historique).commit()
                                true
                            }
                            else -> false
                        }


                    }
                }
            }
        })
    }

    override fun getItemCount() = list.size

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        //Déclaration des variables à affecter des comptes de l'utilisateur
        val solde : TextView = itemView.findViewById(R.id.list_solde)
        val decouvert : TextView = itemView.findViewById(R.id.list_decouvert)

        val idCarte : TextView = itemView.findViewById(R.id.list_idCarte)
        val numeroCarte : TextView = itemView.findViewById(R.id.list_numeroCarte)
        val dateExpiration : TextView = itemView.findViewById(R.id.list_dateExp)
        val type : TextView = itemView.findViewById(R.id.list_typeCarte)

    }

}