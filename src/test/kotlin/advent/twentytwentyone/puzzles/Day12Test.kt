package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day12Test {
    private val sut = Day12()

    private val testInputOne = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
    """.trimIndent().split("\n").map { it.trim() }.filter { it.isNotBlank() }

    @Test
    fun `Part 1`() = runBlocking {
        val result = sut.partOne()
        assertEquals(1697, result)
    }

    @Test
    fun `Part 2`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(344, result)
    }

    @Test
    fun partOneTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInputOne

        val result = sut.partOne()
        assertEquals(1656, result)
    }

    @Test
    fun partTwoTestCase() = runBlocking {
        mockkStatic(::getInput)
        coEvery { getInput(any(), any()) } returns testInputOne

        val result = sut.partTwo()
        assertEquals(288957, result)
    }
}
