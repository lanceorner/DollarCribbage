package net.orner.dollarcribbage.app;

import android.app.Activity;
import android.location.Criteria;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TooManyListenersException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        TextView serialView = (TextView) findViewById(R.id.serialnumber);
        String handstring = serialView.getText().toString();

        CribbageCalc cribbageCalc = new CribbageCalc();
        if (!cribbageCalc.isValidHand(handstring))
        {
            Toast.makeText(this, "Invalid Serial Number", Toast.LENGTH_LONG).show();
            return;
        }

        int score = cribbageCalc.CalcHand(handstring);

        TextView resultsView = (TextView) findViewById(R.id.textResults);

        String results = "Score " + score;


        resultsView.setText(results);

    }

}
