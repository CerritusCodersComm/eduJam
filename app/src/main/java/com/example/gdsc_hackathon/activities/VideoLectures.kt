package com.example.gdsc_hackathon.activities

import android.os.Bundle
import android.widget.Button
import com.example.gdsc_hackathon.R
import android.widget.Toast

import com.google.android.youtube.player.YouTubeInitializationResult

import com.google.android.youtube.player.YouTubePlayer
import android.view.View
import com.google.android.youtube.player.YouTubeBaseActivity

import com.google.android.youtube.player.YouTubePlayerView

class VideoLectures : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_video_lectures)

        val ytPlayer = findViewById<View>(R.id.ytPlayer) as YouTubePlayerView

        ytPlayer.initialize(
            getString(R.string.developer_key),
            object : YouTubePlayer.OnInitializedListener {
                // Implement two methods by clicking on red
                // error bulb inside onInitializationSuccess
                // method add the video link or the playlist
                // link that you want to play In here we
                // also handle the play and pause
                // functionality
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    youTubePlayer.cueVideo("yOnELC2doXc")
//                    youTubePlayer.play()
                }

                // Inside onInitializationFailure
                // implement the failure functionality
                // Here we will show toast
                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Toast.makeText(applicationContext, "Video player Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }


