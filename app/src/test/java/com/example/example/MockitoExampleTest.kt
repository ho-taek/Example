package com.example.example

import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException


@RunWith(MockitoJUnitRunner::class)
class MockitoExampleTest {

    //테스트 메소드로 인식
    @Test
    fun example() {
        //MockitoExample 클래스를 mocking하여 객체로 만들어준다.
        val example = Mockito.mock(MockitoExample::class.java)

        //getId()가 호출될때 100을 리턴함.
        Mockito.`when`(example.getId()).thenReturn(100)
        Mockito.`when`(example.getUrl(100))
            .thenReturn("https://codechacha.com")

        //Test 확인
        assertEquals(100, example.getId())
        assertEquals("https://codechacha.com", example.getUrl(example.getId()))

        //lenient() 불필요하게 쓰이는 stub 코드를 방지하기 위한 것 때문에 에러 발생하는데 이를 해결하기 위함.
        //이렇게도 쓸 수 있음
        val example2 = mock(MockitoExample::class.java)
        lenient().`when`(example2.getId()).thenReturn(100)
        lenient().`when`(example2.getUrl(100)).thenReturn("https://codechacha.com")
    }

    @Test
    fun example2() {
        //anyInt 안에 들어가는 parameter가 상관 없을때
        val example = mock(MockitoExample::class.java)
        `when`(example.getUrl(anyInt())).thenReturn("https://codechacha.com")

        assertEquals("https://codechacha.com", example.getUrl(10))
        //예외 처리
        `when`(example.getUrl(20)).thenThrow(IllegalStateException("예외 발생"))

        try {
            example.getUrl(20)
            fail()
        } catch (e: IllegalStateException) {
            assertEquals(e.message, "예외 발생")
        }

        doReturn(30).`when`(example).getId()
        assertEquals(30, example.getId())
    }

    @Test
    fun example3() {
        val example = mock(MockitoExample::class.java)
        `when`(example.getId()).thenReturn(100)
        `when`(example.getUrl(100)).thenReturn("https://codechacha.com")

        example.getId()
        example.getId()
        val url = example.getUrl(example.getId())

        verify(example).getUrl(ArgumentMatchers.eq(100)) // 1
        verify(example, times(3)).getId() // 2
        verify(example, atLeast(2)).getId() // 3
        verify(example, atLeast(1)).getUrl(100) // 4
    }

}