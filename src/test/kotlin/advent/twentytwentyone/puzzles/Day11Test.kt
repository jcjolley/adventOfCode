package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day11Test {
    private val sut = Day11()

    val testInput = """5483143223
            |2745854711
            |5264556173
            |6141336146
            |6357385478
            |4167524645
            |2176841721
            |6882881134
            |4846848554
            |5283751526
    """.trimMargin()
    @Test
    fun `Part 1`() = runBlocking {
        val result = sut.partOne()
        assertEquals(290691, result)
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
        assertEquals(26397, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInput

        val result = sut.partTwo()
        assertEquals(288957, result)
    }
}
