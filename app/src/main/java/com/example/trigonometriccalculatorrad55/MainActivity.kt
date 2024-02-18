package com.example.trigonometriccalculatorrad55

import android.content.Context
import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
        /// Button color
        //// Sin button
        val sinButtonColorObserver = Observer<Int> {newColor ->
            binding.buttonSin.setBackgroundColor(newColor)
        }

        model.sinButtonColor.observe(this, sinButtonColorObserver)
        //// Tan button
        val tanButtonColorObserver = Observer<Int> {newColor ->
            binding.buttonTan.setBackgroundColor(newColor)
        }

        model.tanButtonColor.observe(this, tanButtonColorObserver)
        //// Sec button
        val secButtonColorObserver = Observer<Int> {newColor ->
            binding.buttonSec.setBackgroundColor(newColor)
        }

        model.secButtonColor.observe(this, secButtonColorObserver)
        ///
        /// Button text
        //// View mode (rad/deg)
        val viewModeButtonTextObserver = Observer<String> {newText ->
            binding.buttonViewMode.text = newText
        }

        model.viewModeText.observe(this, viewModeButtonTextObserver)
        //// Sin button
        val sinButtonTextObserver = Observer<String> {newText ->
            binding.buttonSin.text = newText
        }

        model.sinButtonText.observe(this, sinButtonTextObserver)
        //// Tan button
        val tanButtonTextObserver = Observer<String> {newText ->
            binding.buttonTan.text = newText
        }

        model.tanButtonText.observe(this, tanButtonTextObserver)
        //// Sec button
        val secButtonTextObserver = Observer<String> {newText ->
            binding.buttonSec.text = newText
        }

        model.secButtonText.observe(this, secButtonTextObserver)
        ///
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
        /// Mode buttons
        binding.buttonViewMode.setOnClickListener {
            model.changeViewMode()
        }
        binding.button2nd.setOnClickListener {
            model.change2nd()
        }
        binding.buttonCo.setOnClickListener {
            model.changeCo()
        }
        binding.buttonPI.setOnClickListener {
            model.changePI()
        }
        binding.buttonMinus.setOnClickListener {
            model.changeMinus()
        }
        ///
        /// Function buttons
        binding.buttonSin.setOnClickListener {
            model.trigonometricFunctionEvent(1)
        }
        binding.buttonTan.setOnClickListener {
            model.trigonometricFunctionEvent(2)
        }
        binding.buttonSec.setOnClickListener {
            model.trigonometricFunctionEvent(3)
        }
        ///
        /// Delete buttons
        binding.buttonClear.setOnClickListener {
            model.clearButtonClick()
        }
        binding.buttonBackspace.setOnClickListener {
            model.backspaceButtonClick()
        }
        ///
        //

        // Set model data
        model.density = resources.displayMetrics.density
        model.isDarkTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES
        //

        model.initialize()
    }
}