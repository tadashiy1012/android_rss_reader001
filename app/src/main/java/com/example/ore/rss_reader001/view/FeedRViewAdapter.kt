package com.example.ore.rss_reader001.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ore.rss_reader001.R
import com.rometools.rome.feed.synd.SyndEntry
import com.rometools.rome.feed.synd.SyndFeed
import kotlinx.android.synthetic.main.feed_rview_row.view.*
import org.koin.standalone.KoinComponent
import org.w3c.dom.Text

class FeedRViewAdapter(list: MutableList<FeedRViewItemData>):
        RecyclerView.Adapter<FeedRViewAdapter.Companion.MyViewHolder>(), KoinComponent {

    var list: MutableList<FeedRViewItemData> = mutableListOf()

    init {
        this.list = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.feed_rview_row, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.title.setText(list.get(p1).entry?.title)
        p0.feedTitle.setText(list[p1].feedTitle)
    }

    companion object {
        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val title = itemView.findViewById<TextView>(R.id.title1)
            var feedTitle = itemView.findViewById<TextView>(R.id.feedtitle)
        }
    }

}