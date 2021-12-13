package advent.utilities

operator fun <T, R, V> ((T) -> R).rangeTo(other: (R) -> V): ((T) -> V) {
    return {
        other(this(it))
    }
}

