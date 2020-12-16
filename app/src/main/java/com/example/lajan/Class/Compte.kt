package com.example.lajan.Class

data class Compte(
    var idCompte : Int = -1,
    var solde : Double,
    var decouvert : Double,
    var keyCarte : Int,
    var keyUserCpt : Int,
    var name_cpt : String
)
