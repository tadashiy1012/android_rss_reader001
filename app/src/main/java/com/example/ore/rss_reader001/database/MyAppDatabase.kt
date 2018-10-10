package com.example.ore.rss_reader001.database

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = MyAppDatabase.NAME, version = MyAppDatabase.VERSION)
class MyAppDatabase {
    companion object {
        const val NAME: String ="AppDatabase"
        const val VERSION = 1
    }
}