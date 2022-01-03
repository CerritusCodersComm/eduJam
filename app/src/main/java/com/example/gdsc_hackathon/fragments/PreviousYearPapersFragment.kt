package com.example.gdsc_hackathon.fragments

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.extensions.downloadFile
import com.google.android.material.button.MaterialButton

class PreviousYearPapersFragment : Fragment() {

    /** Maths */
    private lateinit var mathsTitle: TextView
    private lateinit var mathsLayout: RelativeLayout
    private lateinit var maths2020Button: MaterialButton
    private lateinit var maths2019Button: MaterialButton

    /** Design Analysis Of Algorithms */
    private lateinit var designAnalysisOfAlgorithmsTitle: TextView
    private lateinit var designAnalysisOfAlgorithmsLayout: RelativeLayout
    private lateinit var designAnalysisOfAlgorithms2020Button: MaterialButton
    private lateinit var designAnalysisOfAlgorithms2019Button: MaterialButton

    /** Operating Systems */
    private lateinit var operatingSystemsTitle: TextView
    private lateinit var operatingSystemsLayout: RelativeLayout
    private lateinit var operatingSystems2020Button: MaterialButton
    private lateinit var operatingSystems2019Button: MaterialButton

    /** Computer Networks */
    private lateinit var computerNetworksTitle: TextView
    private lateinit var computerNetworksLayout: RelativeLayout
    private lateinit var computerNetworks2020Button: MaterialButton
    private lateinit var computerNetworks2019Button: MaterialButton

    /** Computer Graphics */
    private lateinit var computerGraphicsTitle: TextView
    private lateinit var computerGraphicsLayout: RelativeLayout
    private lateinit var computerGraphics2020Button: MaterialButton
    private lateinit var computerGraphics2019Button: MaterialButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_preview_year_papers, container, false)

        /** Maths Views*/
        mathsLayout = rootView.findViewById(R.id.mathsLayout)
        mathsLayout.setOnClickListener {
            downloadFile("https://drive.google.com/uc?id=1AVtE7opA2hZlaQWlB4A1cN6N_HU47bpX&export=download", "Maths-III ESE QP Dec 2021 Subjective")
        }

        /** Design Analysis Of Algorithms Views*/
        designAnalysisOfAlgorithmsLayout = rootView.findViewById(R.id.designAnalysisOfAlgorithmsLayout)
        designAnalysisOfAlgorithmsLayout.setOnClickListener {
            downloadFile("https://drive.google.com/uc?id=1AVtE7opA2hZlaQWlB4A1cN6N_HU47bpX&export=download", "Design Analysis Of Algorithms ESE QP Dec 2021 Subjective")
        }

        /** Operating Systems Views*/
        operatingSystemsLayout = rootView.findViewById(R.id.operatingSystemsLayout)
        operatingSystemsLayout.setOnClickListener {
            downloadFile("https://drive.google.com/uc?id=1_ppEZjyLdFOm9RNI9AAP67KhA4fr7FQs&export=download", "Operating Systems ESE QP Dec 2021 Subjective")
        }

        /** Computer Networks Views*/
        computerNetworksLayout = rootView.findViewById(R.id.computerNetworksLayout)
        computerNetworksLayout.setOnClickListener {
            downloadFile("https://drive.google.com/uc?id=1o5ZZGRy-zvtKdB6v0YAXnnsRW-H5gVI9&export=download", "Computer Networks ESE QP Dec 2021 Subjective")
        }

        /** Computer Graphics Views*/
        computerGraphicsLayout = rootView.findViewById(R.id.computerGraphicsLayout)
        computerGraphicsLayout.setOnClickListener {
            downloadFile("https://drive.google.com/uc?id=1o5ZZGRy-zvtKdB6v0YAXnnsRW-H5gVI9&export=download", "Computer Graphics ESE QP Dec 2021 Subjective")
        }
        return rootView
    }
}