package com.example.ore.rss_reader001.view

import com.rometools.rome.feed.synd.SyndEntry

class FeedRViewItemData(feedTitle: String, entry: SyndEntry) {
    var feedTitle: String? = null
    var entry: SyndEntry? = null
    init {
        this.feedTitle = feedTitle
        this.entry = entry
    }
}