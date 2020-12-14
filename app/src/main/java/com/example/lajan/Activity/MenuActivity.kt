package com.example.lajan.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lajan.Fragment.HomeFragment
import com.example.lajan.Fragment.ProfilFragment
import com.example.lajan.Fragment.CarteFragment
import com.example.lajan.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    val homeFragment = HomeFragment()
    val profilFragment = ProfilFragment()
    val CarteFragment = CarteFragment()

}