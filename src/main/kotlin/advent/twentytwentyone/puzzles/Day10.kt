package advent.twentytwentyone.puzzles

import advent.utilities.getInput
import java.util.concurrent.ArrayBlockingQueue

class Day10 {

    suspend fun partOne(): Int {
        val input = parseInput()
        return input.mapNotNull { isCorrupted(it) }
            .sumOf(::scoreChar)
    }

    fun scoreChar(c: Char): Int = when (c) {
        ')' -> 3
        ']' -> 57
        '}' -> 1197
        '>' -> 25137
        else -> throw IllegalStateException("Cannot score $c")
    }

    fun isCorrupted(str: String): Char? {
        val stack = mutableListOf<Char>()
        return str.mapNotNull { c ->
            // println(" Char is $c ")
            // println(" Stack is $stack ")
            when {
                isOpen(c) -> null.also { stack.add(c) }
                getMatching(stack.last()) != c -> c
                else -> null.also { stack.removeLast() }
            }
        }.firstOrNull()
    }

    fun isOpen(c: Char) = when (c) {
        '(', '[', '{', '<' -> true
        else -> false
    }

    fun getMatching(c: Char): Char = when (c) {
        '(' -> ')'
        '[' -> ']'
        '{' -> '}'
        '<' -> ')'
        else -> throw IllegalStateException("Not an opening character")
    }

    private suspend fun parseInput(): List<String> {
        return getInput(2021, 10)
            .split("\n")
            .filter { it.isNotBlank() }
    }

    suspend fun partTwo(): Int {
        val input = parseInput()
        return 0
    }
}
