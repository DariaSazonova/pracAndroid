package com.example.tryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MulOrDiv {
    fun mul(
        wholeFraction1Int: Int,
        denominator1Int: Int,
        numerator1Int: Int,
        wholeFraction2Int: Int,
        denominator2Int: Int,
        numerator2Int: Int
    ): Triple<Int?, Int?, Int?> {
        var numeratorResVal =
            (wholeFraction1Int * denominator1Int + numerator1Int) * (wholeFraction2Int * denominator2Int + numerator2Int)
        var denominatorResVal = (denominator1Int * denominator2Int)
        if (denominatorResVal == 0)
            return Triple(null, null, null)
        else {
            val wholeFractionResVal = (numeratorResVal / denominatorResVal)
            numeratorResVal -= wholeFractionResVal * denominatorResVal//числитель
            if (numeratorResVal != 0) {
                var numNod = numeratorResVal
                var demNod = denominatorResVal
                while (numNod != 0 && demNod != 0) {
                    if (numNod > demNod)
                        numNod %= demNod
                    else demNod %= numNod
                }
                numNod += demNod // тут НОД
                numeratorResVal /= numNod
                denominatorResVal /= numNod
            } else denominatorResVal = 0
            return Triple(numeratorResVal, denominatorResVal, wholeFractionResVal)
        }
    }

    fun div(
        wholeFraction1Int: Int,
        denominator1Int: Int,
        numerator1Int: Int,
        wholeFraction2Int: Int,
        denominator2Int: Int,
        numerator2Int: Int
    ): Triple<Int?, Int?, Int?> {
        var numeratorResVal =
            (wholeFraction1Int * denominator1Int + numerator1Int) * denominator2Int
        var denominatorResVal =
            (denominator1Int * (wholeFraction2Int * denominator2Int + numerator2Int))
        if (denominatorResVal == 0)
            return Triple(null, null, null)
        else {
            val wholeFractionResVal = (numeratorResVal / denominatorResVal)
            numeratorResVal -= wholeFractionResVal * denominatorResVal//числитель
            if (numeratorResVal != 0) {
                var numNod = numeratorResVal
                var demNod = denominatorResVal
                while (numNod != 0 && demNod != 0) {
                    if (numNod > demNod)
                        numNod %= demNod
                    else demNod %= numNod

                }
                numNod += demNod // тут НОД
                numeratorResVal /= numNod
                denominatorResVal /= numNod
            } else denominatorResVal = 0
            return Triple(numeratorResVal, denominatorResVal, wholeFractionResVal)
        }
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var  wholeFraction1:EditText
    private lateinit var wholeFraction2:EditText
    private lateinit var wholeFractionRes:TextView
    private lateinit var numerator1:EditText
    private lateinit var numerator2:EditText
    private lateinit var numeratorRes:TextView
    private lateinit var denominator1:EditText
    private lateinit var denominator2:EditText
    private lateinit var denominatorRes:TextView
    private lateinit var buttonMul:Button
    private lateinit var buttondiv:Button
    private lateinit var db:DataBase



    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                var viewData = db.localDao().getAll()
                if(viewData!=null){
                    wholeFractionRes.text = viewData.wholeRes
                    denominatorRes.text = viewData.denominatorRes
                    numeratorRes.text = viewData.numeratorRes
                    wholeFraction1.hint = viewData.whole1
                    wholeFraction2.hint = viewData.whole2
                    numerator1.hint = viewData.numerator1
                    numerator2.hint = viewData.numerator2
                    denominator2.hint = viewData.denominator2
                    denominator1.hint = viewData.denominator1

                }

            }
        }

    }

    override fun onStop() {
        super.onStop()
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                var addData = LocalModel(
                    wholeFraction1.text.toString(),
                    denominator1.text.toString(),
                    numerator1.text.toString(),
                    wholeFraction2.text.toString(),
                    denominator2.text.toString(),
                    numerator2.text.toString(),
                    wholeFractionRes.text.toString(),
                    denominatorRes.text.toString(),
                    numeratorRes.text.toString()
                )
                db.localDao().deleteAll()
                db.localDao().insert(addData)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wholeFraction1 = findViewById<EditText>(R.id.editWholeFraction1)
        wholeFraction2 = findViewById<EditText>(R.id.editWholeFraction2)
        wholeFractionRes = findViewById<TextView>(R.id.editWholeFractionRes)
        numerator1 = findViewById<EditText>(R.id.editNumerator1)
        numerator2 = findViewById<EditText>(R.id.editNumerator2)
        numeratorRes = findViewById<TextView>(R.id.editNumeratorRes)
        denominator1 = findViewById<EditText>(R.id.editDenomirator1)
        denominator2 = findViewById<EditText>(R.id.editDenomirator2)
        denominatorRes = findViewById<TextView>(R.id.editDenomiratorRes)
        buttonMul = findViewById<Button>(R.id.buttonMul)
        buttondiv = findViewById<Button>(R.id.buttonDil)
        db = Room.databaseBuilder(this, DataBase::class.java, "database").build()


        val fractionMulOrDiv = MulOrDiv()

        wholeFractionRes.text = null
        numeratorRes.text = null
        denominatorRes.text = null




        buttonMul.setOnClickListener {
            if(wholeFraction1.length()<5 && wholeFraction2.length()<5 && numerator1.length()<5 && numerator2.length()<5&& denominator1.length()<5&& denominator2.length()<5 ) {


                val wholeFraction1Int = wholeFraction1.text.toString().toIntOrNull()
                val denominator1Int = denominator1.text.toString().toIntOrNull()
                val numerator1Int = numerator1.text.toString().toIntOrNull()
                val wholeFraction2Int = wholeFraction2.text.toString().toIntOrNull()
                val denominator2Int = denominator2.text.toString().toIntOrNull()
                val numerator2Int = numerator2.text.toString().toIntOrNull()

                if (numerator1Int != null && denominator1Int != null && numerator2Int != null && denominator2Int != null && wholeFraction1Int != null && wholeFraction2Int != null) {
                    if (denominator1Int != 0 && denominator2Int != 0) {
                        val res = fractionMulOrDiv.mul(
                            wholeFraction1Int,
                            denominator1Int,
                            numerator1Int,
                            wholeFraction2Int,
                            denominator2Int,
                            numerator2Int
                        )
                        if (res.third != null) {
                            wholeFractionRes.text = res.third.toString()
                            numeratorRes.text = res.first.toString()
                            denominatorRes.text = res.second.toString()
                        } else
                            Toast.makeText(this, getString(R.string.divZero), Toast.LENGTH_SHORT)
                                .show()


                    } else Toast.makeText(this, getString(R.string.NullValues), Toast.LENGTH_SHORT)
                        .show()
                } else Toast.makeText(this, getString(R.string.NullInputValues), Toast.LENGTH_SHORT)
                    .show()
            }else  Toast.makeText(this, getString(R.string.ManyNum), Toast.LENGTH_SHORT)
                .show()

        }
        buttondiv.setOnClickListener {
            if (wholeFraction1.length() < 5 && wholeFraction2.length() < 5 && numerator1.length() < 5 && numerator2.length() < 5 && denominator1.length() < 5 && denominator2.length() < 5) {
                val wholeFraction1Int = wholeFraction1.text.toString().toIntOrNull()
                val denominator1Int = denominator1.text.toString().toIntOrNull()
                val numerator1Int = numerator1.text.toString().toIntOrNull()
                val wholeFraction2Int = wholeFraction2.text.toString().toIntOrNull()
                val denominator2Int = denominator2.text.toString().toIntOrNull()
                val numerator2Int = numerator2.text.toString().toIntOrNull()
                if (numerator1Int != null && denominator1Int != null && numerator2Int != null && denominator2Int != null && wholeFraction1Int != null && wholeFraction2Int != null) {
                    if (denominator1Int != 0 && denominator2Int != 0) {
                        val res = fractionMulOrDiv.div(
                            wholeFraction1Int,
                            denominator1Int,
                            numerator1Int,
                            wholeFraction2Int,
                            denominator2Int,
                            numerator2Int
                        )
                        if (res.third != null) {
                            wholeFractionRes.text = res.third.toString()
                            numeratorRes.text = res.first.toString()
                            denominatorRes.text = res.second.toString()
                        } else
                            Toast.makeText(this, getString(R.string.divZero), Toast.LENGTH_SHORT)
                                .show()

                    } else Toast.makeText(this, getString(R.string.NullValues), Toast.LENGTH_SHORT)
                        .show()
                } else Toast.makeText(this, getString(R.string.NullInputValues), Toast.LENGTH_SHORT)
                    .show()

            }else  Toast.makeText(this, getString(R.string.ManyNum), Toast.LENGTH_SHORT).show()
        }

    }

}
