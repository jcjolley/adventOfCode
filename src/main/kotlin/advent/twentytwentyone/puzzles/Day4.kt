package advent.twentytwentyone.puzzles

import advent.utilities.Board
import advent.utilities.getInput

private fun Board.print(searchSet: Set<Int>) {
    this.forEach {
        it.forEach { n ->
            if (searchSet.contains(n)) {
                System.out.format("%4s", "*$n")
            } else {
                System.out.format("%4d", n)
            }
        }
        println("")
    }
    println("")
}

class Day4 {

    suspend fun partOne(): Int {
        val (seq, boards) = parseInput()
        return findWinningBoard(seq, boards, 1)
    }

    private tailrec fun findWinningBoard(seq: List<Int>, boards: List<Board>, iteration: Int): Int {
        val searchList = seq.take(iteration)
        val winner = boards.firstOrNull { isWinningBoard(it, searchList.toSet()) }
        return if (winner != null) {
            calculateScore(winner, searchList)
        } else {
            findWinningBoard(seq, boards, iteration + 1)
        }
    }

    private fun calculateScore(board: Board, searchList: List<Int>): Int {
        val searchSet = searchList.toSet()
        return searchList.last() * board.map { line -> line.filter { !searchSet.contains(it) } }.sumOf { it.sum() }
    }

    private suspend fun parseInput(): Pair<List<Int>, List<Board>> {
        val input = getInput(2021, 4)

        val seq = input.first()
            .split(",")
            .map { it.toInt() }

        val boards = input.drop(1).map { board ->
            board.split('\n').map { line ->
                line.split("""\s+""".toRegex())
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }
            }.filter { it.isNotEmpty() }
        }
        return seq to boards
    }

    private fun searchColumns(board: Board, searchSet: Set<Int>): Boolean {
        val size = board.first().size
        return (0 until size).any { index ->
            val col = board.map { it[index] }
            col.all { searchSet.contains(it) }
        }
    }

    private fun searchRows(board: Board, searchSet: Set<Int>): Boolean {
        return board.any { line -> line.all { n -> searchSet.contains(n) } }
    }

    private fun isWinningBoard(board: Board, searchSet: Set<Int>): Boolean {
        return searchRows(board, searchSet) || searchColumns(board, searchSet)
    }

    suspend fun partTwo(): Int {
        val (seq, boards) = parseInput()
        return findLosingBoard(seq, boards, 1)
    }

    private tailrec fun findLosingBoard(seq: List<Int>, boards: List<Board>, iteration: Int): Int {
        val searchList = seq.take(iteration)
        val searchSet = searchList.toSet()
        val winner = boards.firstOrNull { isWinningBoard(it, searchSet) }
        return if (boards.size == 1 && winner != null) {
            calculateScore(winner, searchList)
        } else {
           findLosingBoard(seq, boards.filter { !isWinningBoard(it, searchSet) }, iteration + 1)
        }
    }
}
