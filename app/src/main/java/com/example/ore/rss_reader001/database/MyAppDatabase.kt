package com.example.ore.rss_reader001.database

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = MyAppDatabase.NAME, version = MyAppDatabase.VERSION, generatedClassSeparator = "_")
object MyAppDatabase {
    const val NAME: String ="AppDatabase"
    const val VERSION = 1
}