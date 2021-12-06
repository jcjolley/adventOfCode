package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day3Test {
    private val sut = Day3()

    @Test
    fun `Day 3 - Binary Diagnostic `() = runBlocking {
        val result = sut.partOne()
        assertEquals(2743844, result)
    }

    @Test
    fun `Day 3 - Binary Diagnostic - Part 2`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(6677951, result)
    }

    @Test
    fun partOneTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns """00100
            |11110
            |10110
            |10111
            |10101
            |01111
            |00111
            |11100
            |10000
            |11001
            |00010
            |01010
            |
        """.trimMargin()

        val result = sut.partOne()
        assertEquals(198, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns """00100
            |11110
            |10110
            |10111
            |10101
            |01111
            |00111
            |11100
            |10000
            |11001
            |00010
            |01010
            |
        """.trimMargin()

        val result = sut.partTwo()
        assertEquals(230, result)
    }
}