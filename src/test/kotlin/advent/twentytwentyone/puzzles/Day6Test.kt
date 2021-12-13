package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {
    private val sut = Day6()

    @Test
    fun `Day 6 - Lanternfish - Part 1`() = runBlocking {
        val result = sut.partOne()
        assertEquals(362740, result)
    }

    @Test
    fun `Day 6 - Lanternfish - Part 2`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(1644874076764, result)
    }

    @Test
    fun partOneTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns "3,4,3,1,2".split("\n").map { it.trim() }.filter { it.isNotBlank() }

        val result = sut.partOne()
        assertEquals(5934, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns "3,4,3,1,2".split("\n").map { it.trim() }.filter { it.isNotBlank() }

        val result = sut.partTwo()
        assertEquals(26984457539, result)
    }
}
