package com.example.ore.rss_reader001.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.ore.rss_reader001.R
import com.example.ore.rss_reader001.controller.RssUseCase
import com.example.ore.rss_reader001.database.table.FeedUrl
import com.example.ore.rss_reader001.database.table.FeedUrl_Table.id
import com.example.ore.rss_reader001.database.table.FeedUrl_Table.url
import com.example.ore.rss_reader001.event.RssEvent
import com.raizlabs.android.dbflow.kotlinextensions.from
import com.raizlabs.android.dbflow.kotlinextensions.list
import com.raizlabs.android.dbflow.kotlinextensions.select
import com.raizlabs.android.dbflow.kotlinextensions.where
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jdeferred2.DoneCallback
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val useCase: RssUseCase by inject()
    private var dataLs: MutableList<FeedRViewItemData> = mutableListOf()
    private var feedRview: RecyclerView? = null
    private var rViewAdapter: FeedRViewAdapter? = null

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(this.findViewById(R.id.toolbar1))
        feedRview = findViewById(R.id.feedrview)
        feedRview?.layoutManager = LinearLayoutManager(this)
        rViewAdapter = FeedRViewAdapter(dataLs)
        feedRview?.swapAdapter(rViewAdapter, false)
    }

    override fun onResume() {
        super.onResume()
        useCase.requestGetRss()
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEvent(event: RssEvent) {
        println("[onEvent@MainActivity]")
        println(event.eventType)
        when (event.eventType) {
            RssEvent.TYPE.GET_FEED -> {
                event.getRssFeeds().done(DoneCallback {
                    dataLs.clear()
                    for (feed in it) {
                        for (entry in feed.entries) {
                            dataLs.add(FeedRViewItemData(feed.title, entry))
                            println(entry.publishedDate.time)
                        }
                    }
                    dataLs.sortWith(Comparator {
                        a, b -> a.entry?.publishedDate?.time!!.compareTo(
                            b.entry?.publishedDate?.time!!)
                    })
                    dataLs.reverse()
                    rViewAdapter?.notifyDataSetChanged()
                    println(">getRssFeeds done")
                })
            }
        }
    }

}
