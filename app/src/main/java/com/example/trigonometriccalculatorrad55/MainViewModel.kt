package com.example.trigonometriccalculatorrad55

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class MainViewModel : ViewModel() {
    // Global data
    /// Constants
    private val calculatorInputHeightDefault = 259
    private val calculatorInputHeightChanged = 223
    private val calculatorOutputHeightDefault = 0
    private val calculatorOutputHeightChanged = 36
    ///
    /// View data
    var density = 0f
    private var isDecimal = false
    private var calculatorInputString : String = "0"
    private var calculatorInputStringSpaces : String = "0"
    private var fractionalPartString : String = ""
    private var calculatorTextSizeFloat = 48f
    ///
    /// Model data
    private var isRad = true
    private var isArc = false
    private var isCo = false
    private var currentFunction : Int = 0
    ////
    ///
    /*
    0 - Nothing
    1 - Sin
    2 - Tan
    3 - Sec
    4 - Cos
    5 - Ctg
    6 - Cosec
    7 - Arcsin
    8 - Arctan
    9 - Arcsec
    10 - Arccos
    11 - Arcctg
    12 - Arccsc
     */
    private var input : Double = 0.0
    private var output : Double = 0.0
    ///
    //

    // Live data
    val calculatorText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val calculatorOutputText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val calculatorTextSize: MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val calculatorInputHeight: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val calculatorOutputHeight: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    //

    // Initialization
    fun initialize() {
        calculatorOutputText.value = ""
        calculatorText.value = "0"
        setCalculatorTextHeight(true)
    }
    //

    // Public methods
    /// Operation event
    fun trigonometricFunctionEvent(function : Int) {
        currentFunction = function
        if (isCo)
            currentFunction += 3
        if (isArc)
            currentFunction += 6
        when (currentFunction) {
            // Sin
            1 -> output = sin(input)
            // Tan
            2 -> output = tan(input)
            // Sec
            3 -> if (cos(input) != 0.0)
                output = 1 / cos(input)
            else
                calculatorOutputText.value = "Cannot divide by zero"
            // Cos
            4 -> output = cos(input)
            // Ctg
            5 -> if (sin(input) != 0.0)
                output = cos(input) / sin(input)
            else
                calculatorOutputText.value = "Cannot divide by zero"
            // Cosec
            6 -> if (sin(input) != 0.0)
                output = 1 / sin(input)
            else
                calculatorOutputText.value = "Cannot divide by zero"
            // Arcsin
            7 -> output = asin(input)
            // Arctan
            8 -> output = atan(input)
            // Arcsec
            9 -> if (input != 0.0)
                output = acos(1 / input)
            else
                calculatorOutputText.value = "Cannot divide by zero"
            // Arccos
            10 -> output = acos(input)
            // Arcctg
            11 -> output = PI / 2 - atan(input)
            // Arccsc
            12 -> if (input != 0.0)
                output = asin(1 / input)
            else
                calculatorOutputText.value = "Cannot divide by zero"
        }

        // Update output view
        var calculatorOutputString = output.toString()
        /// Add spaces
        var j = -1
        if (calculatorOutputString.contains('.'))
        for (i: Int in calculatorOutputString.length - 1 downTo 1) {
            if (j > -1) {
                j++
                if (j % 3 == 0) {
                    calculatorOutputString = calculatorOutputString.replaceRange(
                        i - 1,
                        i,
                        calculatorOutputString[i - 1] + " "
                    )
                }
            } else if (calculatorOutputString[i] == '.')
            {
                calculatorOutputString.replace('.', ',')
                j = 0
            }
        }
        ///
        setCalculatorTextHeight(false)
        calculatorOutputText.value = "=$calculatorOutputString"
        //
    }

    /// Backspace button click event
    fun backspaceButtonClick() {
        if (!isDecimal) {
            // Update global data
            calculatorInputString = if (calculatorInputString.length > 1) {
                calculatorInputString.dropLast(1)
            } else {
                "0"
            }
            input = calculatorInputString.toDouble()
            //
            // Add spaces
            calculatorInputStringSpaces = calculatorInputString
            var j = 0
            for (i: Int in calculatorInputString.length - 1 downTo 1) {
                j++
                if (j % 3 == 0) {
                    calculatorInputStringSpaces = calculatorInputStringSpaces.replaceRange(
                        i - 1,
                        i,
                        calculatorInputString[i - 1] + " "
                    )
                }
            }
            //
            // Update liva data
            calculatorText.value = calculatorInputStringSpaces
            //

        }
        else {
            if (fractionalPartString.length > 1) {
                fractionalPartString = fractionalPartString.dropLast(1)
                calculatorText.value = "$calculatorInputStringSpaces,$fractionalPartString"
            }
            else {
                fractionalPartString = ""
                isDecimal = false
                calculatorText.value = calculatorInputStringSpaces
            }
            input = (calculatorInputString + fractionalPartString).toDouble()
        }
        if (currentFunction != 0) trigonometricFunctionEvent(currentFunction)
        // Font changes
        if ((calculatorInputString + fractionalPartString).length > 12) {
            calculatorTextSizeFloat += 2.5f
            calculatorTextSize.value = calculatorTextSizeFloat
        }
        //
    }
    ///

    /// Clear button click event
    fun clearButtonClick() {
        isDecimal = false
        calculatorInputString = "0"
        calculatorInputStringSpaces = "0"
        fractionalPartString = ""
        input = 0.0
        output = 0.0
        calculatorText.value = "0"
        setCalculatorTextHeight(true)
    }
    ///

    /// Number button click event
    fun numberButtonClick(number: Int) {
        if (!isDecimal) {
            if (calculatorInputString.length != 9) {
                // Update global data
                if (calculatorInputString != "0") {
                    calculatorInputString += number.toString()
                } else {
                    calculatorInputString = number.toString()
                }
                input = calculatorInputString.toDouble()
                //
                // Add spaces
                calculatorInputStringSpaces = calculatorInputString
                var j = 0
                for (i: Int in calculatorInputString.length - 1 downTo 1) {
                    j++
                    if (j % 3 == 0) {
                        calculatorInputStringSpaces = calculatorInputStringSpaces.replaceRange(
                            i - 1,
                            i,
                            calculatorInputString[i - 1] + " "
                        )
                    }
                }
                //
                // Update liva data
                calculatorText.value = calculatorInputStringSpaces
                //
            }
        } else {
            if (fractionalPartString.length < 10) {
                fractionalPartString += number.toString()
                calculatorText.value += number.toString()
                input = calculatorInputString.toDouble() + fractionalPartString.toDouble() / Math.pow(10.0, fractionalPartString.length.toDouble()
                )
                // Font changes
                if ((calculatorInputString + fractionalPartString).length > 13) {
                    calculatorTextSizeFloat -= 2.5f
                    calculatorTextSize.value = calculatorTextSizeFloat
                }
                //
            }
        }
        if (currentFunction != 0) trigonometricFunctionEvent(currentFunction)
    }
    ///

    /// Comma button click event
    fun commaButtonClick() {
        if (!isDecimal) {
            calculatorText.value += ','
            isDecimal = true
        }
    }
    ///
    //

    // Private methods
    private fun setCalculatorTextHeight(toDefault : Boolean) {
        if (toDefault) {
            calculatorInputHeight.value = (calculatorInputHeightDefault * density + 0.05f).toInt()
            calculatorOutputHeight.value = (calculatorOutputHeightDefault * density + 0.05f).toInt()
        }
        else {
            calculatorInputHeight.value = (calculatorInputHeightChanged * density + 0.05f).toInt()
            calculatorOutputHeight.value = (calculatorOutputHeightChanged * density + 0.05f).toInt()
        }
    }
    //
}