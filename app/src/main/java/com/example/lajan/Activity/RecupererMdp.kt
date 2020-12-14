package com.example.lajan.Activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.lajan.Bdd.DatabaseHelper
import com.example.lajan.R
import kotlinx.android.synthetic.main.activity_recuperer_mdp.*

class RecupererMdp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperer_mdp)
        createNotif()

        btn_confirmeMdp.setOnClickListener {
            sendNotif()
        }
    }

    val databaseHandler: DatabaseHelper = DatabaseHelper(this)
    //val mdpSend = databaseHandler.getMdp(mdp_oublier_ed.text.toString().trim())

    var CHANNEL_ID2 = "channel_test"
    var notificationId = 102
    var CHANNEL_NAME = "ANDROID_CHANNEL"


    private fun createNotif()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {

            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID2,CHANNEL_NAME,importance)
            val notificationsManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationsManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotif()
    {
        val mdpSend = databaseHandler.getMdp(mdp_oublier_ed.text.toString().trim())
        val builder = NotificationCompat.Builder(this,CHANNEL_ID2)
            .setSmallIcon(R.drawable.mastercard)
            .setContentTitle("Mot de Passe")
            .setContentText(mdpSend)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId,builder.build())
        }
    }
}