package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.helloworld.model.CalculatorResult
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var tvInput: TextView
    var input1 = ""
    var input2 = ""
    var method = ""
    val listResult: ArrayList<CalculatorResult> = arrayListOf()

//    var resultLauncherNormal = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult(),
//        object : ActivityResultCallback<ActivityResult> {
//            override fun onActivityResult(result: ActivityResult?) {
//                // There are no request codes
//                val data: Intent? = result?.data
//                Log.e(
//                    "MainActivity",
//                    "Second activity callback: " + data?.getStringExtra("second_key_1")
//                )
//            }
//        })

    var resultLauncherLambda = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // There are no request codes
        val data: Intent? = result.data
        Log.e("MainActivity", "Second activity callback: " + data?.getStringExtra("second_key_1"))
    }

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
            val value = getResult()
            tvInput.text = value
            val calendar =  Calendar.getInstance()
            val formatter = SimpleDateFormat("dd/MM//yyyy hh:mm:ss")
            listResult.add(CalculatorResult(value, formatter.format(calendar.time)))
        }

        val btnSecondActivity = findViewById<Button>(R.id.btnSecondActivity)
        btnSecondActivity.setOnClickListener {
            goToSecondActivity()
        }
    }

    fun goToSecondActivity() {
        val intent = Intent(this, HistoryWithRecycleViewActivity::class.java)
        intent.putExtra("key_result", listResult)
//        startActivity(intent) // not return data
//        resultLauncherNormal.launch(intent) // return data
        resultLauncherLambda.launch(intent) // return data
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