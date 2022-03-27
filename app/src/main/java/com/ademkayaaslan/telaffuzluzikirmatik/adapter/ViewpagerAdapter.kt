package com.ademkayaaslan.telaffuzluzikirmatik.adapter


import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.ademkayaaslan.telaffuzluzikirmatik.R
import com.ademkayaaslan.telaffuzluzikirmatik.model.ViewpagerItem

class ViewpagerAdapter(

    val viewPager: ViewPager2,
    val slideList:ArrayList<ViewpagerItem>
):RecyclerView.Adapter<ViewpagerAdapter.ViewPagerViewHolder>()  {

    inner class ViewPagerViewHolder(val v:View):RecyclerView.ViewHolder(v) {

        val textViewHeaderFromQuran = v.findViewById<TextView>(R.id.textview_header_from_quran)
        val textViewHeader = v.findViewById<TextView>(R.id.textview_header)
        val textViewExplanation = v.findViewById<TextView>(R.id.textview_explanation)
        val textViewText = v.findViewById<TextView>(R.id.textview_body_text)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.slider_item,parent,false)
        return ViewPagerViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val slideItem = slideList[position]
        holder.textViewHeaderFromQuran.text = slideItem.headerFromQuran
        holder.textViewHeader.text = slideItem.header
        holder.textViewExplanation.text = slideItem.explanation
        holder.textViewText.text = slideItem.bodyText
        holder.textViewText.movementMethod = ScrollingMovementMethod()

    }


    override fun getItemCount(): Int = slideList.size
}