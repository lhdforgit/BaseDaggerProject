package com.example.baseproject.presentation.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.baseproject.R
import com.example.baseproject.data.retrofit.resource.StatusNetwork
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var viewModel: MainViewModel? = null

    private lateinit var startBt: Button
    private lateinit var stopBt: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(viewModelStore, factory).get(MainViewModel::class.java)

        startBt = findViewById(R.id.start_bt)

        stopBt = findViewById(R.id.stop_bt)

        startBt.setOnClickListener {
            handleStartService()
        }

        stopBt.setOnClickListener {
            val intent = Intent(this@MainActivity, ServiceMain::class.java)
            stopService(intent)
        }

        lifecycleScope.launch {
            viewModel?.getListPost()?.observe(this@MainActivity, {
                it?.apply {
                    when (statusNetwork) {
                        StatusNetwork.LOADING -> {

                        }
                        StatusNetwork.SUCCESS -> {

                        }
                        else -> {

                        }
                    }
                }
            })
        }


    }

    private fun handleStartService() {
        val intent = Intent(this@MainActivity, ServiceMain::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}