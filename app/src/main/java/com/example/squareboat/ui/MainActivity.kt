package com.example.squareboat.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.squareboat.R
import com.example.squareboat.ui.dashboard.DashBoardFragment
import com.example.squareboat.ui.search.IconSearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {


    private lateinit var searchView: SearchView
    private var lastQuery = ""
    private lateinit var dashBoardFragment: DashBoardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        openDashBoardFragment()

    }

    private fun openDashBoardFragment() {
        dashBoardFragment = DashBoardFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, dashBoardFragment)
            .commit()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        searchView = menu.findItem(R.id.searchView).actionView as SearchView
        searchView.queryHint = getString(R.string.search_icons_here)
        searchView.setOnQueryTextListener(this)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        query?.let {
            if (query.isNotBlank() && query != lastQuery)
                openSearchFragment(query)
        }
        return false
    }

    private fun openSearchFragment(query: String) {
        lastQuery = query

        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment is IconSearchFragment) {
            currentFragment.updateQuery(query)
        } else {
            val fragment = IconSearchFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            bundle.putString(IconSearchFragment.QUERY, query)
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .hide(dashBoardFragment)
                .addToBackStack(TAG)
                .commit()
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is IconSearchFragment) {
            if (!searchView.isIconified) {
                searchView.isIconified = true
                searchView.onActionViewCollapsed()
            }
            supportFragmentManager.popBackStackImmediate()
            supportFragmentManager.beginTransaction().show(dashBoardFragment).commit()
        } else super.onBackPressed()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
