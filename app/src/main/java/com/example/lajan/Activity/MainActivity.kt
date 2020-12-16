package com.example.lajan.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        imgDynamique.animate().apply {
            duration = 1000
            rotationXBy(360f)
            rotationX(360f)
        }.start()

        Handler().postDelayed({
            val intent = Intent(this, ConnexionActivity::class.java)
            startActivity(intent)
            finish()
        },2500)

        val animMain = AnimationUtils.loadAnimation(this, R.anim.animation)
        val secondImg = findViewById<ImageView>(R.id.dynamiqueMain)

        secondImg.startAnimation(animMain)
    }
}