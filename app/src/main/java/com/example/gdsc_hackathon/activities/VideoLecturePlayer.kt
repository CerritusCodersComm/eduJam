package com.example.gdsc_hackathon.activities

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.RelativeLayout
import com.example.gdsc_hackathon.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.*
import java.io.*
import java.net.URL
import java.net.URLConnection



class VideoLecturePlayer :YouTubeBaseActivity() {

    lateinit var parentVew: RelativeLayout
    lateinit var downloadPdfButton: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_lecture_video_player)

        val ytPlayer = findViewById<YouTubePlayerView>(R.id.ytPlayer)
        parentVew = findViewById(R.id.VideoLecturePlayerLayout)
        downloadPdfButton = findViewById(R.id.downloadLectureNotesButton)

        val bundle: Bundle? = intent.extras
        val lectureID = bundle?.get("lectureID")
        ytPlayer.initialize(
            getString(R.string.developer_key),
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    youTubePlayer.cueVideo(lectureID.toString())
                    youTubePlayer.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Snackbar.make(parentVew, "Video player Failed", Snackbar.LENGTH_SHORT)
                        .show()
                }
            })


        downloadPdfButton.setOnClickListener {
            val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val uri =
                Uri.parse("https://drive.google.com/u/0/uc?id=1lQiSIweLdRywz204nAE5p3ztzJq_11pS&export=download")
            val request = DownloadManager.Request(uri)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
            val reference: Long = downloadManager.enqueue(request)
        }
    }
    private fun downloadFile(url: String, outputFile: File) {
        try {
            val u = URL(url)
            val conn: URLConnection = u.openConnection()
            val contentLength: Int = conn.contentLength
            val stream = DataInputStream(u.openStream())
            val buffer = ByteArray(contentLength)
            stream.readFully(buffer)
            stream.close()
            val fos = DataOutputStream(FileOutputStream(outputFile))
            fos.write(buffer)
            fos.flush()
            fos.close()
        } catch (e: FileNotFoundException) {
            return  // swallow a 404
        } catch (e: IOException) {
            return  // swallow a 404
        }
    }
}