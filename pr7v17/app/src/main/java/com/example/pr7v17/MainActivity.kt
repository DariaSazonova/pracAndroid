package com.example.pr7v17

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
//import android.util.Log


class MulOrDiv {

    fun mul(
        wholeFraction1Int: Int,
        denomerator1Int: Int,
        numerator1Int: Int,
        wholeFraction2Int: Int,
        denomerator2Int: Int,
        numerator2Int: Int
    ): Triple<Int, Int, Int> {
        var numeratorResVal =
            (wholeFraction1Int * denomerator1Int + numerator1Int) * (wholeFraction2Int * denomerator2Int + numerator2Int)
        var denomeratorResVal = (denomerator1Int * denomerator2Int)
        val wholeFractionResVal = (numeratorResVal / denomeratorResVal)
        //if (wholeFractionResVal != 0) wholeFractionRes = wholeFractionResVal.toString()//целая часть дроби res
        numeratorResVal -= wholeFractionResVal * denomeratorResVal //числитель
        if (numeratorResVal != 0) {
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

        }
        else denomeratorResVal=0
        return Triple(numeratorResVal, denomeratorResVal, wholeFractionResVal)
    }

    fun div(
        wholeFraction1Int: Int,
        denomerator1Int: Int,
        numerator1Int: Int,
        wholeFraction2Int: Int,
        denomerator2Int: Int,
        numerator2Int: Int
    ): Triple<Int, Int, Int> {
        var numeratorResVal =
            (wholeFraction1Int * denomerator1Int + numerator1Int) * denomerator2Int
        var denomeratorResVal =
            (denomerator1Int * (wholeFraction2Int * denomerator2Int + numerator2Int))
        val wholeFractionResVal = (numeratorResVal / denomeratorResVal)
        numeratorResVal -= wholeFractionResVal * denomeratorResVal//числитель
        if (numeratorResVal != 0) {
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

        }else denomeratorResVal=0

        return Triple(numeratorResVal, denomeratorResVal, wholeFractionResVal)
    }

}
    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val wholeFraction1 = findViewById<EditText>(R.id.editWholeFraction1)
            val wholeFraction2 = findViewById<EditText>(R.id.editWholeFraction2)
            val wholeFractionRes = findViewById<TextView>(R.id.editWholeFractionRes)
            val numerator1 = findViewById<EditText>(R.id.editNumerator1)
            val numerator2 = findViewById<EditText>(R.id.editNumerator2)
            val numeratorRes = findViewById<TextView>(R.id.editNumeratorRes)
            val denomerator1 = findViewById<EditText>(R.id.editDenomirator1)
            val denomerator2 = findViewById<EditText>(R.id.editDenomirator2)
            val denomeratorRes = findViewById<TextView>(R.id.editDenomiratorRes)
            val buttonMul = findViewById<Button>(R.id.buttonMul)
            val buttondiv = findViewById<Button>(R.id.buttonDil)


            val fractionMulOrDiv = MulOrDiv()

            wholeFractionRes.text = null
            numeratorRes.text = null
            denomeratorRes.text = null




            buttonMul.setOnClickListener {
                val wholeFraction1Int = wholeFraction1.text.toString().toIntOrNull()
                val denomerator1Int = denomerator1.text.toString().toIntOrNull()
                val numerator1Int = numerator1.text.toString().toIntOrNull()
                val wholeFraction2Int = wholeFraction2.text.toString().toIntOrNull()
                val denomerator2Int = denomerator2.text.toString().toIntOrNull()
                val numerator2Int = numerator2.text.toString().toIntOrNull()

                if (numerator1Int != null && denomerator1Int != null && numerator2Int != null && denomerator2Int != null && wholeFraction1Int != null && wholeFraction2Int != null) {
                    if (numerator1Int != 0 && denomerator1Int != 0 && numerator2Int != 0 && denomerator2Int != 0 && wholeFraction1Int != 0 && wholeFraction2Int != 0) {
                        val res: Triple<Int, Int, Int> = fractionMulOrDiv.mul(
                            wholeFraction1Int,
                            denomerator1Int,
                            numerator1Int,
                            wholeFraction2Int,
                            denomerator2Int,
                            numerator2Int
                        )
                        wholeFractionRes.text = res.third.toString()
                        numeratorRes.text = res.first.toString()
                        denomeratorRes.text = res.second.toString()

                    } else Toast.makeText(this, getString(R.string.NullValues), Toast.LENGTH_SHORT)
                        .show()
                } else Toast.makeText(this, getString(R.string.NullInputValues), Toast.LENGTH_SHORT)
                    .show()

            }
            buttondiv.setOnClickListener {
                val wholeFraction1Int = wholeFraction1.text.toString().toIntOrNull()
                val denomerator1Int = denomerator1.text.toString().toIntOrNull()
                val numerator1Int = numerator1.text.toString().toIntOrNull()
                val wholeFraction2Int = wholeFraction2.text.toString().toIntOrNull()
                val denomerator2Int = denomerator2.text.toString().toIntOrNull()
                val numerator2Int = numerator2.text.toString().toIntOrNull()
                if (numerator1Int != null && denomerator1Int != null && numerator2Int != null && denomerator2Int != null && wholeFraction1Int != null && wholeFraction2Int != null) {
                    if (numerator1Int != 0 && denomerator1Int != 0 && numerator2Int != 0 && denomerator2Int != 0 && wholeFraction1Int != 0 && wholeFraction2Int != 0) {
                        val res: Triple<Int, Int, Int> = fractionMulOrDiv.div(
                            wholeFraction1Int,
                            denomerator1Int,
                            numerator1Int,
                            wholeFraction2Int,
                            denomerator2Int,
                            numerator2Int
                        )
                        wholeFractionRes.text = res.third.toString()
                        numeratorRes.text = res.first.toString()
                        denomeratorRes.text = res.second.toString()

                    } else Toast.makeText(this, getString(R.string.NullValues), Toast.LENGTH_SHORT)
                        .show()
                } else Toast.makeText(this, getString(R.string.NullInputValues), Toast.LENGTH_SHORT)
                    .show()

            }

        }
    }


