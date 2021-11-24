package com.example.tryapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        assertEquals(Triple(4,5,8), MulOrDiv().mul(2,5,2,3 ,15,10 ))
        assertEquals(Triple(0,0,6), MulOrDiv().mul(1,1,1,2 ,2,2 ))
        assertEquals(Triple(9,368,468), MulOrDiv().mul(104,368,2,4 ,2,1 ))
        assertEquals(Triple(0,0,5), MulOrDiv().mul(3,6,2, 1,6,3 ))
        assertEquals(Triple(2,9,4), MulOrDiv().mul(3,6,1, 1,3,1 ))
        assertEquals(Triple(1,11,0), MulOrDiv().mul(0,121,11, 0,1,1 ))


        assertEquals(Triple(36,55,0), MulOrDiv().div(2,5,2,3 ,15,10 ))
        assertEquals(Triple(2,3,0), MulOrDiv().div(1,1,1,2 ,2,2 ))
        assertEquals(Triple(9,10,0), MulOrDiv().div(1,6,3,1 ,9,6 ))
        assertEquals(Triple(2,3,0), MulOrDiv().div(1,1,1,2 ,2,2 ))
        assertEquals(Triple(351,625,0), MulOrDiv().div(3,25,3,5 ,9,5 ))
    }
}