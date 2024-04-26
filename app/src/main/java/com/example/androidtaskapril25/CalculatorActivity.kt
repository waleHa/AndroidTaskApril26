package com.example.androidtaskapril25

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtaskapril25.databinding.ActivityCalculatorBinding


class CalculatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculatorBinding
    var result: Double = 0.0
    var firstNumber = ""
    var secondNumber = ""
    var operation = ""
    val TAG = "CalculatorActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCalculator()


    }

    private fun setCalculator() {

        clickingNumbers()
        clickingSigns()
    }

    private fun clickingSigns() {
        binding.buttonC.setOnClickListener {
            firstNumber = ""
            clear()
        }

        binding.buttonX.setOnClickListener {
            handleDropLast()
        }
    }



    private fun clickingNumbers() {
        binding.button0.setOnClickListener {
            handleClickedNumber(0)
        }

        binding.button1.setOnClickListener {
            handleClickedNumber(1)
        }

        binding.button2.setOnClickListener {
            handleClickedNumber(2)
        }

        binding.button3.setOnClickListener {
            handleClickedNumber(3)
        }

        binding.button4.setOnClickListener {
            handleClickedNumber(4)
        }

        binding.button5.setOnClickListener {
            handleClickedNumber(5)
        }

        binding.button6.setOnClickListener {
            handleClickedNumber(6)
        }

        binding.button7.setOnClickListener {
            handleClickedNumber(7)
        }

        binding.button8.setOnClickListener {
            handleClickedNumber(8)
        }

        binding.button9.setOnClickListener {
            handleClickedNumber(9)
        }

        binding.buttonPlus.setOnClickListener {
            handleClickedSign("+")
        }
        binding.buttonMinus.setOnClickListener {
            handleClickedSign("-")
        }

        binding.buttonMult.setOnClickListener {
            handleClickedSign("*")
        }

        binding.buttonDiv.setOnClickListener {
            handleClickedSign("/")
        }

        binding.buttonMode.setOnClickListener {
            handleClickedSign("%")
        }

        binding.buttonDot.setOnClickListener {
            handleClickedNumber(".")
        }

        binding.buttonEqual.setOnClickListener {
            Log.i(TAG, "$firstNumber $operation $secondNumber")
            result = when (operation) {
                "+" -> submitEquation(firstNumber.toDouble(), secondNumber.toDouble(), ::add)
                "*" -> submitEquation(firstNumber.toDouble(), secondNumber.toDouble(), multiply)
                "/" -> submitEquation(firstNumber.toDouble(), secondNumber.toDouble(), divide)
                "-" -> submitEquation(firstNumber.toDouble(), secondNumber.toDouble(), subtract)
                else -> submitEquation(firstNumber.toDouble(), secondNumber.toDouble(), mode)

            }
            binding.textViewOperation.text = result.toString()
            firstNumber = result.toString()
            clearForEqual()
        }
    }

    private fun clearForEqual() {
        secondNumber = ""
        operation = ""
    }

    private fun clear() {
        binding.textViewOperation.text = ""
        firstNumber = ""
        secondNumber = ""
        operation = ""
    }

    private fun submitEquation(
        a: Double,
        b: Double,
        operation: (Double, Double) -> Double
    ): Double {
        return operation(a, b)
    }

    val multiply = { num1: Double, num2: Double ->
        num1 * num2
    }
    val divide = { num1: Double, num2: Double ->
        num1 / num2
    }
    val subtract = { num1: Double, num2: Double ->
        num1 - num2
    }
    val mode = { num1: Double, num2: Double ->
        num1 % num2
    }

    fun add(a: Double, b: Double): Double {
        return a + b
    }

    private fun handleDropLast() {
        val input = binding.textViewOperation.text.toString()

        val droppedValue = input[input.length-1]
        if (droppedValue.isDigit()){
            if(operation.isEmpty()){
                firstNumber = firstNumber.dropLast(1)
            }else{
                secondNumber = secondNumber.dropLast(1)
            }
        }else{
            operation = ""
        }
        binding.textViewOperation.text = input.dropLast(1)

    }

    fun <T> handleClickedNumber(num: T) {
        val input = binding.textViewOperation.text.toString()

        //Second number
        if (operation.isNotEmpty()) {
            if (num == "." && "." in secondNumber) {
                //Continue
            } else {
                binding.textViewOperation.text = input.plus("$num")
                secondNumber = secondNumber.plus(num)
            }
        }
        //First number
        else {
            if (num == "." && "." in firstNumber) {
                //Continue
            } else {
                binding.textViewOperation.text = input.plus(num)
                firstNumber = firstNumber.plus(num)
            }
        }
    }

    fun handleClickedSign(sign: String) {
        val input = binding.textViewOperation.text.toString()
        binding.textViewOperation.text = input.plus(" $sign ")
        operation = sign
    }
}

