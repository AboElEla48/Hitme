package eg.foureg.hitme.rand

import java.util.*

private var random = Random()

fun generateRandom() : Int {
    return random.nextInt(100)
}