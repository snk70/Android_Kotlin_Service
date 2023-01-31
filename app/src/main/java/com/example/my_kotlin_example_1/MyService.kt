package com.example.my_kotlin_example_1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import java.time.LocalDate
import java.time.LocalTime

class MyService : Service() {
    private val chan_id = "service"


    companion object {
        fun startService(context: Context, message: String) {
            val intent = Intent(context, MyService::class.java)
            intent.putExtra("input", message)
            ContextCompat.startForegroundService(context, intent)
        }

        fun stopService(context: Context) {
            val intent = Intent(context, MyService::class.java)
            context.stopService(intent)
        }
    }


    override fun onCreate() {

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val input = intent?.getStringExtra("input")
        createNotification()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 5, Intent(this,MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE)


/*        val YesIntent=Intent(this,MainActivity::class.java)
        YesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val YesPendingIntent=PendingIntent.getActivity(this,5,YesIntent,PendingIntent.FLAG_IMMUTABLE)*/




        val notification =
            NotificationCompat.Builder(this, chan_id).setContentTitle(LocalTime.now().toString())
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_alaram)
                .setContentIntent(pendingIntent)
//                .addAction(R.drawable.ic_alaram,"Titleeee",YesPendingIntent)
                .build()

        startForeground(1, notification)

        val tst1 = Toast.makeText(applicationContext, "dfgdg", Toast.LENGTH_SHORT)
        tst1.show()

        val myInt = Intent(applicationContext, MyService::class.java)
        startService(myInt)

        return START_NOT_STICKY
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val servicechannel = NotificationChannel(
                chan_id,
                "Foreground service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService((NotificationManager::class.java))
            manager!!.createNotificationChannel(servicechannel)
        }
    }


    override fun onBind(intent: Intent): IBinder? {

        return null
    }

    override fun onDestroy() {

    }

}