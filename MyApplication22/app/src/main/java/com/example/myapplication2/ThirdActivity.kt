package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ThirdActivity : AppCompatActivity() {
    private lateinit var scoreboard : TextView
    private lateinit var backbutton : Button
    private lateinit var finalText : TextView
    private lateinit var homebutton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        finalText = findViewById(R.id.textView6)
        scoreboard = findViewById(R.id.textView5)
        backbutton = findViewById(R.id.button3)
        homebutton = findViewById(R.id.button5)
        backbutton.setOnClickListener{
            val intent = Intent(this ,TryActivity::class.java )
            startActivity(intent)

        }

        scoreboard.text =  (intent.getStringExtra("score" ) + "/90")
        var scoreINT = intent.getStringExtra("score" )?.toInt()
        if (scoreINT != null) {
            if(scoreINT <= 40){
                finalText.text = "Plis dont talk to me -_-"
            }
        }
        if (scoreINT != null) {
            if(scoreINT > 40 && scoreINT <= 60){
                finalText.text = "Wohoo! You did a great job! Yemezing friend!"
            }
        }
        if (scoreINT != null) {
            if(scoreINT > 60 && scoreINT <= 90){
                finalText.text = "YAAYYY! YOU ARE THE YEBICESTT FRIEND!!!! CONGRATULATIONS!"
            }
        }



        homebutton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

    }
}