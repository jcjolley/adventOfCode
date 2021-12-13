package advent.twentytwentyone.puzzles

import advent.utilities.getInput

class Day3 {

    suspend fun partOne(): Int {
        val binaryNumbers = parseInput()

        val gamma = getGamma(binaryNumbers)
        val epsilon = invertBinaryString(gamma)

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun getGamma(binaryNumbers: List<String>): String {
        val length = binaryNumbers.first().length
        return (0 until length).map { i ->
            val avg = binaryNumbers.map { ns -> ns[i].toString().toInt() }.average()
            if (avg > 0.5) '1' else '0'
        }
            .joinToString("")
    }

    fun invertBinaryString(binString: String) = binString
        .map { if (it == '1') '0' else '1' }
        .joinToString("")

    suspend fun parseInput(): List<String> {
        return getInput(2021, 3)
    }

    suspend fun partTwo(): Int {
        val binaryNumbers = parseInput()
        val o2Rating = whittleDown(binaryNumbers, 0) { if (it >= 0.5) '1' else '0' }
        val co2Rating = whittleDown(binaryNumbers, 0) { if (it >= 0.5) '0' else '1' }
        return o2Rating.toInt(2) * co2Rating.toInt(2)
    }

    tailrec fun whittleDown(numbers: List<String>, index: Int = 0, searchKyFn: (x: Double) -> Char): String {
        return if (numbers.size == 1) {
            numbers.first()
        } else {
            val avg = numbers.map { it[index].toString().toInt() }.average()
            val searchKey = searchKyFn(avg)
            whittleDown(numbers.filter { it[index] == searchKey }, index + 1, searchKyFn)
        }
    }
}
