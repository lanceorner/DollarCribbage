package net.orner.dollarcribbage.app;

/**
 * Created by lance on 3/1/14.
 */

public class CribbageCalc {

    public static int MAX_HAND_LENGTH = 14;

    public boolean isValidHand (String handstring) {
        int handlength = handstring.length();
        if (handlength > MAX_HAND_LENGTH) {
            return false;
        }

        // String should be "[0-9]*" regex

        // Is there a regex class?
        boolean isValid = true;
        for (int i = 0; i < handlength; i++) {
            if (handstring.charAt(i) < '0' || handstring.charAt(i) > '9') {
                isValid = false;
            }
        }
        return isValid;
    }

    public int CalcHand (String handstring) {
        // Break handstring into individual cards
        int[] hand = ParseHand(handstring);

        // Calc hand value
        int score;

        score = CalcScore(hand);

        return score;
    }

    private int[] ParseHand (String handstring)
    {
        int handlength = handstring.length();
        boolean isError = false;


        int[] hand = new int[handlength];

        for (int i = 0; i < handlength; i++)
        {
            // There has got to be a more elegant way to do this.
            switch (handstring.charAt(i)) {
                case '0': hand[i] = 0;
                    break;
                case '1': hand[i] = 1;
                    break;
                case '2': hand[i] = 2;
                    break;
                case '3': hand[i] = 3;
                    break;
                case '4': hand[i] = 4;
                    break;
                case '5': hand[i] = 5;
                    break;
                case '6': hand[i] = 6;
                    break;
                case '7': hand[i] = 7;
                    break;
                case '8': hand[i] = 8;
                    break;
                case '9': hand[i] = 9;
                    break;
                default:  isError = true;
            }
        }
        return hand;
    }

    private int CalcScore(int[] hand)
    {
        int score = 0;
        int handsize = hand.length;

        // Score 15s

        // Code from Bryan Wolf, Mar 1, 2011
        // Number of ways that the cards can add up to each total 0 - 15
        int counts[] = new int[]{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        for (int hand_index = 0; hand_index < handsize; ++hand_index)
        {
            int face_value = hand[hand_index];

            if (face_value > 0){

                // Ways to make various sums WITHOUT the current card are already in the table.
                // Add ways to make various sums WITH the current card.
                for (int count_index = 15; count_index >= face_value; --count_index)
                    counts[count_index] += counts[count_index - face_value];
            }
        }

        // Return then number of ways to make 15
        score += counts[15] * 2;

        // Number of each rank
        int[] ranks = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        for (int hand_index = 0; hand_index < handsize; ++hand_index)
        {
            // Need a function to return an int for a face value
            int rank_value = hand[hand_index];
            ranks[rank_value]++;
        }

        // Search for pairs
        for (int rank_index = 1; rank_index <= 13; ++rank_index)
        {
            // This can be done better
            // score = C(rank[rank_index], 2) * 2
            // This could be done with a lookup table, or even a combination function,
            //  but this will work for now.  Will not work for over 14 pairs.
            switch (ranks[rank_index]) {
                case 2: score += 2;
                    break;
                case 3: score += 6;
                    break;
                case 4: score += 12;
                    break;
                case 5: score += 20;
                    break;
                case 6: score += 30;
                    break;
                case 7: score += 42;
                    break;
                case 8: score += 56;
                    break;
                case 9: score += 72;
                    break;
                case 10: score += 90;
                    break;
                case 11: score += 110;
                    break;
                case 12: score += 132;
                    break;
                case 13: score += 156;
                    break;
                case 14: score += 182;
                    break;
                default:
                    score += 0;
            }

        }

        // Score runs
        for (int runStart = 1; runStart <= 13; ++runStart)
        {
            if (ranks[runStart] > 0)
            {
                int runStop = runStart + 1 ;
                for (; runStop <= 13 && ranks[runStop] > 0; ++runStop)
                {
                    // Blank
                }
                if (runStop <= 13)
                {
                    int runScore = runStop - runStart;
                    if (runScore >= 3)
                    {
                        for (int i = runStart; i < runStop; ++i)
                        {
                            runScore *= ranks[i];
                        }
                        score += runScore;
                    }
                    runStart = runStop + 1;
                }
            }
        }
        return score;
    }
    

}

