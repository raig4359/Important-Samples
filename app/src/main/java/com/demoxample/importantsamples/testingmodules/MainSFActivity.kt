package com.demoxample.importantsamples.testingmodules

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.layout_record.view.*

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

    private fun showDetailSFFragment(itemView: View) {
        openFragment(DetailSFFragment.newInstance("", ""), DETAIL_SF_FRAGMENT, itemView)
    }

    private fun openFragment(fragment: Fragment, tag: String, itemView: View? = null) {
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(tag, 0)
        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            manager.beginTransaction()
                    .replace(R.id.frame, fragment, tag)
                    .apply {
                        if (itemView != null) {
                            addSharedElement(itemView.iv_profile, getString(R.string.profile_image))
                            addSharedElement(itemView.tv_title, getString(R.string.title))
                        }
                    }
                    .addToBackStack(tag)
                    .commit()
        }
    }


    override fun showDetailScreen(itemView: View) {
        showDetailSFFragment(itemView)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
