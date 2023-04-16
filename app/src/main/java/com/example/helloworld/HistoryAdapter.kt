package com.example.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class HistoryAdapter(listData: List<String>, context: Context) : BaseAdapter() {

    private var listData: List<String> = emptyList()
    private var context: Context

    init {
        this.listData = listData
        this.context = context
    }

    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(p0: Int): Any {
        return listData[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val item = listData[p0]
        var convertView = p1
        convertView = LayoutInflater.from(context).inflate(R.layout.history_item, p2, false)
        val tvTitle = convertView.findViewById<TextView>(R.id.tvTitle)
        tvTitle.setText(item)
        val tvTime = convertView.findViewById<TextView>(R.id.tvTime)
        tvTime.text = "20/03/2023"
        return convertView
    }
}