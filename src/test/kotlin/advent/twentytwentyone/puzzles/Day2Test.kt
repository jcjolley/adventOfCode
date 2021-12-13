package advent.twentytwentyone.puzzles.one

import advent.twentytwentyone.puzzles.Day2
import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day2Test {

    val sut = Day2()

    @Test
    fun `Day 2 - Dive! - Part One`() = runBlocking {
        val result = sut.partOne()
        assertEquals(2215080, result)
    }

    @Test
    fun `Day 2 - Dive! - Part Two`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(1864715580, result)
    }

    @Test
    fun DayTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns """forward 5
            |down 5
            |forward 8
            |up 3
            |down 8
            |forward 2
            |
        """.trimMargin().split("\n").map { it.trim() }.filter { it.isNotBlank() }

        val result = sut.partTwo()
        assertEquals(900, result)
    }
}
