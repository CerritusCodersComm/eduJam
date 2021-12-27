package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.gdsc_hackathon.R

class PracticalsFragment : Fragment() {


    /** Maths */
    private lateinit var mathsTitle: TextView
    private lateinit var mathsLayout: RelativeLayout

    /** Design Analysis Of Algorithms */
    private lateinit var designAnalysisOfAlgorithmsTitle: TextView
    private lateinit var designAnalysisOfAlgorithmsLayout: RelativeLayout

    /** Operating Systems */
    private lateinit var operatingSystemsTitle: TextView
    private lateinit var operatingSystemsLayout: RelativeLayout

    /** Computer Networks */
    private lateinit var computerNetworksTitle: TextView
    private lateinit var computerNetworksLayout: RelativeLayout

    /** Computer Graphics */
    private lateinit var computerGraphicsTitle: TextView
    private lateinit var computerGraphicsLayout: RelativeLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_practicals, container, false)

        /** Maths Views*/
        mathsTitle = rootView.findViewById(R.id.mathsTitle)

        mathsLayout = rootView.findViewById(R.id.mathsLayout)
        mathsLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.mathsTutorialFragment)
        }

        /** Design Analysis Of Algorithms Views*/
        designAnalysisOfAlgorithmsTitle =
            rootView.findViewById(R.id.designAnalysisOfAlgorithmsTitle)

        designAnalysisOfAlgorithmsLayout =
            rootView.findViewById(R.id.designAnalysisOfAlgorithmsLayout)
        designAnalysisOfAlgorithmsLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.designAnalysisOfAlgorithmsPracticalFragment)
        }

        /** Operating Systems Views*/
        operatingSystemsTitle = rootView.findViewById(R.id.operatingSystemsTitle)

        operatingSystemsLayout = rootView.findViewById(R.id.operatingSystemsLayout)
        operatingSystemsLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.operatingSystemPracticalFragment)
        }

        /** Computer Networks Views*/
        computerNetworksTitle = rootView.findViewById(R.id.computerNetworksTitle)

        computerNetworksLayout = rootView.findViewById(R.id.computerNetworksLayout)
        computerNetworksLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.computerNetworksPracticalFragment)
        }

        /** Computer Graphics Views*/
        computerGraphicsTitle = rootView.findViewById(R.id.computerGraphicsTitle)

        computerGraphicsLayout = rootView.findViewById(R.id.computerGraphicsLayout)
        computerGraphicsLayout.setOnClickListener {
            rootView.findNavController().navigate(R.id.computerGraphicsPracticalFragment)
        }
        return rootView
    }
}