package com.example.ore.rss_reader001.event

import com.example.ore.rss_reader001.model.RssLogic
import com.rometools.rome.feed.synd.SyndFeed
import org.jdeferred2.Promise

class RssEvent(eventType: RssEvent.TYPE, private val param: String?) {

    enum class TYPE {
        GET_FEED, GET_URL, SET_URL, UNSET_URL
    }

    public var eventType: RssEvent.TYPE? = null

    private val rssLogic = RssLogic()

    init {
        this.eventType = eventType
    }

    public fun getRssFeeds(): Promise<MutableList<SyndFeed>, String, Void> {
        return rssLogic.getFeeds()
    }

    public fun getRssFeedUrls(): Promise<MutableList<String>, String, Void> {
        return rssLogic.getFeedUrls()
    }

    public fun setRssFeedUrl(): Promise<Boolean, String, Void>? {
        return rssLogic.putFeedUrl(param!!)
    }

    public fun unsetRssFeedUrl(): Promise<Boolean, String, Void>? {
        return rssLogic.removeFeedUrl(param!!)
    }

}