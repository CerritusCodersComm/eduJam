package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.GlobalAnnouncementModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class GlobalAnnouncementAdapter(options: FirestoreRecyclerOptions<GlobalAnnouncementModel>) :
    FirestoreRecyclerAdapter<GlobalAnnouncementModel, GlobalAnnouncementAdapter.AnnouncementHolder>(
        options
    ) {

    private var announcementUrl: String = ""

    private var onClick: OnItemClicked? = null

    interface OnItemClicked {
        fun onItemClick(position: Int, announcementUrl: String)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.global_announcement_item, parent, false)

        return AnnouncementHolder(view)
    }

    override fun onBindViewHolder(
        holder: AnnouncementHolder,
        position: Int,
        model: GlobalAnnouncementModel
    ) {
        holder.announcementTitle.text = model.title
        holder.announcementDescription.text = model.description
        holder.announcementDate.text = model.date
        announcementUrl = model.url.toString()
        holder.announcementPinned = model.pinned!!

        if(announcementUrl.isEmpty())
            holder.announcementExtra.text = "No Attached link"
        holder.itemView.setOnClickListener { onClick!!.onItemClick(position, announcementUrl) }

    }

    class AnnouncementHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var announcementTitle: TextView = itemView.findViewById(R.id.announcementTitle)
        var announcementDescription: TextView = itemView.findViewById(R.id.announcementDescription)
        var announcementDate: TextView = itemView.findViewById(R.id.announcementDate)
        var announcementExtra: TextView = itemView.findViewById(R.id.announcementExtra)
        var announcementPinned: Int = 0
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }
}