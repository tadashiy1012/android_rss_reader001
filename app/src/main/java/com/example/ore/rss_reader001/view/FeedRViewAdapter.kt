package com.example.ore.rss_reader001.view

import android.content.Intent
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ore.rss_reader001.R
import com.example.ore.rss_reader001.util.MyDiffUtilCallback
import com.rometools.rome.feed.synd.SyndEntry
import org.koin.standalone.KoinComponent

class FeedRViewAdapter(dataList: MutableList<FeedRViewItemData>):
        RecyclerView.Adapter<FeedRViewAdapter.Companion.MyViewHolder>(), KoinComponent {

    var dataList: MutableList<FeedRViewItemData> = mutableListOf()

    init {
        this.dataList = dataList
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.feed_rview_row, p0, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.itemId = p1
        p0.itemEntry = dataList.get(p1).entry!!
        p0.title.setText(dataList.get(p1).entry?.title)
        p0.feedTitle.setText(dataList[p1].feedTitle)
    }

    fun updateList(newList: MutableList<FeedRViewItemData>) {
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(MyDiffUtilCallback(dataList, newList), true)
        dataList = newList
        result.dispatchUpdatesTo(this)
    }

    companion object {
        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var itemId: Int? = null
            var itemEntry: SyndEntry? = null
            val title = itemView.findViewById<TextView>(R.id.title1)
            var feedTitle = itemView.findViewById<TextView>(R.id.feedtitle)
            var container = itemView.findViewById<LinearLayout>(R.id.feedcontainer)
            init {
                container.setOnClickListener {
                    println("click! " + itemId.toString())
                    val intent = Intent(it.context, ContentActivity::class.java)
                    var content = ""
                    itemEntry?.contents!!.forEach {
                        content += it.value + "\n"
                    }
                    intent.putExtra("entryDate", itemEntry?.publishedDate?.time)
                    intent.putExtra("entryTitle", itemEntry?.title)
                    intent.putExtra("entryContent", content)
                    intent.putExtra("entryLink", itemEntry?.link)
                    it.context.startActivity(intent)
                }
            }
        }
    }

}