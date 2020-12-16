package com.example.lajan.Class

data class CarteBancaire(
    var idCb : Int = -1,
    var numeroCarte : Long,
    var dateExpiration : Int,
    var typeCarte : String,
    var keyUserCarte : Int
)
