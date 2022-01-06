package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.HolidaysItemModel

class HolidaysItemAdapter(private val holidays: List<HolidaysItemModel>) : RecyclerView.Adapter<HolidaysItemAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holidays_recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val holidaysItemModel = holidays[position]

        // sets the image to the imageview from our itemHolder class
        holder.eventIcon.setImageResource(holidaysItemModel.holidayIcon)

        // sets the text to the textview from our itemHolder class
        holder.eventTitle.text = holidaysItemModel.holidayReason

        holder.eventSubTitle.text = holidaysItemModel.holidayDate

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return holidays.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val eventIcon: ImageView = itemView.findViewById(R.id.holidayIcon)
        val eventTitle: TextView = itemView.findViewById(R.id.holidayReason)
        val eventSubTitle: TextView = itemView.findViewById(R.id.holidayDate)
    }
}

