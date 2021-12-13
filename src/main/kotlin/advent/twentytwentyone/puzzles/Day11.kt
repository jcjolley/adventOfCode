package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import advent.utilities.rangeTo

private typealias Octopi = List<List<Int>>

private val adjacentCoords =
    (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }.filter { !(it.first == 0 && it.second == 0) }

private fun Octopi.print() {
    this.forEach { println(it) }
}

private fun Octopi.get(x: Int, y: Int) = this.getOrNull(x)?.getOrNull(y)

private fun Octopi.transform(fn: (x: Int, y: Int, octopus: Int) -> Int): Octopi {
    return this.mapIndexed { x1, line ->
        line.mapIndexed { y1, o ->
            fn(x1, y1, o)
        }
    }
}

private typealias Point = Pair<Int, Int>

class Day11 {
    val adjacentCoords =
        (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }.filter { !(it.first == 0 && it.second == 0) }

    suspend fun partOne(): Int {
        val octopi = parseInput()
        return countFlashes(octopi, iterations = 100)
    }

    private fun increment(octopi: Octopi): Octopi = octopi.transform { _, _, octopus -> octopus + 1 }

    private fun getAdjacentCoords(point: Point): List<Point> =
        adjacentCoords.map { (dx, dy) -> point.first + dx to point.second + dy }

    private fun rollGtNine(octopi: Octopi): Octopi =
        octopi.transform { _, _, octopus -> if (octopus > 9) 0 else octopus }

    private fun numFlashesLastStep(octopi: Octopi): Int = octopi.flatMap { l -> l.filter { it == 0 } }.size

    private tailrec fun flash(
        octopi: Octopi,
        haveFlashed: Set<Point> = emptySet(),
    ): Octopi {
        val readyToFlash = getGtNine(octopi).subtract(haveFlashed)

        return if (readyToFlash.isEmpty()) {
            octopi
        } else {
            val flasher = readyToFlash.first()
            val adjacent = getAdjacentCoords(flasher).toSet()

            flash(
                haveFlashed = haveFlashed.plus(flasher),
                octopi = octopi.transform { x, y, octopus ->
                    if (adjacent.contains(x to y) && !haveFlashed.contains(x to y)) octopus + 1 else octopus
                }
            )
        }
    }

    private fun getGtNine(octopi: Octopi): Set<Point> =
        octopi.indices.flatMap { x -> octopi.first().indices.map { y -> x to y } }
            .filter { (x, y) -> (octopi.get(x, y) ?: 0) > 9 }.toSet()

    private tailrec fun countFlashes(octopi: Octopi, flashes: Int = 0, iterations: Int = 100): Int {
        return if (iterations == 0) {
            flashes
        } else {
            val newOctopi = step(octopi)
            countFlashes(
                octopi = newOctopi,
                flashes = flashes + numFlashesLastStep(newOctopi),
                iterations = iterations - 1
            )
        }
    }

    private fun step(input: Octopi): Octopi = (::increment..::flash..::rollGtNine)(input)

    private suspend fun parseInput(): List<List<Int>> {
        return getInput(2021, 11)
            .map { line -> line.map { "$it".toInt() } }
    }

    suspend fun partTwo(): Int {
        val octopi = parseInput()
        return iterateUntilAllFlash(octopi)
    }

    private tailrec fun iterateUntilAllFlash(octopi: Octopi, numSteps: Int = 0): Int {
        val size = octopi.size * octopi.first().size
        return if (numFlashesLastStep(octopi) == size) {
            numSteps
        } else {
            iterateUntilAllFlash(step(octopi), numSteps + 1)
        }
    }
}
