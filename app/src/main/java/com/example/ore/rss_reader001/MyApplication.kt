package com.example.ore.rss_reader001

import com.example.ore.rss_reader001.controller.RssUseCase
import com.example.ore.rss_reader001.model.RssLogic
import com.raizlabs.android.dbflow.config.FlowManager
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.module

class MyApplication : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(this.appModule))
        FlowManager.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }

    private val appModule = module {
        factory { RssUseCase() }
        single { MyStateStore() }
    }

}