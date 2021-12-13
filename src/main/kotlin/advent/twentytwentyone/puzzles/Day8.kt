package advent.twentytwentyone.puzzles

import advent.utilities.getInput

typealias SignalSet = Pair<List<String>, List<String>>

class Day8 {

    val segmentCountToDigit = mapOf<Int, List<Int>>(
        6 to listOf(0, 6, 9),
        2 to listOf(1),
        3 to listOf(7),
        5 to listOf(2, 3, 5),
        4 to listOf(4),
        7 to listOf(8)
    )

    suspend fun partOne(): Int {
        val signalSets = parseInput()
        return countEasyOnes(signalSets)
    }

    fun countEasyOnes(signalSets: List<SignalSet>): Int {
        return signalSets.map { (_, signals) ->
            signals.map { signal ->
                when (signal.length) {
                    2, 3, 4, 7 -> 1
                    else -> 0
                }
            }.sum()
        }.sum()
    }

    private suspend fun parseInput(): List<SignalSet> {
        return getInput(2021, 8)
            .map {
                val (signalPatterns, signals) = it.split('|')
                parseSignals(signalPatterns) to parseSignals(signals)
            }
    }

    private fun parseSignals(signals: String): List<String> {
        return signals.split(' ')
            .map {
                it.split("")
                    .sorted()
                    .joinToString("")
            }
    }

    private fun interpretSignals(signals: SignalSet) {
        /*****************
          1111
         2    3
         2    3
          4444
         5    6
         5    6
          7777
         ****************/
        val sidesMap = mapOf<Int, MutableList<String>>(
            1 to mutableListOf(),
            2 to mutableListOf(),
            3 to mutableListOf(),
            4 to mutableListOf(),
            5 to mutableListOf(),
            6 to mutableListOf(),
            7 to mutableListOf()
        )

        val finalMap = mutableMapOf<String, Short>()

        val (all, lit) = signals
        val one = all.first { it.length == 2 }.also {
            it.split("").forEach { char ->
                sidesMap[3]!!.add(char)
                sidesMap[6]!!.add(char)
            }
        }

        val seven = all.first { it.length == 3 }
        val sideOne = seven.split("").subtract(one.split("")).first()
        finalMap[sideOne] = 1

        val four = all.first { it.length == 4 }.also {
            it.split("")
                .subtract(one.split(""))
                .forEach { char ->
                    sidesMap[2]!!.add(it)
                    sidesMap[4]!!.add(it)
                }
        }
    }

    private fun findSideFour(all: List<String>) {
        val sixSignals = all.filter { it.length == 6}
        val eight = all.first { it.length == 7 }
    }

    suspend fun partTwo(): Int {
        val signalSets = parseInput()
        return 0
    }
}
