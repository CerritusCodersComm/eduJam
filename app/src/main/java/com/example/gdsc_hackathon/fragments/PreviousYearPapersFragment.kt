package com.example.gdsc_hackathon.fragments

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
        maths2020Button = rootView.findViewById(R.id.maths2020Button)
//        maths2020Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.mathsFragment)
//        }
//
//        maths2019Button = rootView.findViewById(R.id.maths2019Button)
//        maths2019Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.mathsTutorialFragment)
//        }

        mathsTitle = rootView.findViewById(R.id.mathsTitle)
        mathsLayout.setOnClickListener {
            if (maths2019Button.visibility == View.VISIBLE && maths2020Button.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    mathsLayout,
                    AutoTransition()
                )
                maths2019Button.visibility = View.GONE
                maths2020Button.visibility = View.GONE
                mathsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_down_24, //right
                    0
                )
            } else {

                TransitionManager.beginDelayedTransition(
                    mathsLayout,
                    AutoTransition()
                )
                maths2019Button.visibility = View.VISIBLE
                maths2020Button.visibility = View.VISIBLE
                mathsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0
                )

            }
        }


        /** Design Analysis Of Algorithms Views*/
        designAnalysisOfAlgorithmsLayout = rootView.findViewById(R.id.designAnalysisOfAlgorithmsLayout)
        designAnalysisOfAlgorithms2020Button = rootView.findViewById(R.id.designAnalysisOfAlgorithms2020Button)
        designAnalysisOfAlgorithms2019Button = rootView.findViewById(R.id.designAnalysisOfAlgorithms2019Button)

//        designAnalysisOfAlgorithms2020Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.designAnalysisOfAlgorithmsFragment)
//        }
//
//        designAnalysisOfAlgorithms2019Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.designAnalysisOfAlgorithmsPracticalFragment)
//        }
        designAnalysisOfAlgorithmsTitle = rootView.findViewById(R.id.designAnalysisOfAlgorithmsTitle)
        designAnalysisOfAlgorithmsLayout.setOnClickListener {
            if (designAnalysisOfAlgorithms2020Button.visibility == View.VISIBLE && designAnalysisOfAlgorithms2019Button.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    designAnalysisOfAlgorithmsLayout,
                    AutoTransition()
                )
                designAnalysisOfAlgorithms2020Button.visibility = View.GONE
                designAnalysisOfAlgorithms2019Button.visibility = View.GONE
                designAnalysisOfAlgorithmsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_down_24, //right
                    0
                )
            } else {

                TransitionManager.beginDelayedTransition(
                    designAnalysisOfAlgorithmsLayout,
                    AutoTransition()
                )
                designAnalysisOfAlgorithms2020Button.visibility = View.VISIBLE
                designAnalysisOfAlgorithms2019Button.visibility = View.VISIBLE
                designAnalysisOfAlgorithmsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0
                )

            }
        }

        /** Operating Systems Views*/
        operatingSystemsLayout = rootView.findViewById(R.id.operatingSystemsLayout)
        operatingSystems2020Button = rootView.findViewById(R.id.operatingSystems2020Button)
        operatingSystems2019Button = rootView.findViewById(R.id.operatingSystems2019Button)

//        operatingSystems2020Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.operatingSystemsFragment)
//        }
//
//        operatingSystems2019Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.operatingSystemPracticalFragment)
//        }

        operatingSystemsTitle = rootView.findViewById(R.id.operatingSystemsTitle)
        operatingSystemsLayout.setOnClickListener {
            if (operatingSystems2020Button.visibility == View.VISIBLE && operatingSystems2019Button.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    operatingSystemsLayout,
                    AutoTransition()
                )
                operatingSystems2020Button.visibility = View.GONE
                operatingSystems2019Button.visibility = View.GONE
                operatingSystemsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_down_24, //right
                    0
                )
            } else {

                TransitionManager.beginDelayedTransition(
                    operatingSystemsLayout,
                    AutoTransition()
                )
                operatingSystems2020Button.visibility = View.VISIBLE
                operatingSystems2019Button.visibility = View.VISIBLE
                operatingSystemsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0
                )

            }
        }

        /** Computer Networks Views*/
        computerNetworksLayout = rootView.findViewById(R.id.computerNetworksLayout)
        computerNetworks2020Button = rootView.findViewById(R.id.computerNetworks2020Button)
        computerNetworks2019Button = rootView.findViewById(R.id.computerNetworks2019Button)

//        computerNetworks2020Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.computerNetworksFragment)
//        }
//
//        computerNetworks2019Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.computerGraphicsPracticalFragment)
//        }

        computerNetworksTitle = rootView.findViewById(R.id.computerNetworksTitle)
        computerNetworksLayout.setOnClickListener {
            if (computerNetworks2020Button.visibility == View.VISIBLE && computerNetworks2019Button.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    computerNetworksLayout,
                    AutoTransition()
                )
                computerNetworks2020Button.visibility = View.GONE
                computerNetworks2019Button.visibility = View.GONE
                computerNetworksTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_down_24, //right
                    0
                )
            } else {

                TransitionManager.beginDelayedTransition(
                    computerNetworksLayout,
                    AutoTransition()
                )
                computerNetworks2020Button.visibility = View.VISIBLE
                computerNetworks2019Button.visibility = View.VISIBLE
                computerNetworksTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0
                )

            }
        }


        /** Computer Graphics Views*/
        computerGraphicsLayout = rootView.findViewById(R.id.computerGraphicsLayout)
        computerGraphics2020Button = rootView.findViewById(R.id.computerGraphics2020Button)
        computerGraphics2019Button = rootView.findViewById(R.id.computerGraphics2019Button)

//        computerGraphics2020Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.computerGraphicsFragment)
//        }
//
//        computerGraphics2019Button.setOnClickListener {
//            rootView.findNavController().navigate(R.id.computerGraphicsPracticalFragment)
//        }

        computerGraphicsTitle = rootView.findViewById(R.id.computerGraphicsTitle)
        computerGraphicsLayout.setOnClickListener {
            if (computerGraphics2020Button.visibility == View.VISIBLE && computerGraphics2019Button.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    computerGraphicsLayout,
                    AutoTransition()
                )
                computerGraphics2020Button.visibility = View.GONE
                computerGraphics2019Button.visibility = View.GONE
                computerGraphicsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_down_24, //right
                    0
                )
            } else {

                TransitionManager.beginDelayedTransition(
                    computerGraphicsLayout,
                    AutoTransition()
                )
                computerGraphics2020Button.visibility = View.VISIBLE
                computerGraphics2019Button.visibility = View.VISIBLE
                computerGraphicsTitle.setCompoundDrawablesWithIntrinsicBounds(
                    0, //left
                    0, //top
                    R.drawable.ic_baseline_arrow_drop_up_24, //right
                    0
                )

            }
        }
        return rootView
    }
}