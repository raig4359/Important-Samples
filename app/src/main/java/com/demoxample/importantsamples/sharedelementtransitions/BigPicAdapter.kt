package com.demoxample.importantsamples.sharedelementtransitions

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.demoxample.importantsamples.R

/**
 * Created by Gaurav on 06-12-2018.
 */
class BigPicAdapter : RecyclerView.Adapter<BigPicAdapter.BigPicViewHolder>() {

    private val pics = ArrayList<Int>().apply {
        add(R.drawable.arrow)
        add(R.drawable.homeland)
        add(R.drawable.gotham)
        add(R.drawable.big_bang)
        add(R.drawable.cars)
        add(R.drawable.top_gear)
        add(R.drawable.gotham)
        add(R.drawable.homeland)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BigPicViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.big_picture, p0, false)
        return BigPicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(p0: BigPicViewHolder, p1: Int) {
        p0.view.transitionName = p1.toString()
        (p0.view as ImageView).setImageResource(pics[p1])
    }

    class BigPicViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}