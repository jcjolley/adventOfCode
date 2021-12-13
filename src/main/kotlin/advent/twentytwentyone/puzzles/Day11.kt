package advent.twentytwentyone.puzzles

import advent.utilities.getInput

private typealias Octopi = List<List<Int>>

private val adjacentCoords =
    (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }.filter { !(it.first == 0 && it.second == 0) }

private fun Octopi.print() {
    this.forEach { println(it) }
}


class Day11 {
    val adjacentCoords =
        (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }.filter { !(it.first == 0 && it.second == 0) }


    suspend fun partOne(): Long {
        return 0L
    }

    private suspend fun parseInput(): List<List<Int>> {
        return getInput(2021, 11)
            .split("\n")
            .filter { it.isNotBlank() }
            .map { line -> line.split("").map { it.toInt() } }
    }

    suspend fun partTwo(): Long {
        return 0L
    }
}
