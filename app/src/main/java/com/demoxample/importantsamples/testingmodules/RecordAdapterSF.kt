package com.demoxample.importantsamples.testingmodules

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demoxample.importantsamples.R
import kotlinx.android.synthetic.main.layout_record.view.*

/**
 * Created by Gaurav on 09-05-2019.
 */
class RecordAdapterSF(private val listener: RecordInteraction) :
        RecyclerView.Adapter<RecordAdapterSF.Companion.RecordViewHolderSF>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): RecordViewHolderSF {
        return if (itemType == 1) RecordViewHolderSF(LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_record, parent, false), listener)
        else RecordViewHolderSF(LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_record_message, parent, false), listener)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) 1 else 2
    }

    override fun getItemCount(): Int {
        return 14
    }

    override fun onBindViewHolder(holder: RecordViewHolderSF, position: Int) {
        Glide.with(holder.itemView.context)
                .load(if (position % 2 == 0) R.drawable.homeland else R.drawable.gotham)
                .apply(RequestOptions().circleCrop())
                .into(holder.ivProfile)
    }

    companion object {
        class RecordViewHolderSF(itemView: View, listener: RecordInteraction) : RecyclerView.ViewHolder(itemView) {
            var ivProfile: ImageView = itemView.iv_profile

            init {
                itemView.setOnClickListener { listener.showDetailRecord() }
            }
        }
    }

    interface RecordInteraction {
        fun showDetailRecord()
    }
}