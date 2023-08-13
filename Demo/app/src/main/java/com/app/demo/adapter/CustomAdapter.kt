package com.app.demo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.demo.R
import com.bumptech.glide.Glide
import com.example.myapplication.models.Data

class CustomAdapter(val userList: ArrayList<Data>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        //this method is returning the view for each item in the list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(v)
          }

       //this method is binding the data on the list
        override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
           holder.tvFname.text = userList[position].firstName
            holder.tvLname.text = userList[position].lastName
            holder.tvEmail.text = userList[position].email

           if (userList[position].avatar !== null) {
                Glide.with(holder.itemView)
                    .load(userList[position].avatar)
                    .into(holder.imageView)
              } else {
               holder.imageView.setImageResource(R.drawable.ic_launcher_background)
               }
        }

        //this method is giving the size of the list
        override fun getItemCount(): Int {
            return userList.size
        }

        //the class is hodling the list view
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.ivUser)
            val tvFname: TextView = itemView.findViewById(R.id.tvFname)
            val tvLname: TextView = itemView.findViewById(R.id.tvLname)
            val tvEmail: TextView = itemView.findViewById(R.id.tvEmail)
        }
    }