package com.example.ore.rss_reader001.model

import com.example.ore.rss_reader001.database.table.FeedUrl
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.rometools.rome.feed.synd.SyndFeed
import com.rometools.rome.io.SyndFeedInput
import com.rometools.rome.io.XmlReader
import org.jdeferred2.Deferred
import org.jdeferred2.DoneCallback
import org.jdeferred2.FailCallback
import org.jdeferred2.Promise
import org.jdeferred2.android.AndroidDeferredObject
import org.jdeferred2.impl.DefaultDeferredManager
import java.net.URL
import java.util.concurrent.Callable

class RssLogic {

    public fun putFeedUrl(urlstr: String): Promise<Boolean, String, Void>? {
        val deferred: Deferred<Boolean, String, Void> = AndroidDeferredObject()
        DefaultDeferredManager().`when`(Callable {
            val feedUrl = FeedUrl()
            feedUrl.url = urlstr
            feedUrl.insert()
            return@Callable true
        }).done(DoneCallback {
            deferred.resolve(it)
        }).fail(FailCallback {
            deferred.reject(it.message)
        })
        return deferred.promise()
    }

    public fun getFeedUrls(): Promise<MutableList<String>, String, Void> {
        val deferred: Deferred<MutableList<String>, String, Void> = AndroidDeferredObject()
        DefaultDeferredManager().`when`(Callable {
            var ls = mutableListOf<String>()
            val urls = SQLite.select()
                    .from(FeedUrl::class.java)
                    .queryList()
            for (feedUrl in urls) {
                ls.add(feedUrl.url)
            }
            return@Callable ls
        }).done(DoneCallback {
            deferred.resolve(it)
        }).fail(FailCallback {
            deferred.reject(it.message)
        })
        return deferred.promise()
    }

    public fun getFeeds(): Promise<MutableList<SyndFeed>, String, Void> {
        val deferred: Deferred<MutableList<SyndFeed>, String, Void> = AndroidDeferredObject()
        DefaultDeferredManager().`when`(Callable {
            var ls = mutableListOf<SyndFeed>()
            val urls = SQLite.select()
                    .from(FeedUrl::class.java)
                    .queryList()
            for (feedUrl in urls) {
                val url: URL = URL(feedUrl.url)
                val input: SyndFeedInput = SyndFeedInput()
                val feed: SyndFeed = input.build(XmlReader(url))
                ls.add(feed)
            }
            return@Callable ls
        }).done(DoneCallback {
            deferred.resolve(it)
        }).fail(FailCallback {
            deferred.reject(it.message)
        })
        return deferred.promise()
    }

}