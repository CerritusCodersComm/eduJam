package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FillMyCycle : AppCompatActivity(), OnItemSelectedListener {
    var noexp=0
    var courses = arrayOf<String?>("AM-IV", "Design and Analysis of Algorithm",
        "Operating System", "Computer Networks",
        "Computer Graphics")
    var amtuts = arrayOf<String?>("Tutorial on Conditional  Probability and Bayes theorem", "Tutorial on discrete random variable", "Tutorial on continuous random variable", "Tutorial on measure of Central Tendency and Dispersion", "Tutorial on Binomial and Poisson Distribution", "Tutorial on Normal Distribution", "Tutorial on Simplex method", "Tutorial on Dual Simplex method", "Tutorial on Correlation", "Tutorial on Regression", "Tutorial on Curve fitting", "Tutorial on logic operations and truth tables equivalence", "Tutorial on Normal Forms, Predicates and Quantifiers", "Tutorial on Algebraic structures with one binary operation", "Tutorial on Field and Coding theory")
    var daaexp= arrayOf<String?>("Develop a code for Quick Sort", "Develop a code for Dijkstra’s algorithm using Greedy method and analyze it", "Develop a code Minimum spanning tree, Kruskal’s algorithm using Greedy method and analyze it.", "Develop a code for all pair shortest path problem using dynamic programming and analyze it.", "Develop a code for Longest common subsequence using dynamic programming and analyze it.", "Develop a code for 8 queen’s problem using backtracking approach and analyze it.", "Develop a code for 15 puzzle problem and analyze it.", "Develop a code for naïve string matching Algorithm", "Case Study","","","")
    var osexp= arrayOf<String?>("Explore LINUX Commands (Basic and Advanced)", "Write a program to implement any two CPU scheduling algorithms like FCFS, SJF, Round Robin etc.", "Write a program to implement Dinning Philosopher Problem.", "Write a program to implement Banker’s algorithm.", "Build a program to implement FIFO and LRU page replacement policies", "Build a program to implement SRTF and Priority page replacement policies", "Develop a program to implement dynamic partitioning placement algorithms", "Build a program to implement FCFS and SSTF disk scheduling algorithm", "Case Study","","","")
    var cnexp=arrayOf<String?>("Classify the types of cabling used in networking", "Survey various networking devices using Packet Tracer", "Apply CRC/ Hamming code for error detection and correction", "Explain Basic Networking Operations and troubleshooting", "Prepare a network and configure it for IP addressing, subnetting, masking", "Demonstrate working of Static Routing Protocols", "Demonstrate working of Dynamic Routing Protocols", "Show implementation of Socket programming using TCP and Remote Login using", "Case Study","","","")
    var cgexp=arrayOf<String?>("Develop a program for DDA and Bresenham Line Drawing algorithms", "Implement midpoint Circlea/Ellipse algorithm", "Develop a program for Boundary fill and Flood fill algorithm", "Develop a program for Basic transformation on 2D objects", "Design and develop a program for line Clipping Algorithm", "Design and Develop a program for Polygon clipping", "a) Develop a program for Bezier curve for n control points\nb) Design a program to draw Fractals", "Implement Basic primitives using OpenGL", "Case Study","","","")
    var selected = "AM-IV"
    var previous = 12
    var amount = 12
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmc_input)
        val noval = findViewById<EditText>(R.id.noval)

        val fm1 = findViewById<EditText>(R.id.fm1)
        val fm2 = findViewById<EditText>(R.id.fm2)
        val fm3 = findViewById<EditText>(R.id.fm3)

        val exp_1_input = findViewById<TextInputEditText>(R.id.exp_1_input)
        val exp_2_input = findViewById<TextInputEditText>(R.id.exp_2_input)
        val exp_3_input = findViewById<TextInputEditText>(R.id.exp_3_input)
        val exp_4_input = findViewById<TextInputEditText>(R.id.exp_4_input)
        val exp_5_input = findViewById<TextInputEditText>(R.id.exp_5_input)
        val exp_6_input = findViewById<TextInputEditText>(R.id.exp_6_input)
        val exp_7_input = findViewById<TextInputEditText>(R.id.exp_7_input)
        val exp_8_input = findViewById<TextInputEditText>(R.id.exp_8_input)
        val exp_9_input = findViewById<TextInputEditText>(R.id.exp_9_input)
        val exp_10_input = findViewById<TextInputEditText>(R.id.exp_10_input)
        val exp_11_input = findViewById<TextInputEditText>(R.id.exp_11_input)
        val exp_12_input = findViewById<TextInputEditText>(R.id.exp_12_input)

        val exp_1 = findViewById<TextInputLayout>(R.id.exp_1)
        val exp_2 = findViewById<TextInputLayout>(R.id.exp_2)
        val exp_3 = findViewById<TextInputLayout>(R.id.exp_3)
        val exp_4 = findViewById<TextInputLayout>(R.id.exp_4)
        val exp_5 = findViewById<TextInputLayout>(R.id.exp_5)
        val exp_6 = findViewById<TextInputLayout>(R.id.exp_6)
        val exp_7 = findViewById<TextInputLayout>(R.id.exp_7)
        val exp_8 = findViewById<TextInputLayout>(R.id.exp_8)
        val exp_9 = findViewById<TextInputLayout>(R.id.exp_9)
        val exp_10 = findViewById<TextInputLayout>(R.id.exp_10)
        val exp_11 = findViewById<TextInputLayout>(R.id.exp_11)
        val exp_12 = findViewById<TextInputLayout>(R.id.exp_12)

        noval.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(s.toString() != "") {
                    amount = Integer.parseInt(s.toString())
                }
                if(amount<previous){
                    for(i in previous downTo amount+1){
                        when(i){
                            12->exp_12.visibility=View.INVISIBLE
                            11->exp_11.visibility=View.INVISIBLE
                            10->exp_10.visibility=View.INVISIBLE
                            9->exp_9.visibility=View.INVISIBLE
                            8->exp_8.visibility=View.INVISIBLE
                            7->exp_7.visibility=View.INVISIBLE
                            6->exp_6.visibility=View.INVISIBLE
                            5->exp_5.visibility=View.INVISIBLE
                            4->exp_4.visibility=View.INVISIBLE
                            3->exp_3.visibility=View.INVISIBLE
                            2->exp_2.visibility=View.INVISIBLE
                            1->exp_1.visibility=View.INVISIBLE
                        }
                    }
                    previous = amount
                }
                else if(previous<amount){
                    for(i in previous+1..amount){
                        when(i){
                            12->exp_12.visibility=View.VISIBLE
                            11->exp_11.visibility=View.VISIBLE
                            10->exp_10.visibility=View.VISIBLE
                            9->exp_9.visibility=View.VISIBLE
                            8->exp_8.visibility=View.VISIBLE
                            7->exp_7.visibility=View.VISIBLE
                            6->exp_6.visibility=View.VISIBLE
                            5->exp_5.visibility=View.VISIBLE
                            4->exp_4.visibility=View.VISIBLE
                            3->exp_3.visibility=View.VISIBLE
                            2->exp_2.visibility=View.VISIBLE
                            1->exp_1.visibility=View.VISIBLE
                        }
                    }
                    previous = amount
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        noval.filters = arrayOf<InputFilter>(MinMaxFilter(1, 12))
        fm1.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        fm2.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        fm3.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_1_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_2_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_3_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_4_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_5_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_6_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_7_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_8_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_9_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_10_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_11_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        exp_12_input.filters = arrayOf<InputFilter>(MinMaxFilter(1, 100))
        val spin = findViewById<Spinner>(R.id.spinny)
        spin.onItemSelectedListener = this
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            courses)
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = ad
        val dopdoc: Button = findViewById(R.id.dopdoc)
        dopdoc.setOnClickListener{
            val intent = Intent(this, FmcExpMarksInput::class.java)
            intent.putExtra("noval", noval.text.toString())
            intent.putExtra("exp_1_input", exp_1_input.text.toString())
            intent.putExtra("exp_2_input", exp_2_input.text.toString())
            intent.putExtra("exp_3_input", exp_3_input.text.toString())
            intent.putExtra("exp_4_input", exp_4_input.text.toString())
            intent.putExtra("exp_5_input", exp_5_input.text.toString())
            intent.putExtra("exp_6_input", exp_6_input.text.toString())
            intent.putExtra("exp_7_input", exp_7_input.text.toString())
            intent.putExtra("exp_8_input", exp_8_input.text.toString())
            intent.putExtra("exp_9_input", exp_9_input.text.toString())
            intent.putExtra("exp_10_input", exp_10_input.text.toString())
            intent.putExtra("exp_11_input", exp_11_input.text.toString())
            intent.putExtra("exp_12_input", exp_12_input.text.toString())
            intent.putExtra("fm1", fm1.text.toString())
            intent.putExtra("fm2", fm2.text.toString())
            intent.putExtra("fm3", fm3.text.toString())
            intent.putExtra("amount", amount)
            startActivity(intent)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?,
                                view: View, position: Int,
                                id: Long) {
        selected = courses[position].toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    inner class MinMaxFilter() : InputFilter {
        private var intMin: Int = 0
        private var intMax: Int = 0

        // Initialized
        constructor(minValue: Int, maxValue: Int) : this() {
            this.intMin = minValue
            this.intMax = maxValue
        }

        override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned, dStart: Int, dEnd: Int): CharSequence? {
            try {
                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(intMin, intMax, input)) {
                    return null
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return ""
        }
        private fun isInRange(a: Int, b: Int, c: Int): Boolean {
            return if (b > a) c in a..b else c in b..a
        }
    }

}
