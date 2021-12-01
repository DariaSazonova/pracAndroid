package com.example.pr13

import androidx.room.Room
import org.junit.Assert
import org.junit.Test
import androidx.test.core.app.ApplicationProvider
import android.content.Context

import org.junit.Assert.*
import org.junit.Before
import org.junit.After
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    private lateinit var database : DataBase

    @Before
    fun setup()
    {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, DataBase::class.java)
            .allowMainThreadQueries()
            .build()
    }


    @Test
    fun test1() {
        database.localDao().deleteAll()
        database.localDao().insert(LocalModel("some text", "cat"))
        val check = database.localDao().getAll().first()
        assertEquals(check.type, "cat")
        assertEquals(check.text, "some text")
    }
    @Test
    fun test2() {
        database.localDao().deleteAll()
        val check = database.localDao().getAll()
        assertEquals(check.size, 0)
    }
    @Test
    fun test3() {
        database.localDao().insert(LocalModel("some text", "cat1"))
        database.localDao().insert(LocalModel("some text", "cat2"))
        database.localDao().insert(LocalModel("some text", "cat3"))
        val check = database.localDao().getAll()
        assertEquals(check.size, 3)
    }
}