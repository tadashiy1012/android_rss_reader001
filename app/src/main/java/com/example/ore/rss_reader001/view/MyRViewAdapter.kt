package com.example.ore.rss_reader001.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ore.rss_reader001.R

class MyRViewAdapter(private val list: MutableList<MyRViewItemData>): RecyclerView.Adapter<MyRViewAdapter.Companion.MyViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.my_rview_row, p0, false)
        val viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.url.setText(list[p1].url)
        p0.btn.setOnClickListener {
            println(list[p1].id)
        }
    }

    companion object {
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val url = itemView.findViewById<TextView>(R.id.rowTextView)
            val btn = itemView.findViewById<Button>(R.id.rowDelBtn)
        }
    }
}