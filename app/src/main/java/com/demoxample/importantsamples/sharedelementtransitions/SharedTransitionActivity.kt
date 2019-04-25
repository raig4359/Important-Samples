package com.demoxample.importantsamples.sharedelementtransitions

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.activity_shared_transition.*

private const val GRID_ONE_FRAG = "GridOneFragment"
private const val GRID_TWO_FRAG = "GridTwoFragment"

class SharedTransitionActivity : AppCompatActivity(), GridOneFragment.GridOneInteractionListener,
        GridTwoFragment.GridTwoInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_transition)
        toolbar.title = "Shared Transitions"
        showGridOneFragment()
    }

    private fun showGridOneFragment() {
        openFragment(GridOneFragment.newInstance("", ""), GRID_ONE_FRAG)
    }

    override fun showDetail(viewsShared: ArrayList<View>) {
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(GRID_TWO_FRAG, 0)
        if (!fragmentPopped && manager.findFragmentByTag(GRID_TWO_FRAG) == null) {
            manager.beginTransaction()
                    .setReorderingAllowed(true)  //enable reordering fragment transactions for postponed fragment transitions to work
                    .replace(R.id.frame, GridTwoFragment.newInstance("", ""), GRID_TWO_FRAG)
                    .addToBackStack(GRID_TWO_FRAG)
                    .apply {
                        var id = -1
                        for (view in viewsShared) {
                            addSharedElement(view, (++id).toString())
                        }
                    }
                    .commit()
        }
    }

    private fun showGridTwoFragment() {
        openFragment(GridTwoFragment.newInstance("", ""), GRID_TWO_FRAG)
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
}
