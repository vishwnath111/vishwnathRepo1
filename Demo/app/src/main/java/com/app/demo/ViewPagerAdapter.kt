package com.app.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val images: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>(){


    inner class ViewPagerViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val imageview:ImageView = itemview.findViewById(R.id.iv_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item,parent,false)
        return ViewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val curImage  = images[position]
        holder.imageview.setImageResource(curImage)
    }
    override fun getItemCount(): Int {
        return images.size
    }
}