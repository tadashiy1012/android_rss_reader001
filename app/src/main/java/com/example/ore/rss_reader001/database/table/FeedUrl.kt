package com.example.ore.rss_reader001.database.table

import com.example.ore.rss_reader001.database.MyAppDatabase
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel

@Table(name = "feed_urls", database = MyAppDatabase::class)
class FeedUrl: BaseModel() {

    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column
    var url: String = ""

}