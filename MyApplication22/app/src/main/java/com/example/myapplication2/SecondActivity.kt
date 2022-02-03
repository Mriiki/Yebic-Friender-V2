package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var pointText : TextView
    private lateinit var questionNoText : TextView
    private lateinit var questionText : TextView
    private lateinit var optionAText : RadioButton
    private lateinit var optionBText : RadioButton
    private lateinit var optionCText : RadioButton
    private lateinit var submitButton: Button

    private lateinit var questionBank: ArrayList<Question>
    private var questionNo : Int = 0
    private var answer : String = "None"
    private var score : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        pointText = findViewById(R.id.textView9)
        questionNoText = findViewById(R.id.textView8)
        questionText = findViewById(R.id.textView4)

        optionAText = findViewById(R.id.radioButton)
        optionBText = findViewById(R.id.radioButton2)
        optionCText = findViewById(R.id.radioButton3)

        submitButton= findViewById(R.id.button2)

        questionBank = ArrayList()
        questionNo = 0
        setUpQuestions()

        initlisteners()
        display(questionNo);
    }
    fun clearAll(){
        optionAText.isChecked = false
        optionBText.isChecked = false
        optionCText.isChecked = false
    }



    fun display(index : Int) {
        var questionObject = questionBank.get(index)
        questionNoText.text = "Question" +(questionNo + 1).toString()
        questionText.text = questionObject.question
        optionAText.text = questionObject.optionA
        optionBText.text = questionObject.optionB
        optionCText.text = questionObject.optionC

    }
    fun setUpQuestions() {
        questionBank.add(
            Question(
                "Mrinal's favourite colour" ,
                "Black" ,
                10,
                "Yellow" ,
                15,
                "Turquoise",
                5

            )

        )
        questionBank.add(
            Question(
                "Mrinal usually goes by this name at college" ,
                "Minnal" ,
                5 ,
                "Mrigi" ,
                10 ,
                "Mriiki" ,
                15
            )
        )
        questionBank.add(
            Question(
                "Mrinal loves to eat" ,
                "Pizza" ,
                15 ,
                "Galaxy" ,
                5 ,
                "Brownie" ,
                10
            )
        )
        questionBank.add(
            Question(
                "What phrase or word does Mrinal use the most" ,
                "yebic" ,
                10 ,
                "ok da" ,
                15 ,
                "yemezing" ,
                5
            )
        )
        questionBank.add(
            Question(
                "How many languages(spoken) does Mrinal know" ,
                "6" ,
                0 ,
                "4" ,
                0 ,
                "5" ,
                15
            )
        )
        questionBank.add(
            Question(
                "Mrinal's favourite dog breed" ,
                "Pugs" ,
                5 ,
                "Golden retrievers" ,
                15 ,
                "Beagles" ,
                10
            )
        )
    }


    fun initlisteners() {
        optionAText.setOnClickListener{
            clearAll()
            optionAText.isChecked = true
            answer = "A"

        }

        optionBText.setOnClickListener{
            clearAll()
            optionBText.isChecked = true
            answer = "B"

        }
        optionCText.setOnClickListener{
            clearAll()
            optionCText.isChecked = true
            answer = "C"
        }

        submitButton.setOnClickListener{
            clearAll()
            if (answer == "None"){score += 0}
            if (answer == "A"){score += questionBank.get(questionNo).AW}
            if (answer == "B"){score += questionBank.get(questionNo).BW}
            if (answer == "C"){score += questionBank.get(questionNo).CW}
            pointText.text = "Points: $score"
            answer = "None"

            if (questionNo < (questionBank.size - 1)) {
                questionNo++
                display(questionNo)

            } else {
                //go to next page
                var intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
            }
        }






    }

    data class  Question (
       var  question: String ,
       var optionA: String ,
       var AW: Int,
       var optionB: String ,
       var BW: Int,
       var optionC: String,
       var CW: Int,

        )
}


