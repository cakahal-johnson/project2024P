package com.example.practical3


import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.example.practical3.R.string.tip_amount
import com.example.practical3.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Creating the Method for the Calculated Tip
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        // Get the text from the cost of service input field and convert it to a string
        val stringInTextField = binding.costOfService.text.toString()

        // Convert the string to a double or return if null
        val cost = stringInTextField.toDoubleOrNull()
        // Check if the cost is null and return if it is
        if (cost == null) {
            binding.tipResult.text = getString(R.string.tip_amount, "")
            return
        }

        // Get the selected tip percentage based on the checked radio button
        val selectedId = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
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
    }


}