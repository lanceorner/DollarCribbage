package net.orner.dollarcribbage.app

/**
 * The main logic class to compute cribbage scores.
 */
class CribbageCalc {
    private val maxHandLength = 14

    fun isValidHand(handString: String): Boolean {
        val handLength = handString.length
        if (handLength > maxHandLength) {
            return false
        }

        // String should be "[0-9]*" regex

        // Is there a regex class?
        var isValid = true
        for (i in 0 until handLength) {
            if (handString[i] < '0' || handString[i] > '9') {
                isValid = false
            }
        }
        return isValid
    }

    fun calcHand(handString: String): Int {
        // Break handString into individual cards
        val hand = parseHand(handString)

        // Calc hand value
        val score: Int

        score = calcScore(hand)

        return score
    }

    private fun parseHand(handString: String): IntArray {
        val handLength = handString.length

        val hand = IntArray(handLength)

        for (i in 0 until handLength) {
            // There has got to be a more elegant way to do this.
            when (handString[i]) {
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
                else -> hand[i] = 0
            }
        }
        return hand
    }

    private fun calcScore(hand: IntArray): Int {
        var score = 0
        val handSize = hand.size

        // Score 15s

        // Code from Bryan Wolf, Mar 1, 2011
        // Number of ways that the cards can add up to each total 0 - 15
        val counts = intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (hand_index in 0 until handSize) {
            val faceValue = hand[hand_index]

            if (faceValue > 0) {

                // Ways to make various sums WITHOUT the current card are already in the table.
                // Add ways to make various sums WITH the current card.
                for (count_index in 15 downTo faceValue)
                    counts[count_index] += counts[count_index - faceValue]
            }
        }

        // Return then number of ways to make 15
        score += counts[15] * 2

        // Number of each rank
        val ranks = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        for (hand_index in 0 until handSize) {
            // Need a function to return an int for a face value
            var rankValue = hand[hand_index]
            if (rankValue == 0) {
                rankValue = 10  // Zero is a ten in this case
            }
            ranks[rankValue]++
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

