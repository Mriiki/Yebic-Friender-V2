package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn1: Button = findViewById(R.id.button)
        var intent = Intent(this , SecondActivity::class.java)

        btn1.setOnClickListener{
            startActivity(intent)
            finish()

        }
    }
}