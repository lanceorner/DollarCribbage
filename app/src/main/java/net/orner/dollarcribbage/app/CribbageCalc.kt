package net.orner.dollarcribbage.app

/**
 * Created by lance on 3/1/14.
 */

class CribbageCalc {

    val MAX_HAND_LENGTH = 14

    fun isValidHand(handstring: String): Boolean {
        val handlength = handstring.length
        if (handlength > MAX_HAND_LENGTH) {
            return false
        }

        // String should be "[0-9]*" regex

        // Is there a regex class?
        var isValid = true
        for (i in 0 until handlength) {
            if (handstring[i] < '0' || handstring[i] > '9') {
                isValid = false
            }
        }
        return isValid
    }

    fun calcHand(handstring: String): Int {
        // Break handstring into individual cards
        val hand = parseHand(handstring)

        // Calc hand value
        val score: Int

        score = calcScore(hand)

        return score
    }

    private fun parseHand(handstring: String): IntArray {
        val handlength = handstring.length
        var isError = false


        val hand = IntArray(handlength)

        for (i in 0 until handlength) {
            // There has got to be a more elegant way to do this.
            when (handstring[i]) {
                '0' -> hand[i] = 10
                '1' -> hand[i] = 1
                '2' -> hand[i] = 2
                '3' -> hand[i] = 3
                '4' -> hand[i] = 4
                '5' -> hand[i] = 5
                '6' -> hand[i] = 6
                '7' -> hand[i] = 7
                '8' -> hand[i] = 8
                '9' -> hand[i] = 9
                else -> isError = true
            }
        }
        return hand
    }

    private fun calcScore(hand: IntArray): Int {
        var score = 0
        val handsize = hand.size

        // Score 15s

        // Code from Bryan Wolf, Mar 1, 2011
        // Number of ways that the cards can add up to each total 0 - 15
        val counts = intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (hand_index in 0 until handsize) {
            val face_value = hand[hand_index]

            if (face_value > 0) {

                // Ways to make various sums WITHOUT the current card are already in the table.
                // Add ways to make various sums WITH the current card.
                for (count_index in 15 downTo face_value)
                    counts[count_index] += counts[count_index - face_value]
            }
        }

        // Return then number of ways to make 15
        score += counts[15] * 2

        // Number of each rank
        val ranks = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        for (hand_index in 0 until handsize) {
            // Need a function to return an int for a face value
            var rank_value = hand[hand_index]
            if (rank_value == 0) {
                rank_value = 10  // Zero is a ten in this case
            }
            ranks[rank_value]++
        }

        // Search for pairs
        for (rank_index in 1..13) {
            // 2 cards = 2 points
            // 3 cards = 6 points
            // 4 cards = 12 points, ect.
            score += ranks[rank_index] * (ranks[rank_index] - 1)
        }

        // Score runs
        var runStart = 1
        while (runStart <= 13) {
            if (ranks[runStart] > 0) {
                var runStop = runStart + 1
                while (runStop <= 13 && ranks[runStop] > 0) {
                    ++runStop
                    // Blank
                }
                if (runStop <= 13) {
                    var runScore = runStop - runStart
                    if (runScore >= 3) {
                        for (i in runStart until runStop) {
                            runScore *= ranks[i]
                        }
                        score += runScore
                    }
                    runStart = runStop
                }
            }
            ++runStart
        }
        return score
    }

}

