fun main() {

    println("easy (using lists):")
    val (ezl1, ezl2) = mapOf(3 to "Fizz", 5 to "Buzz").map { (i, str) ->
        MutableList(i - 1) { "" }.apply {
            add(
                0,
                str
            )
        }
    }
    val ezlOut =
        buildString { (1..100).forEach { index -> append("$index ${ezl1[index % ezl1.size]}${ezl2[index % ezl2.size]}\n") } }
    println(ezlOut)

    println("\nmedium (using lists):")
    class Ticker(var countTo: Int, val strToPrint: String) {
        private var counter = 0
        fun tick() = if (++counter % countTo == 0) {
            strToPrint
        } else ""
    }

    val ticks = listOf(Ticker(3, "Fizz"), Ticker(5, "Buzz"), Ticker(7, "Rizz"), Ticker(11, "Jazz"))
    val mdlOut = buildString {
        (1..100).forEach { index ->
            append("$index ${ticks.joinToString("") { it.tick() } + if (120 % index == 0) "Dizz" else ""}\n")
        }
    }
    println(mdlOut)

    println("\nhard (using lists):")
    val primes = buildList { (2..103).forEach { i -> if ((2 until i).none { j -> i % j == 0 }) add(i) } }
    val (three, five, seven, eleven) = listOf(3, 5, 7, 11).map { i -> (i..100 step i).map { it } }
    val sevenEleven = (seven + eleven).distinct().sorted()
    val divvo = (1..100).filter { 120 % it == 0 }
    val truePrimes = primes.windowed(2, 1).filter { (theNum, nextPrime) ->
        val firstBigger = sevenEleven.firstOrNull { it > theNum }
        nextPrime >= (firstBigger ?: 100)
    }.map { it.first() }

    fun List<Int>.plunder(num: Int, str: String) = if (contains(num)) str else ""

    val hdlOut = buildString {
        (1..100).forEach {
            append(
                "$it ${three.plunder(it, "Fizz")}${five.plunder(it, "Buzz")}${seven.plunder(it, "Rizz")}" +
                        "${eleven.plunder(it, "Jazz")}${divvo.plunder(it, "Dizz")}${
                            truePrimes.plunder(
                                it,
                                "Prizz"
                            )
                        }\n"
            )
        }
    }

    println("\neasy (one loop):")
    val ezoOut = buildString {
        (1..100).forEach { i ->
            append("$i ")
            if (i % 3 == 0) append("Fizz")
            if (i % 5 == 0) append("Buzz")
            append("\n")
        }
    }
    println(ezoOut)

//    println("Matching output both times: ${ezlOut == ezoOut}")

    println("\nmedium (one loop):")
    val mdoOut = buildString {
        (1..100).forEach { i ->
            append("$i ")
            mapOf(
                3 to "Fizz",
                5 to "Buzz",
                7 to "Rizz",
                11 to "Jazz"
            ).forEach { if (i % it.key == 0) append(it.value) }
            if (120 % i == 0) append("Dizz")
            append("\n")
        }
    }
    println(mdoOut)

//    println("Matching output both times: ${mdlOut == mdoOut}")

    println("\nhard (one loop):")
    val hdoOut = buildString {
        (1..100).forEach { i ->
            append("$i ")
            mapOf(
                3 to "Fi",
                5 to "Bu",
                7 to "Ri",
                11 to "Ja"
            ).forEach { if (i % it.key == 0) append(it.value + "zz") }
            if (120 % i == 0) append("Dizz")
            if ((2 until i).none { j -> i % j == 0 }) run {
                ((i + 1)..100).forEach { k ->
                    if (k % 7 == 0 || k % 11 == 0) {
                        append("Prizz")
                        return@run
                    }
                    if ((2 until k).none { l -> k % l == 0 }) return@run
                }
            }
            append("\n")
        }
    }
    println(hdoOut)

//    println("Matching output both times: ${hdlOut == hdoOut}")
}