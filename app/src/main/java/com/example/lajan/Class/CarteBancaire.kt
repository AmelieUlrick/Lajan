package com.example.lajan.Class

data class CarteBancaire(
    var idCb : Int = -1,
    var numeroCarte : Int,
    var dateExpiration : Int,
    var typeCarte : String,
    var keyUserCarte : Int
)
