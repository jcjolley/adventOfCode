package advent.twentytwentyone.puzzles

import advent.utilities.getInput

class Day10 {

    suspend fun partOne(): Int {
        val input = parseInput()
        return input.mapNotNull { getCorruptedInfo(it).first }
            .sumOf(::scoreChar)
    }

    fun scoreChar(c: Char): Int = when (c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> throw IllegalStateException("Cannot score $c")
    }

    fun getCorruptedInfo(str: String): Pair<Char?, List<Char>> {
        val stack = mutableListOf<Char>()
        return str.mapNotNull { c ->
            when {
                isOpen(c) -> null.also { stack.add(c) }
                getMatching(stack.last()) != c -> c
                else -> null.also { stack.removeLast() }
            }
        }.firstOrNull() to stack
    }

    fun isOpen(c: Char) = when (c) {
        '(', '[', '{', '<' -> true
        else -> false
    }

    fun getMatching(c: Char): Char = when (c) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> '>'
        else -> throw IllegalStateException("Not an opening character")
    }

    private suspend fun parseInput(): List<String> {
        return getInput(2021, 10)
    }

    fun completeString(stack: List<Char>): List<Char> =
        stack.reversed().map(::getMatching)

    fun scoreCompleted(completed: List<Char>): Long =
        completed.fold(0L) { acc, c ->
            5 * acc + when (c) {
                ')' -> 1
                ']' -> 2
                '}' -> 3
                '>' -> 4
                else -> throw IllegalStateException("Invalid character during scoring")
            }
        }

    suspend fun partTwo(): Long {
        val input = parseInput()
        val results = input.map { getCorruptedInfo(it) }
            .filter { it.first == null }
            .map { completeString(it.second).let(::scoreCompleted) }
            .sorted()
            .also { println("Results: $it")}

        return results[results.size / 2]
    }
}
