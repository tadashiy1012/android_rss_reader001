package com.example.ore.rss_reader001.util

import android.support.v7.util.DiffUtil
import com.example.ore.rss_reader001.view.FeedRViewItemData

class MyDiffUtilCallback(val old: MutableList<FeedRViewItemData>, val new: MutableList<FeedRViewItemData>) : DiffUtil.Callback() {


    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areContentsTheSame(p0: Int, p1: Int): Boolean {
        return old.get(p0).equals(new.get(p1))
    }

    override fun areItemsTheSame(p0: Int, p1: Int): Boolean {
        return old[p0].feedTitle == new[p1].feedTitle
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }

}