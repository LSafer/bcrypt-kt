package net.lsafer.bcrypt.test

import net.lsafer.bcrypt.BCryptKt
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class MainTest {
    @Test
    fun `hash then verify a random strings`() {
        val randomChars = ('0'..'9') + ('A'..'Z') + ('a'..'z')
        val randomSequence = {
            generateSequence {
                (0..9).map { randomChars.random() }.joinToString()
            }
        }

        val randomString = randomSequence().first()
        val randomString2 = randomSequence().first { it != randomString }

        val hash = BCryptKt.hash(randomString)

        assertTrue(BCryptKt.verify(randomString, hash), "Correct hash not verified")
        assertFalse(BCryptKt.verify(randomString2, hash), "Incorrect hash verified")
    }
}
