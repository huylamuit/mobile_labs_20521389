package com.example.mobile_labs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("hello")
        var a:Person = Person()

        val arrayAdapter: ArrayAdapter<*>

        val result:MutableList<String> = mutableListOf()

        val txtName = findViewById<EditText>(R.id.editTextTextPersonName)
        val txtSalary = findViewById<EditText>(R.id.editSalary)
        val btnCal = findViewById<Button>(R.id.button)
        btnCal.setOnClickListener(){
            val arrayAdapter: ArrayAdapter<*>

            a.name = txtName.text.toString()
            a.salary =txtSalary.text.toString().toDouble()
            a.salary = a.calGrossSalary()
            result.add(a.printInfo())
            var mListView = findViewById<ListView>(R.id.listResult)
            arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1,0, result.toTypedArray())
            mListView.adapter = arrayAdapter
        }

    }
}