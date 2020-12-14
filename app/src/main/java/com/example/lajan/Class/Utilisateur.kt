package com.example.lajan.Class

data class Utilisateur (
        var id_utilisateur: Int = -1,
        var Prenom: String? = null,
        var Nom: String? = null,
        var adresse_mail: String? = null,
        var login:String? = null,
        var mdp: String? = null,
){
    companion object {

    }
}