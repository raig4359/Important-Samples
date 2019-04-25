package com.demoxample.importantsamples.sharedelementtransitions

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.fragment_grid_one.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GridOneFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: GridOneInteractionListener? = null
    val viewsShared = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grid_one, container, false)
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Summary"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_pictures.layoutManager = GridLayoutManager(activity!!, 4)
        rv_pictures.adapter = SmallPicAdapter(viewsShared)
        tv_more.setOnClickListener {
            listener?.showDetail(viewsShared)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GridOneInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement GridOneInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface GridOneInteractionListener {
        fun showDetail(viewsShared: ArrayList<View>)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                GridOneFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
