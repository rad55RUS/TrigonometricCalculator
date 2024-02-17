package com.example.trigonometriccalculatorrad55

import android.os.Bundle
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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

        // Observers
        /// Text
        val calculatorTextObserver = Observer<String> { newText ->
            binding.calculatorTextView.text = newText
        }

        model.calculatorText.observe(this, calculatorTextObserver)

        val calculatorOutputTextObserver = Observer<String> { newText ->
            binding.calculatorOutputTextView.text = newText
        }

        model.calculatorOutputText.observe(this, calculatorOutputTextObserver)
        ///
        /// Text size
        val calculatorTextSizeObserver = Observer<Float> { newSize ->
            binding.calculatorTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, newSize)
        }

        model.calculatorTextSize.observe(this, calculatorTextSizeObserver)
        ///
        /// View height
        val calculatorInputHeightObserver = Observer<Int> { newHeight ->
            binding.calculatorTextView.height = newHeight
        }

        model.calculatorInputHeight.observe(this, calculatorInputHeightObserver)

        val calculatorOutputHeightObserver = Observer<Int> { newHeight ->
            binding.calculatorOutputTextView.height = newHeight
        }

        model.calculatorOutputHeight.observe(this, calculatorOutputHeightObserver)
        ///
        //

        // Bindings
        /// Number input buttons
        binding.buttonNumber1.setOnClickListener {
            model.numberButtonClick(1)
        }
        binding.buttonNumber2.setOnClickListener {
            model.numberButtonClick(2)
        }
        binding.buttonNumber3.setOnClickListener {
            model.numberButtonClick(3)
        }
        binding.buttonNumber4.setOnClickListener {
            model.numberButtonClick(4)
        }
        binding.buttonNumber5.setOnClickListener {
            model.numberButtonClick(5)
        }
        binding.buttonNumber6.setOnClickListener {
            model.numberButtonClick(6)
        }
        binding.buttonNumber7.setOnClickListener {
            model.numberButtonClick(7)
        }
        binding.buttonNumber8.setOnClickListener {
            model.numberButtonClick(8)
        }
        binding.buttonNumber9.setOnClickListener {
            model.numberButtonClick(9)
        }
        binding.buttonNumber0.setOnClickListener {
            model.numberButtonClick(0)
        }
        binding.buttonComma.setOnClickListener {
            model.commaButtonClick()
        }
        ///
        /// Function buttons
        binding.buttonSin.setOnClickListener {
            model.trigonometricFunctionEvent(1)
        }
        binding.buttonTg.setOnClickListener {
            model.trigonometricFunctionEvent(2)
        }
        binding.buttonSec.setOnClickListener {
            model.trigonometricFunctionEvent(3)
        }
        ///
        /// Operation buttons
        binding.buttonClear.setOnClickListener {
            model.clearButtonClick()
        }
        binding.buttonBackspace.setOnClickListener {
            model.backspaceButtonClick()
        }
        ///
        //

        // Set dpi density
        model.density = resources.displayMetrics.density
        //

        model.initialize()
    }
}