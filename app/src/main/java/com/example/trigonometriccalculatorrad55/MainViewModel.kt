package com.example.trigonometriccalculatorrad55

import android.graphics.Color
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
    private val calculatorInputHeightDefault = 299
    private val calculatorInputHeightChanged = 263
    private val calculatorOutputHeightDefault = 0
    private val calculatorOutputHeightChanged = 36
    ///
    /// View data
    var density = 0f
    var isDarkTheme = false
    private var isDecimal = false
    private var calculatorInputString : String = "0"
    private var calculatorInputStringSpaces : String = "0"
    private var fractionalPartString : String = ""
    private var calculatorTextSizeFloat = 48f
    ///
    /// Model data
    private var isRad = true
    private var isPI = false
    private var isMinus = false
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
    /// Text data
    val calculatorText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val calculatorOutputText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val viewModeText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val sinButtonText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val tanButtonText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val secButtonText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    /// Color data
    val sinButtonColor: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val tanButtonColor: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val secButtonColor: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    /// Size data
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
    /// Change view mode (rad/deg)
    fun changeViewMode() {
        isRad = !isRad
        if (isRad) viewModeText.value = "rad"
        else viewModeText.value = "deg"
        setPrefix()
        trigonometricFunctionEvent(currentFunction)
    }

    /// Change view mode (rad/deg)
    fun change2nd() {
        isArc = !isArc
        changeFunctionNames()
        setPrefix()
        trigonometricFunctionEvent(currentFunction)
    }

    /// Change view mode (rad/deg)
    fun changeCo() {
        isCo = !isCo
        changeFunctionNames()
        trigonometricFunctionEvent(currentFunction)
    }

    /// Change PI mode
    fun changePI() {
        isPI = !isPI
        setPrefix()
        trigonometricFunctionEvent(currentFunction)
    }

    /// Change Minus mode
    fun changeMinus() {
        isMinus = !isMinus
        setPrefix()
        trigonometricFunctionEvent(currentFunction)
    }

    /// Function event
    fun trigonometricFunctionEvent(function : Int) {
        currentFunction = function
        if (currentFunction != 0) {
            // Set color
            when (currentFunction) {
                1, 4, 7, 10 -> {
                    if (isDarkTheme) {
                        sinButtonColor.value = Color.parseColor("#151F2C")
                        tanButtonColor.value = Color.parseColor("#2B4161")
                        secButtonColor.value = Color.parseColor("#2B4161")
                    } else {
                        sinButtonColor.value = Color.parseColor("#38126A")
                        tanButtonColor.value = Color.parseColor("#772DDF")
                        secButtonColor.value = Color.parseColor("#772DDF")
                    }
                }

                2, 5, 8, 11 -> {
                    if (isDarkTheme) {
                        sinButtonColor.value = Color.parseColor("#2B4161")
                        tanButtonColor.value = Color.parseColor("#151F2C")
                        secButtonColor.value = Color.parseColor("#2B4161")
                    } else {
                        sinButtonColor.value = Color.parseColor("#772DDF")
                        tanButtonColor.value = Color.parseColor("#38126A")
                        secButtonColor.value = Color.parseColor("#772DDF")
                    }
                }

                3, 6, 9, 12 -> {
                    if (isDarkTheme) {
                        sinButtonColor.value = Color.parseColor("#2B4161")
                        tanButtonColor.value = Color.parseColor("#2B4161")
                        secButtonColor.value = Color.parseColor("#151F2C")
                    } else {
                        sinButtonColor.value = Color.parseColor("#772DDF")
                        tanButtonColor.value = Color.parseColor("#772DDF")
                        secButtonColor.value = Color.parseColor("#38126A")
                    }
                }
            }
            // Check modes
            var tempFunction = currentFunction
            if (isCo)
                tempFunction += 3
            if (isArc)
                tempFunction += 6
            var inputTemp : Double = input
            /// Translate to degrees
            if (!isRad) {
                inputTemp =
                    if (isPI) inputTemp * Math.PI
                    else inputTemp * Math.PI / 180
            } else if (isPI) {
                inputTemp *= Math.PI
            }
            if (isMinus) inputTemp = -inputTemp
            ///
            //
            when (tempFunction) {
                // Sin
                1 -> output = sin(inputTemp)
                // Tan
                2 -> output = tan(inputTemp)
                // Sec
                3 -> output = 1 / cos(inputTemp)
                // Cos
                4 -> output = cos(inputTemp)
                // Ctg
                5 -> output = cos(inputTemp) / sin(inputTemp)
                // Cosec
                6 -> output = 1 / sin(inputTemp)
                // Arcsin
                7 -> output = asin(inputTemp)
                // Arctan
                8 -> output = atan(inputTemp)
                // Arcsec
                9 -> output = acos(1 / inputTemp)
                // Arccos
                10 -> output = acos(inputTemp)
                // Arcctg
                11 -> output = PI / 2 - atan(inputTemp)
                // Arccsc
                12 -> output = asin(1 / inputTemp)
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
                    } else if (calculatorOutputString[i] == '.') {
                        calculatorOutputString.replace('.', ',')
                        j = 0
                    }
                }
            ///
            setCalculatorTextHeight(false)
            calculatorOutputText.value = "=$calculatorOutputString"
            //
            setPrefix()
        }
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
        if ((calculatorInputString + fractionalPartString).length > 10) {
            calculatorTextSizeFloat += 2.2f
            calculatorTextSize.value = calculatorTextSizeFloat
        }
        //
        setPrefix()
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
        setPrefix()
        if (isDarkTheme) {
            sinButtonColor.value = Color.parseColor("#2B4161")
            tanButtonColor.value = Color.parseColor("#2B4161")
            secButtonColor.value = Color.parseColor("#2B4161")
        } else {
            sinButtonColor.value = Color.parseColor("#772DDF")
            tanButtonColor.value = Color.parseColor("#772DDF")
            secButtonColor.value = Color.parseColor("#772DDF")
        }
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
                if ((calculatorInputString + fractionalPartString).length > 11) {
                    calculatorTextSizeFloat -= 2.2f
                    calculatorTextSize.value = calculatorTextSizeFloat
                }
                //
            }
        }
        setPrefix()
        if (currentFunction != 0) trigonometricFunctionEvent(currentFunction)
    }
    ///

    /// Comma button click event
    fun commaButtonClick() {
        if (!isDecimal) {
            calculatorText.value += ','
            isDecimal = true
        }
        setPrefix()
    }
    ///
    //

    // Private methods
    private fun setPrefix() {
        if (isRad) {
            calculatorText.value = calculatorText.value?.replace("°", "")
            calculatorOutputText.value = calculatorOutputText.value?.replace("°", "")
            calculatorText.value = calculatorText.value?.replace("π", "")
            calculatorText.value = calculatorText.value?.replace("-", "")
            if (isMinus) calculatorText.value = '-' + calculatorText.value.toString()
            if (isPI) calculatorText.value += 'π'
        }
        else {
            calculatorText.value = calculatorText.value?.replace("°", "")
            calculatorOutputText.value = calculatorOutputText.value?.replace("°", "")
            calculatorText.value = calculatorText.value?.replace("π", "")
            calculatorText.value = calculatorText.value?.replace("-", "")
            if (isMinus) calculatorText.value = '-' + calculatorText.value.toString()
            if (isPI) calculatorText.value += 'π'
            calculatorText.value += '°'
            calculatorOutputText.value += '°'
        }
    }
    private fun changeFunctionNames() {
        if (isArc) {
            if (isCo) {
                sinButtonText.value = "acos"
                tanButtonText.value = "actg"
                secButtonText.value = "acsc"
            }
            else {
                sinButtonText.value = "asin"
                tanButtonText.value = "atan"
                secButtonText.value = "asec"
            }
        } else {
            if (isCo) {
                sinButtonText.value = "cos"
                tanButtonText.value = "ctg"
                secButtonText.value = "csc"
            }
            else {
                sinButtonText.value = "sin"
                tanButtonText.value = "tan"
                secButtonText.value = "sec"
            }
        }
    }

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