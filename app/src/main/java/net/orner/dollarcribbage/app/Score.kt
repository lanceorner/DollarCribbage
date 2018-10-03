package net.orner.dollarcribbage.app

data class Score (val serialNumber: String) {
    private var calc = CribbageCalc();
    val score  = calc.calcHand(serialNumber);
    override fun toString(): String {
        return "$serialNumber = $score"
    }
}