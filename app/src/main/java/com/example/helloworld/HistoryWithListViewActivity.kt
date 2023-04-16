package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class HistoryWithListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setupList()
    }

    private fun setupList() {
        val listData = intent.getStringArrayExtra("key_result")?.toList() ?: listOf<String>()
        val lvHistory = findViewById<ListView>(R.id.lvHistory)
        val adapter = HistoryAdapter(listData, this)
        lvHistory.setAdapter(adapter)
    }
}