package com.demoxample.importantsamples.sharedelementtransitions

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.transition.ChangeBounds
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.fragment_grid_two.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GridTwoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: GridTwoInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        sharedElementEnterTransition = ChangeBounds()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grid_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postponeEnterTransition()

        rv_big_pictures.layoutManager = GridLayoutManager(activity!!, 3)
        rv_big_pictures.adapter = BigPicAdapter()

        startPostponedEnterTransition()
    }

    override fun onResume() {
        super.onResume()
        activity?.title = "Detail"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is GridTwoInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement GridTwoInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface GridTwoInteractionListener

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                GridTwoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
