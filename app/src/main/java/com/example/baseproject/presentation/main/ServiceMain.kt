package com.example.baseproject.presentation.main

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import android.os.Build
import androidx.annotation.RequiresApi

class ServiceMain : Service() {
    private var count = 0
    companion object{
        private const val MAX_COUNT = 7
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i("=================", "ServiceMain is onBind")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notiBuidler = Notification.Builder(this@ServiceMain, createNotificationChannel("123456","Service"))
            notiBuidler.apply {
                setContentTitle("Demo service O")
                setContentText("Service O is running ")
                setAutoCancel(true)
            }
            startForeground(111, notiBuidler.build())
        } else {
            val notiBuidler = Notification.Builder(this@ServiceMain)
            notiBuidler.apply {
                setContentTitle("Demo service")
                setContentText("Service is running")
                setAutoCancel(true)
            }
            startForeground(111, notiBuidler.build())
        }

        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post(object : Runnable {
            override fun run() {
                minusOneSecond()
                mainHandler.postDelayed(this, 3000)
            }
        })
        return START_NOT_STICKY
    }

    @SuppressLint("LogNotTimber")
    private fun minusOneSecond() {
        Log.i("=================", "ServiceMain is running")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("=================", "ServiceMain is onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("=================", "ServiceMain is onDestroy")
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i("=================", "ServiceMain is onRebind")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.i("=================", "ServiceMain is onLowMemory")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(channelId: String, channelName: String): String{
        val chan = NotificationChannel(channelId,
            channelName, NotificationManager.IMPORTANCE_NONE)
        chan.lightColor = Color.BLUE
        chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }

}