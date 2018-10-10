package com.example.ore.rss_reader001.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.ore.rss_reader001.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }

}
