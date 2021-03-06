package com.demoxample.importantsamples.testingmodules

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.fragment_master_sf.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MasterSFFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_master_sf, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_records.layoutManager = LinearLayoutManager(activity)
        rv_records.adapter = RecordAdapterSF(object :RecordAdapterSF.RecordInteraction{
            override fun showDetailRecord(itemView: View) {
                listener?.showDetailScreen(itemView)
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener{
        fun showDetailScreen(itemView: View)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MasterSFFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
