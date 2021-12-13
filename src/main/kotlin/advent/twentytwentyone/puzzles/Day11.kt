package advent.twentytwentyone.puzzles

import advent.utilities.getInput

private typealias Octopi = List<List<Int>>

private val adjacentCoords =
    (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }.filter { !(it.first == 0 && it.second == 0) }

private fun Octopi.print() {
    this.forEach { println(it) }
}

private fun Octopi.get(x: Int, y: Int) = this.getOrNull(x)?.getOrNull(y)

private typealias Point = Pair<Int, Int>

class Day11 {
    val adjacentCoords =
        (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }.filter { !(it.first == 0 && it.second == 0) }


    suspend fun partOne(): Long {
        return 0L
    }

    private fun increment(octopi: Octopi): Octopi = octopi.map { line -> line.map { it + 1 } }

    private fun coords(input: Octopi): List<Point> =
        input.indices.flatMap { x -> input.first().indices.map { y -> x to y } }

    private fun getAdjacentCoords(point: Point): List<Point> {
        return adjacentCoords.map { (dx, dy) -> point.first + dx to point.second + dy }
    }

    private tailrec fun flash(octopi: Octopi, toFlash: Map<Point, Boolean> = emptyMap()): Octopi {
        return if (octopi.flatten().none { it >= 9 })
            octopi
        else {
            val points = coords(octopi)
            val startingNines = points.filter { (x, y) -> (octopi.get(x, y) ?: 0) >= 9 }
            val adjacents = startingNines.flatMap { getAdjacentCoords(it) }

            val flashers = setOf(startingNines).plus(adjacents)
            val newOctopi = octopi.mapIndexed { x, line -> line.mapIndexed { y, oct ->
                if (flashers.contains(x to y)) oct + 1 else oct
            }}
            return flash(newOctopi)
        }
    }


    fun step(input: Octopi): Octopi {
        increment(input)
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
