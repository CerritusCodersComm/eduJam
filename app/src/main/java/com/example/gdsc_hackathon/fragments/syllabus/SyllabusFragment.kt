package com.example.gdsc_hackathon.fragments.syllabus

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
import com.example.gdsc_hackathon.fragments.Practicals
import com.google.android.material.button.MaterialButton

class SyllabusFragment : Fragment() {

    /** Maths */
    private lateinit var mathsTitle: TextView
    private lateinit var mathsLayout: RelativeLayout
    private lateinit var mathsCheckoutModulesButton: MaterialButton
    private lateinit var mathsCheckoutTutorialsButton: MaterialButton

    /** Design Analysis Of Algorithms */
    private lateinit var designAnalysisOfAlgorithmsTitle: TextView
    private lateinit var designAnalysisOfAlgorithmsLayout: RelativeLayout
    private lateinit var designAnalysisOfAlgorithmsCheckoutModulesButton: MaterialButton
    private lateinit var designAnalysisOfAlgorithmsCheckoutPracticalsButton: MaterialButton

    /** Operating Systems */
    private lateinit var operatingSystemsTitle: TextView
    private lateinit var operatingSystemsLayout: RelativeLayout
    private lateinit var operatingSystemsCheckoutModulesButton: MaterialButton
    private lateinit var operatingSystemsCheckoutPracticalsButton: MaterialButton

    /** Computer Networks */
    private lateinit var computerNetworksTitle: TextView
    private lateinit var computerNetworksLayout: RelativeLayout
    private lateinit var computerNetworksCheckoutModulesButton: MaterialButton
    private lateinit var computerNetworksCheckoutPracticalsButton: MaterialButton

    /** Computer Graphics */
    private lateinit var computerGraphicsTitle: TextView
    private lateinit var computerGraphicsLayout: RelativeLayout
    private lateinit var computerGraphicsCheckoutModulesButton: MaterialButton
    private lateinit var computerGraphicsCheckoutPracticalsButton: MaterialButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_syllabus, container, false)

        /** Maths Views*/
        mathsLayout = rootView.findViewById(R.id.mathsLayout)
        mathsCheckoutModulesButton = rootView.findViewById(R.id.mathsCheckoutModulesButton)
        mathsCheckoutModulesButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.mathsFragment)
        }

        mathsCheckoutTutorialsButton = rootView.findViewById(R.id.mathsCheckoutTutorialButton)
        mathsCheckoutTutorialsButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.mathsTutorialFragment)
        }

        mathsTitle = rootView.findViewById(R.id.mathsTitle)
        mathsLayout.setOnClickListener {
            if (mathsCheckoutModulesButton.visibility == View.VISIBLE && mathsCheckoutTutorialsButton.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    mathsLayout,
                    AutoTransition()
                )
                mathsCheckoutModulesButton.visibility = View.GONE
                mathsCheckoutTutorialsButton.visibility = View.GONE
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
                mathsCheckoutModulesButton.visibility = View.VISIBLE
                mathsCheckoutTutorialsButton.visibility = View.VISIBLE
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
        designAnalysisOfAlgorithmsCheckoutModulesButton = rootView.findViewById(R.id.designAnalysisOfAlgorithmsCheckoutModulesButton)
        designAnalysisOfAlgorithmsCheckoutPracticalsButton = rootView.findViewById(R.id.designAnalysisOfAlgorithmsCheckoutPracticalButton)
        designAnalysisOfAlgorithmsCheckoutModulesButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.designAnalysisOfAlgorithmsFragment)
        }

        designAnalysisOfAlgorithmsCheckoutPracticalsButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.designAnalysisOfAlgorithmsPracticalFragment)
        }
        designAnalysisOfAlgorithmsTitle = rootView.findViewById(R.id.designAnalysisOfAlgorithmsTitle)
        designAnalysisOfAlgorithmsLayout.setOnClickListener {
            if (designAnalysisOfAlgorithmsCheckoutModulesButton.visibility == View.VISIBLE && designAnalysisOfAlgorithmsCheckoutPracticalsButton.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    designAnalysisOfAlgorithmsLayout,
                    AutoTransition()
                )
                designAnalysisOfAlgorithmsCheckoutModulesButton.visibility = View.GONE
                designAnalysisOfAlgorithmsCheckoutPracticalsButton.visibility = View.GONE
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
                designAnalysisOfAlgorithmsCheckoutModulesButton.visibility = View.VISIBLE
                designAnalysisOfAlgorithmsCheckoutPracticalsButton.visibility = View.VISIBLE
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
        operatingSystemsCheckoutModulesButton = rootView.findViewById(R.id.operatingSystemsCheckoutModulesButton)
        operatingSystemsCheckoutPracticalsButton = rootView.findViewById(R.id.operatingSystemsCheckoutPracticalButton)

        operatingSystemsCheckoutModulesButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.operatingSystemsFragment)
        }

        operatingSystemsCheckoutPracticalsButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.operatingSystemPracticalFragment)
        }

        operatingSystemsTitle = rootView.findViewById(R.id.operatingSystemsTitle)
        operatingSystemsLayout.setOnClickListener {
            if (operatingSystemsCheckoutModulesButton.visibility == View.VISIBLE && operatingSystemsCheckoutPracticalsButton.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    operatingSystemsLayout,
                    AutoTransition()
                )
                operatingSystemsCheckoutModulesButton.visibility = View.GONE
                operatingSystemsCheckoutPracticalsButton.visibility = View.GONE
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
                operatingSystemsCheckoutModulesButton.visibility = View.VISIBLE
                operatingSystemsCheckoutPracticalsButton.visibility = View.VISIBLE
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
        computerNetworksCheckoutModulesButton = rootView.findViewById(R.id.computerNetworksCheckoutModulesButton)
        computerNetworksCheckoutPracticalsButton = rootView.findViewById(R.id.computerNetworksCheckoutPracticalButton)

        computerNetworksCheckoutModulesButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.computerNetworksFragment)
        }

        computerNetworksCheckoutPracticalsButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.computerGraphicsPracticalFragment)
        }

        computerNetworksTitle = rootView.findViewById(R.id.computerNetworksTitle)
        computerNetworksLayout.setOnClickListener {
            if (computerNetworksCheckoutModulesButton.visibility == View.VISIBLE && computerNetworksCheckoutPracticalsButton.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    computerNetworksLayout,
                    AutoTransition()
                )
                computerNetworksCheckoutModulesButton.visibility = View.GONE
                computerNetworksCheckoutPracticalsButton.visibility = View.GONE
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
                computerNetworksCheckoutModulesButton.visibility = View.VISIBLE
                computerNetworksCheckoutPracticalsButton.visibility = View.VISIBLE
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
        computerGraphicsCheckoutModulesButton = rootView.findViewById(R.id.computerGraphicsCheckoutModulesButton)
        computerGraphicsCheckoutPracticalsButton = rootView.findViewById(R.id.computerGraphicsCheckoutPracticalButton)

        computerGraphicsCheckoutModulesButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.computerGraphicsFragment)
        }

        computerGraphicsCheckoutPracticalsButton.setOnClickListener {
            rootView.findNavController().navigate(R.id.computerGraphicsPracticalFragment)
        }

        computerGraphicsTitle = rootView.findViewById(R.id.computerGraphicsTitle)
        computerGraphicsLayout.setOnClickListener {
            if (computerGraphicsCheckoutModulesButton.visibility == View.VISIBLE && computerGraphicsCheckoutPracticalsButton.visibility == View.VISIBLE) {

                TransitionManager.beginDelayedTransition(
                    computerGraphicsLayout,
                    AutoTransition()
                )
                computerGraphicsCheckoutModulesButton.visibility = View.GONE
                computerGraphicsCheckoutPracticalsButton.visibility = View.GONE
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
                computerGraphicsCheckoutModulesButton.visibility = View.VISIBLE
                computerGraphicsCheckoutPracticalsButton.visibility = View.VISIBLE
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