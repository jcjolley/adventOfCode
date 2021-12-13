package advent.twentytwentyone.puzzles

import advent.utilities.getInput

class Ocean(initialFish: List<Int>) {
    var ocean = mutableMapOf<Int, Long>()

    val newSpawnHoldingTank = mutableListOf<Int>()

    init {
        initialFish.forEach {
            addFish(ocean, it)
        }
    }

    private fun addFish(map: MutableMap<Int, Long>, day: Int, value: Long = 1) {
        val temp = map[day] ?: 0
        map[day] = temp + value
    }

    fun advanceDay() {
        val newOcean = mutableMapOf<Int, Long>()

        (8 downTo 0).forEach {
            if (it == 0) {
                val numFish = ocean[0] ?: 0
                addFish(newOcean, 6, numFish)
                addFish(newOcean, 8, numFish)
            } else {
                newOcean[it - 1] = ocean[it] ?: 0
            }
        }

        ocean = newOcean
    }

    fun numFish(): Long {
        return (0..8).sumOf { ocean[it] ?: 0L }
    }
}

class Day6 {

    suspend fun partOne(): Long {
        val fishes = parseInput()
        val ocean = Ocean(fishes)
        advanceTime(80, ocean)
        return ocean.numFish()
    }

    fun advanceTime(days: Int, ocean: Ocean) {
        (0 until days).forEach {
            ocean.advanceDay()
        }
    }

    private suspend fun parseInput(): List<Int> {
        return getInput(2021, 6)
            .first()
            .split(",")
            .map { it.toInt() }
            .also { println("Input: $it") }
    }

    suspend fun partTwo(): Long {
        val fishes = parseInput()
        val ocean = Ocean(fishes)
        advanceTime(256, ocean)
        return ocean.numFish()
    }
}
