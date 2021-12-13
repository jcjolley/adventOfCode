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

    suspend fun partOne(): Int {
        var octopi = parseInput()
        var count = 0

        (1..100).forEach {
            val res = step(octopi)
            octopi = res.first
            count = res.second
        }

        return count
    }

    private fun increment(octopi: Octopi): Octopi = octopi.map { line -> line.map { it + 1 } }

    private fun coords(input: Octopi): List<Point> =
        input.indices.flatMap { x -> input.first().indices.map { y -> x to y } }

    private fun getAdjacentCoords(point: Point): List<Point> {
        return adjacentCoords.map { (dx, dy) -> point.first + dx to point.second + dy }
    }

    // TODO Fix this
    private tailrec fun flash(
        unflashedNines: List<Point>,
        octopi: Octopi,
        alreadyFlashed: Map<Point, Boolean> = emptyMap()
    ): Octopi {
        return if (unflashedNines.isEmpty())
            octopi.map { line ->
                line.map {
                    if (it > 9) 0 else it
                }
            }
        else {
            val adjacents = unflashedNines.flatMap { getAdjacentCoords(it) }
            val flashers = setOf(unflashedNines).plus(adjacents)

            return flash(
                getGtNine(octopi).filter { (x, y) -> !(alreadyFlashed[x to y] ?: false) },
                octopi.mapIndexed { x, line ->
                    line.mapIndexed { y, oct ->
                        if (flashers.contains(x to y)) oct + 1 else oct
                    }
                },
                alreadyFlashed.plus(unflashedNines.map { it to true })
            )
        }
    }

    private fun getGtNine(octopi: Octopi): List<Point> =
        coords(octopi).filter { (x, y) -> (octopi.get(x, y) ?: 0) > 9 }


    private fun step(input: Octopi): Pair<Octopi, Int>  {
        val result = input.let(::increment)
            .also { println("After Increment: "); it.print() }
            .let { flash(getGtNine(it), it) }
            .also { println("After flash: "); it.print() }
        return result to result.flatMap { line -> line.filter { it == 0 } }.count()
    }

    private suspend fun parseInput(): List<List<Int>> {
        return getInput(2021, 11)
            .split("\n")
            .filter { it.isNotBlank() }
            .map { line -> line.trim()
                .split("")
                .filter { it.isNotBlank() }
                .map { it.toInt() } }
    }

    suspend fun partTwo(): Long {
        return 0L
    }
}
