package net.orner.dollarcribbage.app

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    fun onClick(view: View) {
        val serialView = findViewById<View>(R.id.serialnumber) as TextView
        val handString = serialView.text.toString()

        val cribbageCalc = CribbageCalc()
        if (!cribbageCalc.isValidHand(handString)) {
            Toast.makeText(this, "Invalid Serial Number", Toast.LENGTH_LONG).show()
            return
        }

        val score = cribbageCalc.calcHand(handString)

        val resultsView = findViewById<View>(R.id.textResults) as TextView

        val results = "Score $score"


        resultsView.text = results

    }

}
