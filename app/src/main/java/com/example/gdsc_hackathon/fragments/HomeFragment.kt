package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.adapters.RecentLectureAdapter
import com.example.gdsc_hackathon.dataModel.RecentLectureModel

import com.google.firebase.auth.FirebaseAuth

import com.example.gdsc_hackathon.network.Api
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

import com.example.gdsc_hackathon.extensions.copyToClipboard
import com.example.gdsc_hackathon.extensions.showSnackBarWithAction





class HomeFragment : Fragment() {
    private lateinit var syllabusLayout: LinearLayout
    private lateinit var weeklyTimeTableLayout: LinearLayout
    private lateinit var holidayLayout: LinearLayout
    private lateinit var examTimeConstraintLayout: LinearLayout
    private lateinit var practicalLayout: LinearLayout
    private lateinit var previousYearPapersLayout: LinearLayout
    private lateinit var academicCalendarLayout: LinearLayout
    private lateinit var moreLayout: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var quote: TextView
    private lateinit var quoteAuthor: TextView
    private lateinit var adapter: RecentLectureAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var quoteBannerLayout: RelativeLayout
    private lateinit var  quoteList: JsonObject

    private lateinit var mAuth : FirebaseAuth

//    https://api.quotable.io/random?tags=famous-quotes|friendship|wisdom|technology&maxLen=250

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView: View = inflater.inflate(R.layout.fragment_home, container, false)

        syllabusLayout = rootView.findViewById(R.id.syllabusLayout)
        syllabusLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.syllabusFragment)
        }

        weeklyTimeTableLayout = rootView.findViewById(R.id.weeklyTimeTableLayout)
        weeklyTimeTableLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.weeklyTimeTableFragment)
        }

        holidayLayout = rootView.findViewById(R.id.holidayLayout)
        holidayLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.holidayFragment)
        }

        examTimeConstraintLayout = rootView.findViewById(R.id.examTimeConstraintLayout)
        examTimeConstraintLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.examTimeConstraintFragment)
        }

        practicalLayout = rootView.findViewById(R.id.practicalLayout)
        practicalLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.practicalFragment)
        }

        previousYearPapersLayout = rootView.findViewById(R.id.previousYearPapersLayout)
        previousYearPapersLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.previousYearPapersFragment)
        }

        academicCalendarLayout = rootView.findViewById(R.id.academicCalendarLayout)
        academicCalendarLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.academicCalendarFragment)
        }

        moreLayout = rootView.findViewById(R.id.moreLayout)
        moreLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.moreFragment)
        }


        recyclerView = rootView.findViewById(R.id.recent_lectures_recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(context)

        val lectures = ArrayList<RecentLectureModel>()

        lectures.add(RecentLectureModel(R.drawable.ic_baseline_video_camera_front_24, "MATHS","25th December, 2021", "20:00"))
        lectures.add(RecentLectureModel(R.drawable.ic_baseline_video_camera_front_24, "PHYSICS","27th December, 2021","20:00"))
        for (i in 2..20) {
            lectures.add(RecentLectureModel(R.drawable.ic_baseline_video_camera_front_24, "Item $i", "1 JAN","TIME: 16:00" ))
        }

        adapter = RecentLectureAdapter(lectures)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

        quote = rootView.findViewById(R.id.quote)
        quoteAuthor = rootView.findViewById(R.id.quote_author)

        progressBar = rootView.findViewById(R.id.progress_bar)
        getQuotes()

        quoteBannerLayout = rootView.findViewById(R.id.quote_banner_layout)
        quoteBannerLayout.setOnClickListener {
            getQuotes()
        }
        quoteBannerLayout.setOnLongClickListener {
            copyQuote()
            true // <- set to true
        }
        return rootView
    }

    private fun copyQuote() {
        requireContext().copyToClipboard(quote.text.toString())
        showSnackBarWithAction(
            requireActivity(),
            "Quote Copied!",
            "Share Quote?",
            quote.text.toString()
        )
    }


    private fun getQuotes(){
        val apiInterface = Api.create().getQuotes()
        progressBar.visibility =View.VISIBLE
        apiInterface.enqueue( object : Callback<JsonObject>{
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {

                if(response?.body() != null){
                    quoteList = response.body()!!
                    progressBar.visibility =View.INVISIBLE
                    quote.text = quoteList.get("content").toString()
                    val dash = "-"
                    quoteAuthor.text = dash.plus(quoteList.get("author").toString().subSequence(1,quoteList.get("author").toString().length-1))
                }
            }

            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.w("MyTag", "requestFailed", t)
            }
        })
    }
}

