package com.example.ore.rss_reader001.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ore.rss_reader001.R
import com.example.ore.rss_reader001.controller.RssUseCase
import com.example.ore.rss_reader001.database.table.FeedUrl
import com.example.ore.rss_reader001.database.table.FeedUrl_Table.id
import com.example.ore.rss_reader001.database.table.FeedUrl_Table.url
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.list
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.kotlinextensions.where
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val useCase: RssUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar1))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val result = when (item!!.itemId) {
            R.id.setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return result
    }

}
