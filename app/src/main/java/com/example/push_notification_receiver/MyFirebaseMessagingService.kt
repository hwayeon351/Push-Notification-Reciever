package com.example.push_notification_receiver

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        createNotificationChannel()

        val type = message.data["type"]
            ?.let { NotificationType.valueOf(it) }
        val title = message.data["title"]
        val content = message.data["content"]

        type ?: return

        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, content))
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = CHANNEL_DESCRIPTION

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    @SuppressLint("RemoteViewLayout")
    private fun createNotification(
        type: NotificationType,
        title: String?,
        content: String?
    ): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", "${type.title} 타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(this, type.id, intent, FLAG_UPDATE_CURRENT)

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        when(type) {
            NotificationType.NORMAL -> Unit
            NotificationType.EXPANDABLE -> {
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "\uD83D\uDE00 \uD83D\uDE03 \uD83D\uDE04 \uD83D\uDE01 " +
                                    "\uD83D\uDE06 \uD83D\uDE05 \uD83D\uDE02 \uD83E\uDD23 " +
                                    "\uD83E\uDD72 ☺️ \uD83D\uDE0A \uD83D\uDE07 " +
                                    "\uD83D\uDE42 \uD83D\uDE43 \uD83D\uDE09 \uD83D\uDE0C " +
                                    "\uD83D\uDE0D \uD83E\uDD70 \uD83D\uDE18 \uD83D\uDE17 " +
                                    "\uD83D\uDE19 \uD83D\uDE1A \uD83D\uDE0B \uD83D\uDE1B " +
                                    "\uD83D\uDE1D \uD83D\uDE1C \uD83E\uDD2A \uD83E\uDD28 " +
                                    "\uD83E\uDDD0 \uD83E\uDD13 \uD83D\uDE0E \uD83D\uDC7A " +
                                    "\uD83E\uDD21 \uD83D\uDCA9 \uD83D\uDC7B \uD83D\uDC80 ☠️" +
                                    "☠️ \uD83D\uDC7D \uD83D\uDC7E \uD83E\uDD16 \uD83C\uDF83 " +
                                    "\uD83D\uDE3A \uD83D\uDE38 \uD83D\uDE39 \uD83D\uDE3B " +
                                    "\uD83D\uDE3C \uD83D\uDE3D \uD83D\uDE40 \uD83D\uDE3F " +
                                    "\uD83D\uDE3E"
                        )
                )

            }
            NotificationType.CUSTOM -> {
                notificationBuilder
                    .setStyle(NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(
                        RemoteViews(
                            packageName,
                            R.layout.view_custom_notification
                        ).apply {
                            setTextViewText(R.id.title, title)
                            setTextViewText(R.id.message, content)
                        }
                    )
            }
        }

        return notificationBuilder.build()
    }

    companion object {
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel Id"
    }
}