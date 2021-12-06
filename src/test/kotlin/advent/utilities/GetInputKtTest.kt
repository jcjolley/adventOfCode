package advent.utilities

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class GetInputKtTest {
    @Test
    fun `Get Input Works`() = runBlocking {
        val input = getInput(2021, 1)

        println(input)
        assertTrue(input.isNotEmpty())
        assertTrue(File("files/input/2021-1.txt").exists())
    }
}