package com.example.ore.rss_reader001

import com.example.ore.rss_reader001.view.FeedRViewAdapter
import com.example.ore.rss_reader001.view.FeedRViewItemData

class MyStateStore {
    var dataList: MutableList<FeedRViewItemData>? = null
    var adapter: FeedRViewAdapter? = null
}