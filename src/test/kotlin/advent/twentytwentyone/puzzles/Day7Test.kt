package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7Test {
    private val sut = Day7()

    val testInput = "16,1,2,0,4,2,7,1,2,14"

    @Test
    fun `Day 7 - Part 1`() = runBlocking {
        val result = sut.partOne()
        assertEquals(344735, result)
    }

    @Test
    fun `Day 7 - Part 2`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(96798233, result)
    }

    @Test
    fun partOneTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInput

        val result = sut.partOne()
        assertEquals(37, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInput

        val result = sut.partTwo()
        assertEquals(168, result)
    }
}
