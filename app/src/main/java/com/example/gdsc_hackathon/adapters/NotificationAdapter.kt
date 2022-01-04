package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.NotificationModel

class NotificationAdapter(private val events: List<NotificationModel>) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)

        return ViewHolder(view)
    }

//    private var onClick: OnItemClicked? = null
//
//    interface OnItemClicked {
//        fun onItemClick(position: Int)
//    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val academicCalendarEventModel = events[position]

        // sets the text to the textview from our itemHolder class
        holder.notificationTitle.text = academicCalendarEventModel.notificationTitle

        holder.notificationContent.text = academicCalendarEventModel.notificationContent

//        holder.itemView.setOnClickListener { onClick!!.onItemClick(position) }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return events.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val notificationTitle: TextView = itemView.findViewById(R.id.notificationTitle)
        val notificationContent: TextView = itemView.findViewById(R.id.notificationContent)
    }

//    fun setOnClick(onClick: OnItemClicked?) {
//        this.onClick = onClick
//    }

}

