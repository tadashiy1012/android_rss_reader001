package com.example.ore.rss_reader001.controller

import com.example.ore.rss_reader001.event.RssEvent
import org.greenrobot.eventbus.EventBus

class RssUseCase {

    public fun requestGetRss() {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.GET_FEED))
    }

    public fun requestGetUrls() {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.GET_URL))
    }

    public fun requestSetUrl() {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.SET_URL))
    }

    public fun requestUnsetUrl() {
        EventBus.getDefault().post(RssEvent(RssEvent.TYPE.UNSET_URL))
    }

}