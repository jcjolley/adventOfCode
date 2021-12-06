package advent.twentytwentyone.puzzles

import advent.utilities.getInput

enum class Direction(val direction: String) {
    forward("forward"),
    down("down"),
    up("up")
}

data class SubmarineCommand(val direction: Direction, val distance: Int)
data class Position(val x: Int, val y: Int, val aim: Int = 0)

class Day2 {
    suspend fun partOne(): Int {
        val commands = parseInput()
        val position = Position(0, 0)

        return commands.fold(position, ::applyPartOneCommand)
            .let { (x, y) -> x * y }
    }

    suspend fun partTwo(): Int {
        val commands = parseInput()
        val position = Position(0, 0, 0)

        return commands.fold(position, ::applyPartTwoCommand)
            .let { (x, y) -> x * y }
    }

    private fun applyPartOneCommand(position: Position, command: SubmarineCommand): Position {
        val (x, y) = position
        val (direction, distance) = command
        return when (direction) {
            Direction.forward -> Position(x + distance, y)
            Direction.up -> Position(x, y - distance)
            Direction.down -> Position(x, y + distance)
        }
    }

    private fun applyPartTwoCommand(position: Position, command: SubmarineCommand): Position {
        val (x, y, aim) = position
        val (direction, distance) = command
        return when (direction) {
            Direction.forward -> Position(x + distance, y + aim * distance, aim)
            Direction.up -> Position(x, y, aim - distance)
            Direction.down -> Position(x, y, aim + distance)
        }
    }

    private fun parseCommand(instruction: String): SubmarineCommand {
        val (direction, distance) = instruction.split(' ')
        return SubmarineCommand(Direction.valueOf(direction), distance.toInt())
    }

    private suspend fun parseInput(): List<SubmarineCommand> {
        return getInput(2021, 2)
            .split('\n')
            .filter { it.isNotBlank() }
            .map(::parseCommand)
    }
}