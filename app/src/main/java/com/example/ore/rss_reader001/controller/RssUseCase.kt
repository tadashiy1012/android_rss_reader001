package com.example.ore.rss_reader001.controller

import com.example.ore.rss_reader001.event.RssEvent
import org.greenrobot.eventbus.EventBus

class RssUseCase {

    public fun requestGetRss() {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.GET_FEED, null))
    }

    public fun requestGetUrls() {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.GET_URL, null))
    }

    public fun requestSetUrl(tgtUrl: String) {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.SET_URL, tgtUrl))
    }

    public fun requestUnsetUrl(tgtUrl: String) {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.UNSET_URL, tgtUrl))
    }

}