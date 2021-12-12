package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import java.lang.Math.abs

typealias HeightMap = List<List<Int>>

fun HeightMap.get(x: Int, y: Int) = this.getOrNull(x)?.getOrNull(y)

// Whoops, this has diagonals
//val adjacentCoords = (-1..1).flatMap { dy -> (-1..1).map { dx -> dx to dy } }
//    .filter { !(it.first == 0 && it.second == 0) }

val adjacentCoords = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)
//    .filter { !(it.first == 0 && it.second == 0) }

private fun HeightMap.print() {
    getCoords().forEach { (x, y) ->
        if (y == 0) println("")
        val point = get(x, y)
        val out = if (this.isLowPoint(x, y)) "*$point" else "$point"
        System.out.format("%3s", out)
    }
}

fun HeightMap.getAdjacentCoords(x: Int, y: Int): List<Pair<Int, Int>> {
    return adjacentCoords.map { (dx, dy) -> x + dx to y + dy }
}


fun HeightMap.getAdjacent(x: Int, y: Int): List<Int> {
    return adjacentCoords
        .map { (dx, dy) -> x + dx to y + dy }
        .mapNotNull { (newX, newY) -> this.get(newX, newY) }
}

fun HeightMap.getCoords(): List<Pair<Int, Int>> {
    return this.indices.flatMap { x -> this.first().indices.map { y -> x to y } }
}

fun HeightMap.isLowPoint(x: Int, y: Int): Boolean {
    val point = this.get(x, y)!!
    return this.getAdjacent(x, y).all { point < it }
}


class Day9 {

    suspend fun partOne(): Int {
        val heightMap = parseInput().also { it.print() }
        return heightMap.getCoords().filter { (x, y) -> heightMap.isLowPoint(x, y) }
            .sumOf { (x, y) -> heightMap.get(x, y)!! + 1 }
    }

    private suspend fun parseInput(): HeightMap {
        return getInput(2021, 9)
            .split("\n")
            .filter { it.isNotBlank() }
            .map { line ->
                line.trim()
                    .split("")
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
            }
    }

    suspend fun partTwo(): Int {
        val heightMap = parseInput()
        val lowPoints = heightMap.getCoords().filter { (x, y) -> heightMap.isLowPoint(x, y) }
        return lowPoints.map { it to getBasinSize(heightMap, listOf(it)) }
            .sortedByDescending { it.second }
            .also { println(it) }
            .map {it.second }
            .take(3)
            .reduce { acc, x -> acc * x }
    }

    private tailrec fun getBasinSize(
        heightMap: HeightMap,
        pointsToCheck: List<Pair<Int, Int>>,
        checked: Map<Pair<Int, Int>, Boolean> = emptyMap(),
        size: Int = 0
    ): Int {
        if (pointsToCheck.isEmpty()) {
            return size
        } else {
            val (x, y) = pointsToCheck.first()
            val newChecked = checked.plus((x to y) to true)
            val adjacent = heightMap.getAdjacentCoords(x, y).filter {
                val point = heightMap.get(it.first, it.second)
                newChecked[it] == null && point != null && point != 9
            }

            return getBasinSize(
                heightMap = heightMap,
                pointsToCheck = pointsToCheck.drop(1).filter{newChecked[it] == null} + adjacent,
                checked = newChecked,
                size = size + 1
            )
        }
    }

}
