package advent.twentytwentyone.puzzles

import advent.utilities.getInput

class Day1 {
    suspend fun partOne(): Int {
        val numbers = parseInput()

        return getIncreasing(numbers)
    }

    suspend fun partTwo(): Int {
        return parseInput()
            .windowed(3)
            .map { it.sum() }
            .run(::getIncreasing)
    }

    private fun getIncreasing(numbers: List<Int>): Int {
        return numbers.windowed(2).map { (a, b) -> if (b > a) 1 else 0 }.sum()
    }

    private suspend fun parseInput(): List<Int> {
        return getInput(2021, 1).map { it.toInt() }
    }
}
