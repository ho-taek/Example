package com.example.example

import com.example.example.presentation.Calculator
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class CalculatorTest{

    private lateinit var calculator : Calculator

    //테스트 시작전 실행되어야 할 동작(객체초기화)
    @Before
    fun setUp(){
        calculator = Calculator()
    }

    //Before 이후 돌아가는 테스트 케이스 코드
    @Test
    fun addTest(){
        val result = calculator.add(2,4)
        assertEquals(6, result)
    }

    @Test
    fun addNegativeTest(){
        val result = calculator.add(-4,-2)
        assertEquals(-6, result)
    }

    @Test
    fun subtractTest(){
        val result = calculator.subtract(6,4)
        assertEquals(2, result)
    }

    @Test
    fun subtractNegativeTest(){
        val result = calculator.subtract(-2,-4)
        assertEquals(2, result)

    }
}