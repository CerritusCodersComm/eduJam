package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.VideoLecturesModel

class VideoLectureAdapter(private val lectures: List<VideoLecturesModel>) : RecyclerView.Adapter<VideoLectureAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_video_lecture, parent, false)

        return ViewHolder(view)
    }

    private var onClick: OnItemClicked? = null

    interface OnItemClicked {
        fun onItemClick(position: Int)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val videoLecturesModel = lectures[position]

        // sets the image to the imageview from our itemHolder class
//        holder.lectureIcon.setImageResource(recentLectureModel.lectureIcon)

        // sets the text to the textview from our itemHolder class
        holder.lectureTitle.text = videoLecturesModel.lectureTitle

        holder.lectureDate.text = videoLecturesModel.lectureDate

        holder.itemView.setOnClickListener { onClick!!.onItemClick(position) }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return lectures.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val lectureTitle: TextView = itemView.findViewById(R.id.lectureTitle)
        val lectureDate: TextView = itemView.findViewById(R.id.lectureDate)
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }

}

