package advent.twentytwentyone.puzzles

import advent.utilities.getInput

class Day11 {

    suspend fun partOne(): Long {
        return 0L
    }

    private suspend fun parseInput(): List<String> {
        return getInput(2021, 11)
            .split("\n")
            .filter { it.isNotBlank() }
    }

    suspend fun partTwo(): Long {
        return 0L
    }
}
