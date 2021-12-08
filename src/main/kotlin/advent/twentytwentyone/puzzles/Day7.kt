package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import java.lang.Math.abs

class Day7 {

    suspend fun partOne(): Int {
        val crabs = parseInput()
        return findMinimumAlignment(crabs, ::getDistance)
    }

    private fun findMinimumAlignment(crabs: List<Int>, distanceFn: (Int, Int) -> Int): Int {
        return (0..crabs.maxOrNull()!!).map { i ->
            i to crabs.sumOf { distanceFn(i, it) }
        }.minByOrNull { it.second }!!.second
    }

    private fun getDistance(n: Int, target: Int) = abs(n - target)

    private fun getDistancePartTwo(n: Int, target: Int): Int {
        val distance = abs(n - target)
        return (1..distance).sum()
    }

    private suspend fun parseInput(): List<Int> {
        return getInput(2021, 7)
            .split(",")
            .map { it.toInt() }
    }

    suspend fun partTwo(): Int {
        val crabs = parseInput()
        return findMinimumAlignment(crabs, ::getDistancePartTwo)
    }
}
