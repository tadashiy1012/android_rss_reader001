package com.example.ore.rss_reader001.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ore.rss_reader001.R
import com.example.ore.rss_reader001.controller.RssUseCase
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val useCase: RssUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
