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
class SmallPicAdapter(val viewsShared: ArrayList<View>) : RecyclerView.Adapter<SmallPicAdapter.SmallPicViewHolder>() {

    init {
        viewsShared.clear()
    }

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

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SmallPicViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_picture, p0, false)
        viewsShared.add(view)
        return SmallPicViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(p0: SmallPicViewHolder, p1: Int) {
        p0.view.transitionName = p1.toString()
        (p0.view as ImageView).setImageResource(pics[p1])
    }

    class SmallPicViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}