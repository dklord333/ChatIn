package com.example.chatin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class Firebasenotification : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("this is token", "token: $token")

    }

  override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title ?: "DEFAULT"
        val body = remoteMessage.notification?.body ?: "DEFAULT BODY"
        shownotification(title, body)

    }


    fun shownotification(title: String, body: String) {

        val customid = "Defualtid"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                Log.w("FCM", "Notification permission not granted")
                return
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val newchannel =
                NotificationChannel(customid, "custom", NotificationManager.IMPORTANCE_HIGH)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(newchannel)
        }


        val builder=NotificationCompat.Builder(this,customid).setSmallIcon(R.drawable.pic1).setContentTitle(title).setContentText(body).setAutoCancel(true)
        NotificationManagerCompat.from(this).notify(1,builder.build())


    }
}