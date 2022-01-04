package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.NotificationAdapter
import com.example.gdsc_hackathon.dataModel.NotificationModel
import android.content.Intent

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class NotificationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private lateinit var notifications: ArrayList<NotificationModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_notification, container, false)

        recyclerView = rootView.findViewById(R.id.notification_recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        notifications = ArrayList()

        adapter = NotificationAdapter(notifications)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

        notifications.add(
            NotificationModel(
                "Nice",
                "Sup"
            )
        )

        val intentFilter = IntentFilter("com.google.firebase.INSTANCE_ID_EVENT")
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(onNotice,intentFilter)
        return rootView
    }
    private val onNotice: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val title = intent!!.getStringExtra("title")
            val content = intent.getStringExtra("content")
            Log.d("receiver", "Got title: $title")
            Log.d("receiver", "Got content: $content")
            // Update your RecyclerView here using notifyItemInserted(position);
        }
    }
    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("com.google.firebase.INSTANCE_ID_EVENT")
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(onNotice,intentFilter)
    }
    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(onNotice)
    }
}