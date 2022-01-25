package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.AcademicCalendar

import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class AcademicCalendarEventAdapter(options: FirestoreRecyclerOptions<AcademicCalendar>) :
    FirestoreRecyclerAdapter<AcademicCalendar, AcademicCalendarEventAdapter.AnnouncementHolder>(
        options
    ) {

    private var announcementUrl: String = ""
//
//    private var onClick: .OnItemClicked? = null

//    interface OnItemClicked {
//        fun onItemClick(position: Int, announcementUrl: String)
//    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.academic_recycler_view_event, parent, false)

        return AnnouncementHolder(view)
    }

    override fun onBindViewHolder(
        holder: AnnouncementHolder,
        position: Int,
        model: AcademicCalendar
    ) {
        holder.date.text = "${model.startingDate}-${model.endingDate}"
        holder.title.text = model.eventName


//        if(announcementUrl.isEmpty())
//            holder.announcementExtra.text = "No Attached link"
//        holder.itemView.setOnClickListener { onClick!!.onItemClick(position, announcementUrl) }

    }

    class AnnouncementHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView = itemView.findViewById(R.id.eventSubTitle)
        var title: TextView = itemView.findViewById(R.id.eventTitle)
    }

//    fun setOnClick(onClick: OnItemClicked?) {
//        this.onClick = onClick
//    }
}