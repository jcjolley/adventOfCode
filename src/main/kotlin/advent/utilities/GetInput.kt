package advent.utilities

import io.github.rybalkinsd.kohttp.dsl.async.httpGetAsync
import io.github.rybalkinsd.kohttp.ext.asString
import io.github.rybalkinsd.kohttp.ext.url
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.io.File

typealias Board = List<List<Int>>

val inputScope = CoroutineScope(Dispatchers.IO)

tailrec suspend fun getInput(year: Int, day: Int): List<String> {
    val file = File(getInputFilename(year, day))
    return if (file.exists()) {
        file.readLines()
            .map { it.trim() }
            .filter { it.isNotBlank() }
    } else {
        inputScope.async {
            fetchInput(year, day).also {
                writeFile(year, day, it)
            }
        }.await()
        getInput(year, day)
    }
}

private suspend fun fetchInput(year: Int, day: Int): String {
    val cookie = readFile(getCookieFilename(year)) ?: throw IllegalStateException("Cookie file missing")
    val response = httpGetAsync {
        url("https://adventofcode.com/$year/day/$day/input")
        header {
            "cookie" to cookie
        }
    }.await()

    return if (response.code() == 200) {
        response.asString()?.trim() ?: throw IllegalStateException("No response body from advent of code")
    } else {
        throw IllegalStateException("Non 200 response code: ${response.code()}: ${response.asString()}")
    }
}

private fun readFile(filename: String): String? {
    val file = File(filename)
    return if (file.exists()) file.readText().trim() else null
}

private fun writeFile(year: Int, day: Int, input: String) {
    File(getInputFilename(year, day)).writeText(input)
}

private fun getInputFilename(year: Int, day: Int): String = "files/input/$year-$day.txt"
private fun getCookieFilename(year: Int): String = "files/cookies/$year-cookie.txt"
