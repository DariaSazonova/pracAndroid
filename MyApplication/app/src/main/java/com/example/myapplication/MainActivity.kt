package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextNUmber1 = findViewById<EditText>(R.id.editTextNUmber1)
        val editTextNUmber2 = findViewById<EditText>(R.id.editTextNUmber2)
        val textViewRes = findViewById<TextView>(R.id.textViewRes)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)


        buttonAdd.setOnClickListener {
            val num1text = editTextNUmber1.text
            val num2text = editTextNUmber2.text

            val num1 = num1text.toString().toIntOrNull()
            val num2 = num2text.toString().toIntOrNull()

            if (num1 != null) {
                if (num2 != null) {
                    val result = num1 + num2
                    textViewRes.text = result.toString()
                }
                else {
                    Toast.makeText(this,getString(R.string.WrongSecondNum), Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(this,getString(R.string.WrongFirstNum), Toast.LENGTH_LONG).show()
            }

        }
    }
}