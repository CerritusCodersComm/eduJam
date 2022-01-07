package com.example.gdsc_hackathon.adapters

import android.content.Context
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gdsc_hackathon.R

import com.example.gdsc_hackathon.databinding.SingleProfileBinding



class ProfileAdapter(private val profiles: List<String>, private val image: List<Int>,
                     private val context: Context) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private var onClick: OnItemClicked? = null

    interface OnItemClicked {
        fun onItemClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =  SingleProfileBinding.inflate(LayoutInflater.from(parent.context,), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = profiles[position]
        val imagePosition = image[position]
//        val linkedin = linkedinIcon[position]
//        val github = githubIcon[position]

        holder.bind(name,imagePosition,context)

        holder.itemView.setOnClickListener { onClick!!.onItemClick(position) }

    }


    override fun getItemCount(): Int {
        return profiles.size
    }

    class ViewHolder(private var binding: SingleProfileBinding) :  RecyclerView.ViewHolder(binding.root)  {
        fun bind(name:String,image:Int,context:Context) {
            binding.memberImage.setImageResource(image)

            binding.memberName.text =name

//            binding.githubIcon.setImageResource(github)
//
//            binding.linkedinIcon.setImageResource(linkedin)

        }
    }

    fun setOnClick(onClick: OnItemClicked?) {
        this.onClick = onClick
    }



}