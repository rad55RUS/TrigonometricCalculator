package com.example.trigonometriccalculatorrad55

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.trigonometriccalculatorrad55.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    private val model: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /// Observers
        val nameObserver = Observer<String> { newText ->
            // Update the UI, in this case, a TextView.
            binding.calculatorTextView.text = newText
        }

        model.calculatorText.observe(this, nameObserver)
        ///

        /// Bindings
        binding.buttonNumber1.setOnClickListener {
            model.numberButtonClick(1)
        }
        ///

        /// Default values
        model.calculatorText.value = "0"
        ///
    }
}