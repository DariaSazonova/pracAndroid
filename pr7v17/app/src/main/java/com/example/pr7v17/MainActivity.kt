package com.example.pr7v17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wholeFraction1=findViewById<EditText>(R.id.editWholeFraction1)
        val wholeFraction2=findViewById<EditText>(R.id.editWholeFraction2)
        val wholeFractionRes=findViewById<TextView>(R.id.editWholeFractionRes)
        val numerator1=findViewById<EditText>(R.id.editNumerator1)
        val numerator2=findViewById<EditText>(R.id.editNumerator2)
        val numeratorRes=findViewById<TextView>(R.id.editNumeratorRes)
        val denomerator1=findViewById<EditText>(R.id.editDenomirator1)
        val denomerator2=findViewById<EditText>(R.id.editDenomirator2)
        val denomeratorRes=findViewById<TextView>(R.id.editDenomiratorRes)
        val buttonMul=findViewById<Button>(R.id.buttonMul)
        val buttondiv=findViewById<Button>(R.id.buttonDil)
        val textViewSign=findViewById<TextView>(R.id.textViewSign)
        val textViewEqual=findViewById<TextView>(R.id.textViewEqual)

        buttonMul.setOnClickListener {
            wholeFractionRes.text=null
            numeratorRes.text = null
            denomeratorRes.text = null
            var wholeFraction1Int=wholeFraction1.text.toString().toIntOrNull()
            val denomerator1Int=denomerator1.text.toString().toIntOrNull()
            val numerator1Int = numerator1.text.toString().toIntOrNull()
            var wholeFraction2Int = wholeFraction2.text.toString().toIntOrNull()
            val denomerator2Int = denomerator2.text.toString().toIntOrNull()
            val numerator2Int =numerator2.text.toString().toIntOrNull()
            if(numerator1Int!=null && denomerator1Int!=null && numerator2Int!=null  && denomerator2Int!=null){
                if(wholeFraction1Int==null) wholeFraction1Int = 0
                if(wholeFraction2Int==null) wholeFraction2Int = 0
//                Log.d(null,"Все плохо")
                textViewSign.text = "*"
                textViewEqual.text = "="
                var numeratorResVal=(wholeFraction1Int*denomerator1Int+numerator1Int)*(wholeFraction2Int*denomerator2Int+numerator2Int)
                var denomeratorResVal = (denomerator1Int*denomerator2Int)
                var wholeFractionResVal = (numeratorResVal/denomeratorResVal)
                if(wholeFractionResVal!=0) wholeFractionRes.text=wholeFractionResVal.toString()//целая часть дроби res
                numeratorResVal-=wholeFractionResVal*denomeratorResVal //числитель
                if(numeratorResVal!=0) {
                    while (numeratorResVal % 2 == 0 && denomeratorResVal % 2 == 0) { //сокращаю дробь
                        numeratorResVal /= 2
                        denomeratorResVal /= 2
                    }
                    while (numeratorResVal % 3 == 0 && denomeratorResVal % 3 == 0) { //все еще сокращаю дробь
                        numeratorResVal /= 3
                        denomeratorResVal /= 3
                    }
                    while (numeratorResVal % 5 == 0 && denomeratorResVal % 5 == 0) { //все еще сокращаю дробь
                        numeratorResVal /= 5
                        denomeratorResVal /= 5
                    }
                    while (numeratorResVal % 7 == 0 && denomeratorResVal % 7 == 0) { //все еще сокращаю дробь
                        numeratorResVal /= 7
                        denomeratorResVal /= 7
                    }
                    numeratorRes.text = numeratorResVal.toString()
                    denomeratorRes.text = denomeratorResVal.toString()
                }
                else {
                    numeratorRes.text = null
                    denomeratorRes.text = null
                }

            }
            else Toast.makeText(this, getString(R.string.NullInputValues), Toast.LENGTH_SHORT).show()

        }
        buttondiv.setOnClickListener {
            wholeFractionRes.text=null
            numeratorRes.text = null
            denomeratorRes.text = null
            var wholeFraction1Int=wholeFraction1.text.toString().toIntOrNull()
            val denomerator1Int=denomerator1.text.toString().toIntOrNull()
            val numerator1Int = numerator1.text.toString().toIntOrNull()
            var wholeFraction2Int = wholeFraction2.text.toString().toIntOrNull()
            val denomerator2Int = denomerator2.text.toString().toIntOrNull()
            val numerator2Int =numerator2.text.toString().toIntOrNull()
            if(numerator1Int!=null && denomerator1Int!=null && numerator2Int!=null  && denomerator2Int!=null){
                if(wholeFraction1Int==null) wholeFraction1Int = 0
                if(wholeFraction2Int==null) wholeFraction2Int = 0
                textViewSign.text = ":"
                textViewEqual.text = "="
                var numeratorResVal=(wholeFraction1Int!! *denomerator1Int+numerator1Int)*denomerator2Int
                var denomeratorResVal = (denomerator1Int*(wholeFraction2Int!! *denomerator2Int+numerator2Int))
                var wholeFractionResVal = (numeratorResVal/denomeratorResVal)
                if(wholeFractionResVal!=0) wholeFractionRes.text=wholeFractionResVal.toString()//целая часть дроби res
                numeratorResVal-= wholeFractionResVal*denomeratorResVal//числитель
                if(numeratorResVal!=0) {
                    while (numeratorResVal % 2 == 0 && denomeratorResVal % 2 == 0) { //сокращаю дробь
                        numeratorResVal /= 2
                        denomeratorResVal /= 2
                    }
                    while (numeratorResVal % 3 == 0 && denomeratorResVal % 3 == 0) { //все еще сокращаю дробь
                        numeratorResVal /= 3
                        denomeratorResVal /= 3
                    }
                    while (numeratorResVal % 5 == 0 && denomeratorResVal % 5 == 0) { //все еще сокращаю дробь
                        numeratorResVal /= 5
                        denomeratorResVal /= 5
                    }
                    while (numeratorResVal % 7 == 0 && denomeratorResVal % 7 == 0) { //все еще сокращаю дробь
                        numeratorResVal /= 7
                        denomeratorResVal /= 7
                    }
                    numeratorRes.text = numeratorResVal.toString()
                    denomeratorRes.text = denomeratorResVal.toString()
                }
                else {
                    numeratorRes.text = null
                    denomeratorRes.text = null
                }
            }
            else Toast.makeText(this, getString(R.string.NullInputValues), Toast.LENGTH_SHORT).show()

        }

    }
}

