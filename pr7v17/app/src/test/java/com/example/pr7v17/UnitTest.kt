package com.example.pr7v17

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun addition_isCorrect() {
        val mullOrDiv=MulOrDiv()
        assertEquals(Triple(4,5,8), MulOrDiv().mul(2,5,2,3 ,15,10 ))
        assertEquals(Triple(0,0,6), MulOrDiv().mul(1,1,1,2 ,2,2 ))



        assertEquals(Triple(36,55,0), MulOrDiv().div(2,5,2,3 ,15,10 ))
        assertEquals(Triple(2,3,0), MulOrDiv().div(1,1,1,2 ,2,2 ))
    }
}