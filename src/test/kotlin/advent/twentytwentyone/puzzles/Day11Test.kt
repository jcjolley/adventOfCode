package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {
    private val sut = Day11()

    private val testInput = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
    """.trimIndent().split("\n").map { it.trim() }.filter { it.isNotBlank() }

    @Test
    fun `Part 1`() = runBlocking {
        val result = sut.partOne()
        assertEquals(1697, result)
    }

    @Test
    fun `Part 2`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(847044, result)
    }

    @Test
    fun partOneTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInput

        val result = sut.partOne()
        assertEquals(1656, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInput

        val result = sut.partTwo()
        assertEquals(288957, result)
    }
}
