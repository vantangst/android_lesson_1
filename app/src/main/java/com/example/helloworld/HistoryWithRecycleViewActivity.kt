package com.example.helloworld

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.helloworld.model.CalculatorResult


class HistoryWithRecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setupUI()
    }

    private fun setupUI() {
        // actionbar
        supportActionBar?.title = "History"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val listData = intent.getSerializableExtra("key_result") as? List<CalculatorResult> ?: listOf()
        val lvHistory = findViewById<RecyclerView>(R.id.rvHistory)
        val adapter = HistoryRecycleAdapter(listData, this)
        lvHistory.adapter = adapter
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