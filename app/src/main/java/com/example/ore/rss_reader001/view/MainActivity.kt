package com.example.ore.rss_reader001.view

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.ore.rss_reader001.MyStateStore
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
import kotlin.math.log

const val TAG = "ore"

class MainActivity : AppCompatActivity() {

    private val stateStore: MyStateStore by inject()
    private val useCase: RssUseCase by inject()
    private var rViewAdapter: FeedRViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, ">>onCreate")
        Log.d(TAG, "" + stateStore)
        setContentView(R.layout.activity_main)
        setSupportActionBar(this.findViewById(R.id.toolbar1))
        val dataLs: MutableList<FeedRViewItemData> = if (stateStore.dataList == null) {
            mutableListOf()
        } else {
            stateStore.dataList!!
        }
        val feedRview = findViewById<RecyclerView>(R.id.feedrview)
        feedRview.layoutManager = LinearLayoutManager(this)
        rViewAdapter = FeedRViewAdapter(dataLs)
        feedRview.adapter = rViewAdapter
        rViewAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, ">>onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, ">>onStart")
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, ">>onStop")
        EventBus.getDefault().unregister(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, ">>onResume")
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
                intent.setFlags(FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                true
            }
            R.id.reload -> {
                useCase.requestGetRss()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return result
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, ">>onSaveInstanceState")
        stateStore.dataList = rViewAdapter?.dataList
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, ">>onRestoreInstanceState")
    }

    fun createData(feedList: MutableList<SyndFeed>): MutableList<FeedRViewItemData> {
        var ls: MutableList<FeedRViewItemData> = arrayListOf()
        for (feed in feedList) {
            for (entry in feed.entries) {
                ls.add(FeedRViewItemData(feed.title, entry))
            }
        }
        ls.sortWith(Comparator {
            a, b -> a.entry?.publishedDate?.time!!.compareTo(
                b.entry?.publishedDate?.time!!)
        })
        ls.reverse()
        return ls
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEvent(event: RssEvent) {
        Log.d(TAG, "[onEvent@MainActivity]")
        when (event.eventType) {
            RssEvent.TYPE.GET_FEED -> {
                event.getRssFeeds().done(DoneCallback {
                    rViewAdapter?.updateList(createData(it))
                    println(">getRssFeeds done")
                })
            }
        }
    }

}
