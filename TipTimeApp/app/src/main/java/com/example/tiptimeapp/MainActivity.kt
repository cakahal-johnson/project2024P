package com.example.tiptimeapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import android.annotation.SuppressLint
import android.util.Log
import com.example.tiptimeapp.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        Binding the XML after linking the gradle script
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    //    creating the calculateTip method
    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        // Get the text from the cost of service input field and convert it to a string
        val stringInTextField = binding.costOfService.text.toString()

        // Convert the string to a double or return if null
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = getString(R.string.tip_amount, "")
            Log.d("MainActivity", "Cost is null or empty")
            return
        }

        // Get the selected tip percentage based on the checked radio button
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            R.id.option_fifteen_percent -> 0.15
            else -> 0.15
        }

        // Calculate the tip amount
        var tip = tipPercentage * cost

        // Check if the user wants to round up the tip and round if necessary
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        // Format the tip as currency and display it in the tip result text view
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        Log.d(
            "MainActivity",
            "Cost: $cost, Tip Percentage: $tipPercentage, Tip: $tip, Formatted Tip: $formattedTip"
        )
    }
}