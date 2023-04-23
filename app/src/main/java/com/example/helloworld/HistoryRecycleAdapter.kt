package com.example.helloworld

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.model.CalculatorResult
import java.text.SimpleDateFormat
import java.util.*

class HistoryRecycleAdapter(val listData: List<CalculatorResult>, val context: Context): RecyclerView.Adapter<HistoryRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val convertView = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(convertView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listData[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var tvTitle: TextView
        private var tvTime: TextView

        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            tvTime = view.findViewById(R.id.tvTime)
        }

        fun bindData(item: CalculatorResult) {
            tvTitle.text = item.result
            tvTime.text = item.time
        }
    }
}