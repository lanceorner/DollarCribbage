package net.orner.dollarcribbage.app

import junit.framework.TestCase.*
import org.junit.Test

class CribbageCalcTest {
    @Test
    fun testWorks() {
        assertTrue(true)
    }

    @Test
    fun basicScoreCall() {
        val calc = CribbageCalc()
        assertEquals(3, calc.calcHand("345"))
        assertEquals(46, calc.calcHand("34556778"))
        assertEquals(594, calc.calcHand("3333333333"))
        assertEquals(29, calc.calcHand("5589000"))
        assertEquals(2, calc.calcHand("50"))
    }

    @Test
    fun isValidTest() {
        val calc = CribbageCalc()
        assertTrue(calc.isValidHand("012345"))
        assertFalse(calc.isValidHand("zzz"))
        assertFalse(calc.isValidHand("123zzz"))
        assertTrue(calc.isValidHand("12345678901234"))
        assertFalse(calc.isValidHand("123456789012345"))
    }
}