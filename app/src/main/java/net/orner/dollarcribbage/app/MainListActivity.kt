package net.orner.dollarcribbage.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import net.orner.dollarcribbage.app.R.id.fab
import net.orner.dollarcribbage.app.R.id.toolbar
import kotlinx.android.synthetic.main.activity_main_list.*
import kotlinx.android.synthetic.main.content_main_list.*


class MainListActivity : AppCompatActivity() {
    var scoreList: MutableList<Score> = mutableListOf(Score("03547254"), Score("223344567"), Score("72866543"))
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view -> startCreateNewActivity() }

        viewManager = LinearLayoutManager(this)
        viewAdapter = ScoreViewAdapater(scoreList)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_list_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter
            adapter = viewAdapter

            // Space between items requires modification of the decoration
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
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
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this@MainListActivity, "Settings menu", Toast.LENGTH_LONG).show()
                true
            }
//            R.id.menuWirelessInfo -> {
//                val intent = Intent(this, WirelessDataActivity::class.java)
//                startActivity(intent)
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun startCreateNewActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        true
    }
}
