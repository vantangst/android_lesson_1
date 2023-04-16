package com.example.helloworld

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class HistoryWithRecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupUI()
    }

    private fun setupUI() {
        // actionbar
        supportActionBar?.setTitle("History")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listData = intent.getStringArrayExtra("key_result")?.toList() ?: listOf<String>()
        val lvHistory = findViewById<RecyclerView>(R.id.rvHistory)
        val adapter = HistoryRecycleAdapter(listData, this)
        lvHistory.setAdapter(adapter)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}