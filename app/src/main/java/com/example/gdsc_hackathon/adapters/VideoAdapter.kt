package com.example.gdsc_hackathon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Video
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class VideoAdapter(options: FirestoreRecyclerOptions<Video>) :
    FirestoreRecyclerAdapter<Video, VideoAdapter.VideoHolder>(options) {
    private var listener: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.video_item,
            parent, false
        )
        return VideoHolder(v)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int, model: Video) {
        holder.videoTitle.text = model.videoName
//        holder.itemView.setOnClickListener {
//            if (position != RecyclerView.NO_POSITION && listener != null) {
//                listener!!.onItemClick(snapshots.getSnapshot(position).id)
//            }
//        }
    }

    class VideoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoTitle : TextView = itemView.findViewById(R.id.videoTitle)
    }

    interface OnItemClickListener {
        fun onItemClick(documentSnapshot: String)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}