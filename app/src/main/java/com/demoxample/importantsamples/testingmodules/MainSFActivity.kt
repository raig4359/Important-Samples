package com.demoxample.importantsamples.testingmodules

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.demoxample.importantsamples.R

private const val MASTER_SF_FRAGMENT = "MasterSFFragment"
private const val DETAIL_SF_FRAGMENT = "DetailSFFragment"

class MainSFActivity : AppCompatActivity(), MasterSFFragment.OnFragmentInteractionListener,
        DetailSFFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_sf)
        showMasterSFFragment()
    }

    private fun showMasterSFFragment() {
        openFragment(MasterSFFragment.newInstance("", ""), MASTER_SF_FRAGMENT)
    }

    private fun showDetailSFFragment() {
        openFragment(DetailSFFragment.newInstance("", ""), DETAIL_SF_FRAGMENT)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(tag, 0)
        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            manager.beginTransaction()
                    .replace(R.id.frame, fragment, tag)
                    .addToBackStack(tag)
                    .commit()
        }
    }


    override fun showDetailScreen() {
        showDetailSFFragment()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
