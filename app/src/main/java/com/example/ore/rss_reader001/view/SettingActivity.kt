package com.example.ore.rss_reader001.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import com.example.ore.rss_reader001.R
import com.example.ore.rss_reader001.controller.RssUseCase
import com.example.ore.rss_reader001.event.RssEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jdeferred2.DoneCallback
import org.jdeferred2.FailCallback
import org.koin.android.ext.android.inject

class SettingActivity : AppCompatActivity() {

    val useCase: RssUseCase by inject()
    var dataLs: MutableList<String> = mutableListOf()
    var recyclerView: RecyclerView? = null
    var rViewAdapter: MyRViewAdapter? = null
    var inUrl: EditText? = null
    var addBtn: Button? = null

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
        setContentView(R.layout.activity_setting)
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        recyclerView = findViewById<RecyclerView>(R.id.rview1)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        rViewAdapter = MyRViewAdapter(dataLs)
        recyclerView?.swapAdapter(rViewAdapter, false)
        inUrl = findViewById(R.id.infeedurl)
        addBtn = findViewById(R.id.addbtn)
        addBtn?.setOnClickListener {
            val str = inUrl?.text.toString()
            useCase.requestSetUrl(str)
        }
    }

    override fun onResume() {
        super.onResume()
        useCase.requestGetUrls()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val result: Boolean = when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return result
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEvent(event: RssEvent) {
        println("[onEvent@SettingActivity]")
        println(event.eventType)
        when (event.eventType) {
            RssEvent.TYPE.GET_URL -> {
                event.getRssFeedUrls().done(DoneCallback {
                    println(it.size)
                    println(">get urls done")
                    dataLs.clear()
                    dataLs.addAll(it)
                    rViewAdapter?.notifyDataSetChanged()
                }).fail(FailCallback {
                    println(it)
                })
            }
            RssEvent.TYPE.SET_URL -> {
                event.setRssFeedUrl()?.done(DoneCallback {
                    println(it)
                    println(">set url done")
                    useCase.requestGetUrls()
                })?.fail(FailCallback {
                    println(it)
                })
            }
            RssEvent.TYPE.UNSET_URL -> {
                event.unsetRssFeedUrl()?.done(DoneCallback {
                    println(it)
                    println(">unset url done")
                    useCase.requestGetUrls()
                })?.fail(FailCallback {
                    println(it)
                })
            }
        }
    }

}
