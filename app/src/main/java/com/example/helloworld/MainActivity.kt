package com.example.helloworld

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.helloworld.model.CalculatorResult
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    lateinit var tvInput: TextView
    var input1 = ""
    var input2 = ""
    var method = ""
    var listResult: ArrayList<CalculatorResult> = arrayListOf()

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

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("key_result", listResult)
        super.onSaveInstanceState(outState)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        listResult = savedInstanceState.getSerializable("key_result") as? ArrayList<CalculatorResult> ?: arrayListOf()
        tvInput.text = listResult.last().result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("MainActivity", "onCreate" + " Ham main")
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        // custom overflow icon
//        toolbar.overflowIcon = getDrawable(R.drawable.ic_baseline_settings_24)
        drawer = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.leftMenu)
        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_setting -> {
                    goToSecondActivity()
                }
                else -> {

                }
            }
            return@setNavigationItemSelectedListener true
        }

        //setup listener for drawer and actionbar
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        // show drawer menu toggle button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        setSupportActionBar(toolbar)
        setupUI()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        when (item.itemId) {
            R.id.action_history -> {
                goToSecondActivity()
            }
            else -> {

            }
        }
        return super.onOptionsItemSelected(item)
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