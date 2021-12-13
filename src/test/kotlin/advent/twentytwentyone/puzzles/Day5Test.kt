package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day5Test {
    private val sut = Day5()

    @Test
    fun `Day 5 - Hydrothermal Venture - Part 1`() = runBlocking {
        val result = sut.partOne()
        assertEquals(6710, result)
    }

    @Test
    fun `Day 5 - Hydrothermal Venture - Part 2`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(10478, result)
    }

    @Test
    fun partOneTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns """
           |0,9 -> 5,9
           |8,0 -> 0,8
           |9,4 -> 3,4
           |2,2 -> 2,1
           |7,0 -> 7,4
           |6,4 -> 2,0
           |0,9 -> 2,9
           |3,4 -> 1,4
           |0,0 -> 8,8
           |5,5 -> 8,2 
        """.trimMargin().split("\n").map { it.trim() }.filter { it.isNotBlank() }

        val result = sut.partOne()
        assertEquals(5, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns """
           |0,9 -> 5,9
           |8,0 -> 0,8
           |9,4 -> 3,4
           |2,2 -> 2,1
           |7,0 -> 7,4
           |6,4 -> 2,0
           |0,9 -> 2,9
           |3,4 -> 1,4
           |0,0 -> 8,8
           |5,5 -> 8,2 
        """.trimMargin().split("\n").map { it.trim() }.filter { it.isNotBlank() }

        val result = sut.partTwo()
        assertEquals(12, result)
    }
}
