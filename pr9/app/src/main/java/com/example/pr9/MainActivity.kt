package com.example.pr9

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View.VISIBLE
import android.widget.*
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    var db: Table = DataTable()
    var nullDataRow: NullableDataRow = NullableDataRow()
    private var photo:Bitmap? = null

    private val listColumns: List<Column> =
        listOf(CinemaColumn(), FilmColumn(), HallColumn(), DateColumn(), TimeColumn())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonAdd = findViewById<Button>(R.id.buttonAdd)
        var buttonEdit = findViewById<Button>(R.id.buttonEdit)
        var buttonDelete = findViewById<Button>(R.id.buttonDelete)
        var buttonSort = findViewById<Button>(R.id.buttonSort)
        var buttonSearch = findViewById<Button>(R.id.buttonSearch)
        var buttonPrint = findViewById<Button>(R.id.buttonPrint)
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        var editTextSearch = findViewById<EditText>(R.id.editTextSearch)
        var buttonAddPhoto = findViewById<Button>(R.id.buttonAddphoto)

        val cinema = findViewById<EditText>(R.id.editTextCinema)
        val film = findViewById<EditText>(R.id.editTextFilm)
        val hall = findViewById<EditText>(R.id.editTextHall)
        val date = findViewById<EditText>(R.id.editTextDate)
        val time = findViewById<EditText>(R.id.editTextTime)
        val buttonSave = findViewById<Button>(R.id.buttonSave)

        val textViewRow = findViewById<TextView>(R.id.textViewRow)
        val editTextNumberRow = findViewById<EditText>(R.id.editTextNumberRow)

        val adapter = Adapter()

        val nullDataRow: NullableDataRow = NullableDataRow()

        buttonAdd.setOnClickListener {
            recyclerView.isVisible = false
            editTextSearch.isVisible = false
            buttonAddPhoto.isVisible = true
            textViewRow.isVisible = false
            editTextNumberRow.isVisible = false
            cinema.isVisible = true
            film.isVisible = true
            hall.isVisible = true
            date.isVisible = true
            time.isVisible = true
            buttonSave.isVisible = true
            cinema.text = null
            film.text = null
            hall.text = null
            date.text = null
            time.text = null
            buttonSave.text = getString(R.string.save)


        }
        buttonDelete.setOnClickListener {
            textViewRow.text = getString(R.string.EnterDel)
            editTextSearch.isVisible = false
            recyclerView.isVisible = false
            textViewRow.isVisible = true
            editTextNumberRow.isVisible = true
            cinema.isVisible = false
            film.isVisible = false
            hall.isVisible = false
            date.isVisible = false
            time.isVisible = false
            buttonSave.isVisible = true
            buttonSave.text = getString(R.string.del)
            editTextNumberRow.text = null
            buttonAddPhoto.isVisible = false

        }
        buttonEdit.setOnClickListener {
            textViewRow.text = getString(R.string.enterEdit)
            editTextSearch.isVisible = false
            recyclerView.isVisible = false
            cinema.isVisible = true
            film.isVisible = true
            hall.isVisible = true
            date.isVisible = true
            time.isVisible = true
            buttonSave.isVisible = true
            textViewRow.isVisible = true
            editTextNumberRow.isVisible = true
            buttonAddPhoto.isVisible = true
            buttonSave.text = getString(R.string.save)

            cinema.text = null
            film.text = null
            hall.text = null
            date.text = null
            time.text = null
            editTextNumberRow.text = null

        }
        buttonSort.setOnClickListener {
            editTextSearch.isVisible = false
            recyclerView.isVisible = false
            textViewRow.isVisible = true
            editTextNumberRow.isVisible = true
            cinema.isVisible = false
            buttonAddPhoto.isVisible = false
            film.isVisible = false
            hall.isVisible = false
            date.isVisible = false
            time.isVisible = false
            buttonSave.isVisible = true
            buttonSave.text = getString(R.string.sort)
            editTextNumberRow.text = null
            textViewRow.text = getString(R.string.entersort)
        }
        buttonSearch.setOnClickListener {
            editTextSearch.isVisible = false
            recyclerView.isVisible = false
            textViewRow.isVisible = true
            editTextNumberRow.isVisible = true
            cinema.isVisible = false
            film.isVisible = false
            hall.isVisible = false
            date.isVisible = false
            time.isVisible = false
            buttonSave.isVisible = true
            buttonSave.text = getString(R.string.search)
            editTextNumberRow.text = null
            textViewRow.text = getString(R.string.entersearch)
            editTextSearch.isVisible = true
            buttonAddPhoto.isVisible = false
        }
        buttonSave.setOnClickListener {
            val cinemaText = cinema.text.toString()
            val filmText = film.text.toString()
            val hallText = hall.text.toString()
            val dateText = date.text.toString()
            val timeText = time.text.toString()
            if (cinemaText.isNotEmpty() && filmText.isNotEmpty() && hallText.isNotEmpty()
                && hallText.isNotEmpty() && dateText.isNotEmpty() && timeText.isNotEmpty() &&photo!=null
            ) {
                if ("(0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](\\d{4}|\\d{2})".toRegex()
                        .matches(dateText)
                )
                    if ("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]\$".toRegex().matches(timeText)) {
                        nullDataRow.cinema = cinemaText
                        nullDataRow.film = filmText
                        nullDataRow.hall = hallText.toInt()
                        nullDataRow.date = dateText
                        nullDataRow.time = timeText
                        nullDataRow.photo = photo
                        photo=null
                        if (!textViewRow.isVisible)
                            db.add(nullDataRow.asDataRow())
                        else if (buttonSave.text.toString() == getString(R.string.del)) {
                            if (db.getList().size >= editTextNumberRow.text.toString()
                                    .toInt() - 1 && editTextNumberRow.text.toString().isNotEmpty()
                            )
                                db.delete(editTextNumberRow.text.toString().toInt() - 1)
                        } else if (buttonSave.text.toString() == getString(R.string.sort)) {
                            if (listColumns.size >= editTextNumberRow.text.toString()
                                    .toInt() - 1 && editTextNumberRow.text.toString().isNotEmpty()
                            ) {
                                recyclerView.isVisible = true
                                val list = listColumns[editTextNumberRow.text.toString()
                                    .toInt() - 1]
                                    .sort(db.getList())
                                adapter.setList(list)


                            }
                        } else if (buttonSave.text.toString() == getString(R.string.search)) {
                            if (listColumns.size >= editTextNumberRow.text.toString()
                                    .toInt() - 1 && editTextNumberRow.text.toString().isNotEmpty()
                            ) {
                                recyclerView.isVisible = true
                                val list = listColumns[editTextNumberRow.text.toString()
                                    .toInt() - 1]
                                    .search(db.getList(), editTextSearch.text.toString())
                                adapter.setList(list)
                            }
                        } else {
                                if (db.getList().size >= editTextNumberRow.text.toString()
                                        .toInt() - 1 && editTextNumberRow.text.toString()
                                        .isNotEmpty()
                                )
                                    db.set(
                                        editTextNumberRow.text.toString().toInt() - 1,
                                        nullDataRow.asDataRow()
                                    )
                                else Toast.makeText(
                                    this,
                                    getString(R.string.notIndex),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }


                            cinema.isVisible = false
                            film.isVisible = false
                            hall.isVisible = false
                            date.isVisible = false
                            time.isVisible = false
                            buttonAddPhoto.isVisible = false
                            buttonSave.isVisible = false
                            textViewRow.isVisible = false
                            editTextNumberRow.isVisible = false
                            editTextSearch.isVisible = false
                        } else Toast.makeText(
                            this,
                            getString(R.string.wrongString),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else Toast.makeText(
                        this,
                        getString(R.string.nullString),
                        Toast.LENGTH_SHORT
                    ).show()

            }
            buttonAddPhoto.setOnClickListener {
                val intent = Intent()
                intent.action=MediaStore.ACTION_IMAGE_CAPTURE
                val intentCreateChooser = Intent.createChooser(intent, null)
                startActivityForResult(intentCreateChooser, REQUEST_IMAGE_CAPTURE)

            }

            buttonPrint.setOnClickListener {
                recyclerView.isVisible = true
                buttonAddPhoto.isVisible = false
                editTextSearch.isVisible = false
                cinema.isVisible = false
                film.isVisible = false
                hall.isVisible = false
                date.isVisible = false
                time.isVisible = false
                buttonSave.isVisible = false
                textViewRow.isVisible = false
                editTextNumberRow.isVisible = false

                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
                adapter.setList(db.getList())


            }


        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == REQUEST_IMAGE_CAPTURE) && (resultCode == RESULT_OK)){
            photo = data?.extras?.get("data") as Bitmap
            //val imageView = findViewById<ImageView>(R.id.imageview)
            //imageView.setImageBitmap(bitmap)
        }
    }
    companion object{
        const val REQUEST_IMAGE_CAPTURE=1
    }
}

