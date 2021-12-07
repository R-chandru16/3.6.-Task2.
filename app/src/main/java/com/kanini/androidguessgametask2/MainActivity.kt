package com.kanini.androidguessgametask2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    var num = (0..1000).random()
    var stringNum = num.toString()
    var NoofAttempt = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numInputView = findViewById<TextInputLayout>(R.id.inputNumber)
        val checkButtonView = findViewById<TextView>(R.id.checkButton)
        val resultView = findViewById<TextView>(R.id.result)

        checkButtonView.setOnClickListener{
            val number = numInputView.editText?.text?.toString()
            val myguessedNumber =
                if (number.isNullOrEmpty()) "0"
                else number
            if (myguessedNumber != "0") {
                if (myguessedNumber.toInt() == num) {
                    resultView.text = "You are right!"
                    val newScreenIntent = Intent(this,EndGameActivity::class.java)
                    newScreenIntent.putExtra("number",stringNum)
                    newScreenIntent.putExtra("msg","success You won the game The number is")
                    newScreenIntent.putExtra("color","#00FF00")
                    startActivity(newScreenIntent)
                }
                else if(myguessedNumber.toInt() > num)
                    resultView.text = "No:) My number is smaller"
                else
                    resultView.text = "No:) My number is bigger"
            }
            else
                resultView.text = "please enter the number between 0 to 1000"
            NoofAttempt++
            if(NoofAttempt > 12) {
                val newScreenIntent = Intent(this,EndGameActivity::class.java)
                newScreenIntent.putExtra("number",stringNum)
                newScreenIntent.putExtra("msg","You lost all 12 attempts. The number is")
                newScreenIntent.putExtra("color","#FF0000")
                startActivity(newScreenIntent)
            }
        }
    }
}