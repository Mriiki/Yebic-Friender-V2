package com.example.myapplication2

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "TryActivity"
class TryActivity : AppCompatActivity() {
    private lateinit var button1: ImageButton
    private lateinit var button2: ImageButton
    private lateinit var button3: ImageButton
    private lateinit var button4: ImageButton
    private lateinit var button5: ImageButton
    private lateinit var button6: ImageButton
    private lateinit var button7: ImageButton
    private lateinit var button8: ImageButton
    private lateinit var buttons: List<ImageButton>
    private lateinit var cards: List<MemoryCard>
    private var indexOfSingleSelectedCard: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_try)

        val images = mutableListOf(
            R.drawable.ic_bee,
            R.drawable.ic_corona,
            R.drawable.ic_rat,
            R.drawable.ic_star
        )
        //Add each image twice to create pairs
        images.addAll(images)
        //Randomize the order of images
        images.shuffle()

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Try Activity"
        }
        actionBar?.setDisplayHomeAsUpEnabled(true)

        button1 = findViewById(R.id.imageButton2)
        button2 = findViewById(R.id.imageButton3)
        button3 = findViewById(R.id.imageButton4)
        button4 = findViewById(R.id.imageButton5)
        button5 = findViewById(R.id.imageButton6)
        button6 = findViewById(R.id.imageButton7)
        button7 = findViewById(R.id.imageButton8)
        button8 = findViewById(R.id.imageButton9)

        buttons = listOf(button1, button2, button3, button4, button5, button6, button7, button8)

        cards = buttons.indices.map { index ->
            MemoryCard(images[index])
        }
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                Log.i(TAG, "Button clicked!!")
                //Update models
                updateModels(index)
                //Update the UI
                updateViews()

            }
        }



        }

     private fun updateModels(position: Int){
         val card = cards[position]
         //error check
         if (card.isFaceUp){
             Toast.makeText(this, "Invalid move!", Toast.LENGTH_SHORT).show()
             return

         }
         //Three cases
         //0 cards previously flipped => flip selected card
         //1 card previously flipped over => flip + check
         //2 cards previously flipped over => restore + flip
         if (indexOfSingleSelectedCard == null){
             // 0 or 2 cards selected
                 restoreCards()
             indexOfSingleSelectedCard = position
         } else {
             //one card
             checkForMatch(indexOfSingleSelectedCard!!, position)
             indexOfSingleSelectedCard = null
         }
         card.isFaceUp = !card.isFaceUp
    }
    private fun restoreCards(){
        for (card in cards){
            if(!card.isMatched){
                card.isFaceUp = false
            }

        }

    }
    private fun updateViews(){
        cards.forEachIndexed { index, card ->
            val  button = buttons[index]
            if (card.isMatched){
                button.alpha = 0.1f
            }
            if (card.isFaceUp) {
                button.setImageResource(card.identifier)
            } else
            {
                button.setImageResource(R.drawable.ic_baseline_wb_sunny_24)
            }


        }


    }
    private fun checkForMatch(position1: Int, position2: Int){
        if(cards[position1].identifier == cards[position2].identifier){
            Toast.makeText(this, "Match Found" , Toast.LENGTH_SHORT).show()
                    cards[position1].isMatched = true
                    cards[position2].isMatched = true
        }


    }


}

