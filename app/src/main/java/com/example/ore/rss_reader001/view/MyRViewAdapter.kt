package com.example.ore.rss_reader001.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ore.rss_reader001.R
import com.example.ore.rss_reader001.controller.RssUseCase
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class MyRViewAdapter(val dataList: MutableList<String>):
        RecyclerView.Adapter<MyRViewAdapter.Companion.MyViewHolder>(), KoinComponent {

    private val useCase: RssUseCase by inject()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.my_rview_row, p0, false)
        val viewHolder = MyViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.url.setText(dataList[p1])
        p0.btn.setOnClickListener {
            println(p1)
            val url = dataList[p1]
            useCase.requestUnsetUrl(url)
        }
    }

    fun getCurrentDataList(): MutableList<String> {
        return dataList
    }

    companion object {
        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val url = itemView.findViewById<TextView>(R.id.rowTextView)
            val btn = itemView.findViewById<Button>(R.id.rowDelBtn)
        }
    }
}