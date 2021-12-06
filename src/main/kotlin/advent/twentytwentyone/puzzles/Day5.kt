package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

typealias MutableBoard = List<MutableList<Int>>

private fun MutableBoard.print() {
    this.forEach {
        it.forEach { n -> System.out.format("%4d", n) }
        println("")
    }
    println("")
}

private fun MutableBoard.inc(x: Int, y: Int): MutableBoard {
    this[y][x] = this[y][x] + 1
    return this
}

data class LineSegment(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    val slope: Int? = if (x1 == x2) null else (y2 - y1) / (x2 - x1)
    fun isPointOnLineSegment(x: Int, y: Int): Boolean {
        val isOnLine = if (slope != null) (y - y1) == (slope * (x - x1)) else x == x1
        val isBetweenX = listOf(x1, x2).sorted().let { x in it[0]..it[1] }
        val isBetweenY = listOf(y1, y2).sorted().let { y in it[0]..it[1] }
        return isOnLine && isBetweenX && isBetweenY
    }

    override fun toString(): String {
        return "($x1,$y1)->($x2,$y2)"
    }
}

class Day5 {

    suspend fun partOne(): Int {
        val nonDiagonalLines = parseInput().filter { it.x1 == it.x2 || it.y1 == it.y2 }
        val board = buildBoard()
        walkBoard(nonDiagonalLines, board)
        return countIntersections(board)
    }

    private fun buildBoard(): MutableBoard {
        return (0 until 1000).map {
            IntArray(1000) { 0 }.toMutableList()
        }
    }

    // This is the least efficient way possible that you could do this.  Don't be like me.
    private fun walkBoard(lines: List<LineSegment>, board: MutableBoard): MutableBoard {
        (0 until 1000).forEach { y ->
            (0 until 1000).forEach { x ->
                lines.forEach { line ->
                    if (line.isPointOnLineSegment(x, y)) board.inc(x, y)
                }
            }
        }
        return board
    }

    private fun countIntersections(board: MutableBoard): Int {
        return board.sumOf { line -> line.count { it >= 2 } }
    }

    private suspend fun parseInput(): List<LineSegment> {
        return getInput(2021, 5)
            .split('\n')
            .filter { it.isNotBlank() }
            .map { line ->
                line.split("""( -> |,)""".toRegex())
                    .filter { it.isNotBlank() }
                    .map { it.trim().toInt() }.let {
                        LineSegment(it[0], it[1], it[2], it[3])
                    }
            }
    }

    suspend fun partTwo(): Int {
        val lines = parseInput()
        val board = buildBoard()
        walkBoard(lines, board)
        return countIntersections(board)
    }

}