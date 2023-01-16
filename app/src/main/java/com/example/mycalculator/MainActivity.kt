package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var display: TextView? = null
    var lastNumeric: Boolean = false
    var lastdecimal: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.display)
    }

    fun onDigit(view: View) {

        display?.append((view as Button).text);
        lastNumeric = true
        lastdecimal = false


    }

    fun onclear(view: View) {
        display?.text = ""
    }

    fun ondecimal(view: View) {
        if (lastNumeric && !lastdecimal) {
            display?.append(".")
            lastNumeric = false
            lastdecimal = true
        }
    }

    fun onOperator(view: View) {                   // returning false from isoperator shows no ooperator has been used before
        display?.text.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                display?.append((view as Button).text)
                lastNumeric = false
                lastdecimal = false
            }

        }

    }

    fun OnEqual(view: View) {
        if (lastNumeric) {
            var displayvalue = display?.text.toString()
            var prefix = ""
            try {
                if (displayvalue.startsWith("-")) {
                    prefix = "-"
                    displayvalue =
                        displayvalue.substring(1)//-99is given ->99 will be store , so that split doesnot get confused
                }

                if (displayvalue.contains("-")) {

                    var split = displayvalue.split("-")  // for 99-1 one= 99 and two =1
                    var one = split[0]
                    var two = split[1]

                    if (prefix.isNotEmpty()) {      // if prefix is not empty just put that value in one, so that now calculation can be smooth
                        one = prefix + one
                    }
                    display?.text = RemoveZero((one.toDouble() - two.toDouble()).toString())  //ans=98.0
                } else if (displayvalue.contains("+")) {

                    var split = displayvalue.split("+")  // for 99-1 one= 99 and two =1
                    var one = split[0]
                    var two = split[1]

                    if (prefix.isNotEmpty()) {      // if prefix is not empty just put that value in one, so that now calculation can be smooth
                        one = prefix + one
                    }
                    display?.text = RemoveZero((one.toDouble() + two.toDouble()).toString() ) //ans=98.0
                } else if (displayvalue.contains("/")) {

                    var split = displayvalue.split("/")  // for 99-1 one= 99 and two =1
                    var one = split[0]
                    var two = split[1]

                    if (prefix.isNotEmpty()) {      // if prefix is not empty just put that value in one, so that now calculation can be smooth
                        one = prefix + one
                    }
                    display?.text = RemoveZero((one.toDouble() / two.toDouble()).toString())  //ans=98.0
                } else if (displayvalue.contains("*")) {

                    var split = displayvalue.split("*")  // for 99-1 one= 99 and two =1
                    var one = split[0]
                    var two = split[1]

                    if (prefix.isNotEmpty()) {      // if prefix is not empty just put that value in one, so that now calculation can be smooth
                        one = prefix + one
                    }
                    display?.text = RemoveZero((one.toDouble() * two.toDouble()).toString() ) //ans=98.0
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    private  fun RemoveZero(value:String):String{
        var an=value
        if (an.contains(".0")){
            an=an.substring(0,an.length-2)
        }
        return an
    }

    private fun isOperatorAdded(value: String): Boolean         // IN THIS WE ARE CHECKING IF DISPLAY
    // CONTAINS OPERATOR OR NOT
    {
        return if (value.startsWith("-")) {            // IGNORE IF - IS GIVEN
            false
        } else {
            value.contains("/")
                    || value.contains("-")
                    || value.contains("+")
                    || value.contains("*")
        }
    }
}