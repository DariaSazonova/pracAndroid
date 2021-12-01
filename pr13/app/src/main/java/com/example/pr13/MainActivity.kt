package com.example.pr13

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    private var db : DataBase? = null
    private var list : List<Animal> = listOf()
    private val adapter = Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button = findViewById<Button>(R.id.buttonF)
        button.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editTextTextPersonName)

            db = Room.databaseBuilder(this, DataBase::class.java, "database").build()
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
            val API_BASE_URL =" https://cat-fact.herokuapp.com/"
            val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ApiService::class.java)
            val text = editText.text.toString()
            val call = service.getSchedule(text)
            call.enqueue(object : Callback<List<Animal>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(
                    call: Call<List<Animal>>,
                    response: Response<List<Animal>>
                ) {
                    if (response.isSuccessful) {
                        list = response.body()!!
                        adapter.setList(list)
                        adapter.notifyDataSetChanged()
                        saveData()
                    } else {
                        var error = getString(R.string.Err) + response.code().toString()
                        findViewById<TextView>(R.id.textView).text = error
                        getSavedData()
                    }
                }

                override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                    findViewById<TextView>(R.id.textView).text = getString(R.string.error)
                    getSavedData()
                }

            })
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getSavedData() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                val savedList = db?.localDao()?.getAll()
                val newList = mutableListOf<Animal>()
                for (item in savedList!!) newList.add(Animal(item.text, item.type))
                adapter.setList(newList)
                list = newList
            }
            adapter.setList(list)
            adapter.notifyDataSetChanged()
        }
    }

    private fun saveData() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                db?.localDao()?.deleteAll()
                if (list.isNotEmpty())
                    list.forEach {
                        db?.localDao()?.insert(
                            LocalModel(
                                it.type!!,
                                it.text!!
                            )
                        )
                    }
            }
        }
    }
    @Test
    fun test1() {
        var lm = LocalModel("text", "cat")
        var db: DataBase? = Room.databaseBuilder(applicationContext, DataBase::class.java, "database").build()
        db?.localDao()?.insert(lm)
        var list = db?.localDao()?.getAll()?.get(1)
        Assert.assertEquals(lm, list)
    }

    override fun onPause() {
        saveData()
        super.onPause()
    }
}
