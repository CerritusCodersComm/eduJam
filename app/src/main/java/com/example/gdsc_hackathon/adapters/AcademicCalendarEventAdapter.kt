package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.AcademicCalendarEventModel

class AcademicCalendarEventAdapter(private val events: List<AcademicCalendarEventModel>) : RecyclerView.Adapter<AcademicCalendarEventAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.academic_recycler_view_event, parent, false)

        return ViewHolder(view)
    }

    private var onClick: OnItemClicked? = null

    interface OnItemClicked {
        fun onItemClick(position: Int)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val academicCalendarEventModel = events[position]

        // sets the image to the imageview from our itemHolder class
        holder.eventIcon.setImageResource(academicCalendarEventModel.eventIcon)

        // sets the text to the textview from our itemHolder class
        holder.eventTitle.text = academicCalendarEventModel.eventTitle

        holder.eventSubTitle.text = academicCalendarEventModel.eventSubTitle

        holder.itemView.setOnClickListener { onClick!!.onItemClick(position) }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return events.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val eventIcon: ImageView = itemView.findViewById(R.id.eventIcon)
        val eventTitle: TextView = itemView.findViewById(R.id.eventTitle)
        val eventSubTitle: TextView = itemView.findViewById(R.id.eventSubTitle)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

}

