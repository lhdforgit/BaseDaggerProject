package com.example.baseproject.presentation.main

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(viewModelStore, factory).get(MainViewModel::class.java)
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
}