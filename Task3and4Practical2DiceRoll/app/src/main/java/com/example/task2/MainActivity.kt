package com.example.task2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var totalRolls = 0 // Initialize totalRolls

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val rollButton: Button = findViewById(R.id.rollButton)
        val diceImage: ImageView = findViewById(R.id.diceImage)
        val clearButton: Button = findViewById(R.id.clearButton)


        rollButton.setOnClickListener {
            rollDice(diceImage)
        }

        // Roll the dice when the app starts
        rollDice(diceImage)

        clearButton.setOnClickListener {
            clearResults()
        }
    }

    private fun rollDice(diceImage: ImageView) {
        val dice = Dice(6)
        val diceRoll = dice.roll()

        // Update the screen with the dice roll
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = diceRoll.toString()

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()

        // Determine which drawable resource ID to use based on the dice roll
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Set content description for accessibility (optional)
        diceImage.contentDescription = diceRoll.toString()
    }

    private fun clearResults() {
        totalRolls = 0 // Reset the total rolls to zero

        // Update the screen with the value 1
        val resultTextView: TextView = findViewById(R.id.textView)
        resultTextView.text = "1"

        // Update the content description for accessibility
        val diceImage: ImageView = findViewById(R.id.diceImage)
        diceImage.contentDescription = "1"

        // Set the ImageView to display the dice face with the number 1
        diceImage.setImageResource(R.drawable.dice_1)
    }
}

class Dice(private val numSides: Int) {
    private val random = java.util.Random()

    fun roll(): Int {
        return random.nextInt(numSides) + 1
    }
}
