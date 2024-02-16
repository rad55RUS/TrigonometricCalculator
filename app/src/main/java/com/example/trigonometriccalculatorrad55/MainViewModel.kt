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
    /// Public data
    public var isRad = true
    public var isArc = false
    public var isCo = false
    ///
    /// View data
    private var isDecimal = false
    private var calculatorTextString : String = "0"
    private var calculatorTextStringSpaces : String = "0"
    private var fractionalPartString : String = ""
    private var calculatorTextSizeFloat = 48f
    ///
    /// Model data
    private var currentFunction : Int = 0
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
    //

    // Events
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
                calculatorOutputText.value = "∞"
            // Cos
            4 -> output = cos(input)
            // Ctg
            5 -> if (sin(input) != 0.0)
                output = cos(input) / sin(input)
            else
                calculatorOutputText.value = "∞"
            // Cosec
            6 -> if (sin(input) != 0.0)
                output = 1 / sin(input)
            else
                calculatorOutputText.value = "∞"
            // Arcsin
            7 -> output = asin(input)
            // Arctan
            8 -> output = atan(input)
            // Arcsec
            9 -> if (input != 0.0)
                output = acos(1 / input)
            else
                calculatorOutputText.value = "∞"
            // Arccos
            10 -> output = acos(input)
            // Arcctg
            11 -> output = PI / 2 - atan(input)
            // Arccsc
            12 -> if (input != 0.0)
                output = asin(1 / input)
            else
                calculatorOutputText.value = "∞"
        }
    }

    /// Backspace button click event
    fun backspaceButtonClick() {
        if (!isDecimal) {
            // Update global data
            calculatorTextString = if (calculatorTextString.length > 1) {
                calculatorTextString.dropLast(1)
            } else {
                "0"
            }
            input = calculatorTextString.toDouble()
            //
            // Add spaces
            calculatorTextStringSpaces = calculatorTextString
            var j = 0
            for (i: Int in calculatorTextString.length - 1 downTo 1) {
                j++
                if (j % 3 == 0) {
                    calculatorTextStringSpaces = calculatorTextStringSpaces.replaceRange(
                        i - 1,
                        i,
                        calculatorTextString[i - 1] + " "
                    )
                }
            }
            //
            // Update liva data
            calculatorText.value = calculatorTextStringSpaces
            //

        }
        else {
            if (fractionalPartString.length > 1) {
                fractionalPartString = fractionalPartString.dropLast(1)
                calculatorText.value = "$calculatorTextStringSpaces,$fractionalPartString"
            }
            else {
                fractionalPartString = ""
                isDecimal = false
                calculatorText.value = calculatorTextStringSpaces
            }
            input = (calculatorTextString + fractionalPartString).toDouble()
        }
        // Font changes
        if ((calculatorTextString + fractionalPartString).length > 12) {
            calculatorTextSizeFloat += 2.5f
            calculatorTextSize.value = calculatorTextSizeFloat
        }
        //
    }
    ///

    /// Clear button click event
    fun clearButtonClick() {
        isDecimal = false
        calculatorTextString = "0"
        calculatorTextStringSpaces = "0"
        fractionalPartString = ""
        input = 0.0
        output = 0.0
        calculatorText.value = "0"
    }
    ///

    /// Number button click event
    fun numberButtonClick(number: Int) {
        if (!isDecimal) {
            if (calculatorTextString.length != 9) {
                // Update global data
                if (calculatorTextString != "0") {
                    calculatorTextString += number.toString()
                } else {
                    calculatorTextString = number.toString()
                }
                input = calculatorTextString.toDouble()
                //
                // Add spaces
                calculatorTextStringSpaces = calculatorTextString
                var j = 0
                for (i: Int in calculatorTextString.length - 1 downTo 1) {
                    j++
                    if (j % 3 == 0) {
                        calculatorTextStringSpaces = calculatorTextStringSpaces.replaceRange(
                            i - 1,
                            i,
                            calculatorTextString[i - 1] + " "
                        )
                    }
                }
                //
                // Update liva data
                calculatorText.value = calculatorTextStringSpaces
                //
            }
        } else {
            if (fractionalPartString.length < 10) {
                fractionalPartString += number.toString()
                calculatorText.value += number.toString()
                input = (calculatorTextString + fractionalPartString).toDouble()
                // Font changes
                if ((calculatorTextString + fractionalPartString).length > 13) {
                    calculatorTextSizeFloat -= 2.5f
                    calculatorTextSize.value = calculatorTextSizeFloat
                }
                //
            }
        }
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
}