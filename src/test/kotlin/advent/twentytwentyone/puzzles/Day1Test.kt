package advent.twentytwentyone.puzzles.one

import advent.twentytwentyone.puzzles.Day1
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day1Test {

    val sut = Day1()

    @Test
    fun `Day 1 - Sonar Sweep - Part One`() = runBlocking {
       val result = sut.partOne()
       assertEquals(1374, result)
    }

    @Test
    fun `Day 1 - Sonar Sweep - Part Two`() = runBlocking {
        val result = sut.partTwo()
        assertEquals(1418, result)
    }

}