package com.ademkayaaslan.telaffuzluzikirmatik.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.model.RecycleItem

class RecyclerViewAdapter(
    private val dhikrList: List<RecycleItem>,

) :
    RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewDhikrHolder>() {

    inner class RecyclerViewDhikrHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        //val textViewTarih = itemView.textview_recycle_item_tarih
        override fun onClick(v: View?) {
            v?.setOnClickListener {

            }
        }


    }


    override fun onBindViewHolder(holder: RecyclerViewDhikrHolder, position: Int) {

        val currentItem = dhikrList[position]

    }

    override fun getItemCount(): Int {
        return dhikrList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewDhikrHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycle_dhikr_item, parent, false)
        return RecyclerViewDhikrHolder(itemView)
    }



}