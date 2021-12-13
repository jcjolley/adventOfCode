package advent.twentytwentyone.puzzles

import advent.utilities.getInput

data class Cave(val name: String, val edges: MutableList<Cave>) {
    val isBig = name.first().isUpperCase()
}

class Day12 {

    suspend fun partOne(): Int {
        val caves = parseInput()
        val paths = caves.flatMap {
            val set = LinkedHashSet<Cave>()
            set.add(it)
            walkEdges(it, listOf(set))
        }
        return paths.size
    }

    fun walkEdges(cave: Cave, paths: List<LinkedHashSet<Cave>>): List<LinkedHashSet<Cave>> {
        if (cave.name == "end") {
            return paths
        }

        return cave.edges.flatMap {
            walkEdges(
                cave = it,
                paths = paths.map { path ->
                    if (it.isBig || !path.contains(it)) {
                        path.add(it)
                        path
                    } else null
                }.filterNotNull()
            )
        }
    }

    private suspend fun parseInput(): List<Cave> {
        val nodes = getInput(2021, 12)
            .groupBy { it.split("-").first() }
            .map { (source, connections) ->
                val destinations = connections.map {
                    it.split('-')[1]
                }
                Cave(source, mutableListOf()) to destinations
            }
            .plus(Cave("end", mutableListOf()) to emptyList())
            .associateBy { it.first.name }

        nodes.forEach { (_, value) ->
            val (node, destinations) = value
            println("Node: $node")
            println("Destinations: $destinations")
            println("Nodes:")
            nodes.forEach {
                println(it)
            }
            destinations.forEach {
                println("dest: $it")
                node.edges.add(nodes[it]!!.first)
            }
        }

        return nodes.map { it.value.first }
    }

    suspend fun partTwo(): Int {
        val octopi = parseInput()
        return 0
    }
}
