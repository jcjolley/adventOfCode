package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10Test {
    private val sut = Day10()

    val testInput = """[({(<(())[]>[[{[]{<()<>>
        |[(()[<>])]({[<{<<[]>>(
        |{([(<{}[<>[]}>{[]{[(<()>
        |(((({<>}<{<{<>}{[]{[]{}
        |[[<[([]))<([[{}[[()]]]
        |[{[{({}]{}}([{[{{{}}([]
        |{<[[]]>}<{[{[{[]{()[[[]
        |[<(<(<(<{}))><([]([]()
        |<{([([[(<>()){}]>(<<{{
        |<{([{{}}[<[[[<>{}]]]>[]]
    """.trimMargin().split("\n").map { it.trim() }.filter { it.isNotBlank() }
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
