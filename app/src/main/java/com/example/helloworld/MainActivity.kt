package com.example.helloworld

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tvInput: TextView
    var input1 = ""
    var input2 = ""
    var method = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("MainActivity", "onCreate" + " Ham main")
        setupUI()

    }

    fun setupUI() {
        tvInput = findViewById(R.id.tvInput)
        val btnNum1 = findViewById<Button>(R.id.btnNum1)
        btnNum1.setOnClickListener {
            Log.e("MainActivity", "Click num 1")
            val oldValue = tvInput.text.toString()
            tvInput.text = oldValue + "1"
            if (method == "") {
                input1 = input1 + "1"
            } else {
                input2 = input2 + "1"
            }
        }

        val btnNum2 = findViewById<Button>(R.id.btnNum2)
        btnNum2.setOnClickListener {
            Log.e("MainActivity", "Click num 2")
            val oldValue = tvInput.text.toString()
            tvInput.text = oldValue + "2"
            if (method.isEmpty()) {
                input1 = input1 + "2"
            } else {
                input2 = input2 + "2"
            }
        }

        val btnAC = findViewById<Button>(R.id.btnAc)
        btnAC.setOnClickListener {
            Log.e("MainActivity", "Click AC")
            input1 = ""
            input2 = ""
            method = ""
            tvInput.text = ""
        }

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            Log.e("MainActivity", "Click Add")
            val oldValue = tvInput.text.toString()
            tvInput.text = oldValue + " + "
            method = "+"

        }

        val btnResult = findViewById<Button>(R.id.btnResult)
        btnResult.setOnClickListener {
            tvInput.text = getResult()
        }
    }

    fun getResult(): String {
        var result = 0.0
        if (method == "+") {
            result = input1.toDouble() + input2.toDouble()
        } else if (method == "-") {
            result = input1.toDouble() - input2.toDouble()
        }
        val oldValue = tvInput.text.toString()
        return oldValue + " = " + result
    }
}