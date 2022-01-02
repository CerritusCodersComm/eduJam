package com.example.gdsc_hackathon.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.activities.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.gdsc_hackathon.dataModel.NotificationModel


/**
generates the notifications, attaches the notification created
with custom layout and shows it
 */

class NotificationService: FirebaseMessagingService() {

    private var notificationChannelName = "com.example.gdsc_hackathon"
    private val notificationChannelID = "69"
    private var notifications = ArrayList<NotificationModel>()


    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if(remoteMessage.notification != null)
        {
            generateNotification(
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )
            notifications.add(
                NotificationModel(
                    remoteMessage.notification!!.title!!,
                    remoteMessage.notification!!.body!!
                )
            )
            broadcastIntent(remoteMessage.notification!!.title!!, remoteMessage.notification!!.body!!)
        }

        super.onMessageReceived(remoteMessage)
    }
    private fun generateNotification(title:String, content:String){

        val intent = Intent(this,MainActivity::class.java )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        // channel id,channel name
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext,notificationChannelID)
            .setSmallIcon(R.drawable.primary_background)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title,content))

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(notificationChannelID
                ,notificationChannelName
                ,NotificationManager.IMPORTANCE_HIGH)

            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0,builder.build())
    }

    private fun getRemoteView(title: String, content: String): RemoteViews {

        val remoteView = RemoteViews("com.example.gdsc_hackathon", R.layout.notification_item)

        remoteView.setTextViewText(R.id.title,title)
        remoteView.setTextViewText(R.id.content,content)
        remoteView.setImageViewResource(R.id.notificationIcon, R.drawable.primary_background)

        return remoteView
    }
        private fun broadcastIntent(title: String, content: String) {
            val intent = Intent()
            intent.putExtra("title",title)
            intent.putExtra("content",content)
            intent.action = "com.myApp.CUSTOM_EVENT"
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        }
    }