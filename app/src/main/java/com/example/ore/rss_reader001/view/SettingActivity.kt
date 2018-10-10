package com.example.ore.rss_reader001.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import com.example.ore.rss_reader001.R

class SettingActivity : AppCompatActivity() {

    var dataLs: MutableList<MyRViewItemData> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        val recyclerView = findViewById<RecyclerView>(R.id.rview1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRViewAdapter(dataLs)
        for (i in 0..9) {
            dataLs.add(MyRViewItemData(i, "http://www.example.com"))
        }
    }

}
