package com.example.trigonometriccalculatorrad55

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // Live data
    val calculatorText: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    //

    // Button events
    fun numberButtonClick(number: Int) {
        var calculatorTextString = calculatorText.value.toString()
        val firstNumber: Char = calculatorTextString.first()
        if (calculatorTextString == "0") {
            calculatorTextString = number.toString()
        } else if (calculatorTextString.length != 8) {
            // Add spaces
            if (calculatorTextString.length == 3 || calculatorTextString.length == 7) {
                calculatorTextString.removeRange(0, 0)
                calculatorTextString = firstNumber + " " + calculatorTextString
            }
            //
            calculatorTextString += number.toString()
        }
        calculatorText.value = calculatorTextString
    }
    //
}