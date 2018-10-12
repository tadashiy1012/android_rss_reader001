package com.example.ore.rss_reader001.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.example.ore.rss_reader001.R
import java.text.SimpleDateFormat
import java.util.*

class ContentActivity : AppCompatActivity() {

    var date: TextView? = null
    var title: TextView? = null
    var content: TextView? = null
    var link: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        setSupportActionBar(findViewById(R.id.toolbar3))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        date = findViewById(R.id.date1)
        title = findViewById(R.id.title1)
        content = findViewById(R.id.content1)
        link = findViewById(R.id.link1)
        val intent = this.intent
        val dt = Date(intent.getLongExtra("entryDate", 0))
        val formatter = SimpleDateFormat("yyyy/MM/dd h:mm")
        date?.text = formatter.format(dt)
        title?.text = intent.getStringExtra("entryTitle")
        content?.text = intent.getStringExtra("entryContent")
        link?.text = intent.getStringExtra("entryLink")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val result = when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return result
    }

}
