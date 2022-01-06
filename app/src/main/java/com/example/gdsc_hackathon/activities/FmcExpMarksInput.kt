package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FmcExpMarksInput: AppCompatActivity() {

    var amtuts = arrayOf<String?>("Tutorial on Conditional  Probability and Bayes theorem", "Tutorial on discrete random variable", "Tutorial on continuous random variable", "Tutorial on measure of Central Tendency and Dispersion", "Tutorial on Binomial and Poisson Distribution", "Tutorial on Normal Distribution", "Tutorial on Simplex method", "Tutorial on Dual Simplex method", "Tutorial on Correlation", "Tutorial on Regression", "Tutorial on Curve fitting", "Tutorial on logic operations and truth tables equivalence", "Tutorial on Normal Forms, Predicates and Quantifiers", "Tutorial on Algebraic structures with one binary operation", "Tutorial on Field and Coding theory")
    var daaexp= arrayOf<String?>("Develop a code for Quick Sort", "Develop a code for Dijkstra’s algorithm using Greedy method and analyze it", "Develop a code Minimum spanning tree, Kruskal’s algorithm using Greedy method and analyze it.", "Develop a code for all pair shortest path problem using dynamic programming and analyze it.", "Develop a code for Longest common subsequence using dynamic programming and analyze it.", "Develop a code for 8 queen’s problem using backtracking approach and analyze it.", "Develop a code for 15 puzzle problem and analyze it.", "Develop a code for naïve string matching Algorithm", "Case Study","","","")
    var osexp= arrayOf<String?>("Explore LINUX Commands (Basic and Advanced)", "Write a program to implement any two CPU scheduling algorithms like FCFS, SJF, Round Robin etc.", "Write a program to implement Dinning Philosopher Problem.", "Write a program to implement Banker’s algorithm.", "Build a program to implement FIFO and LRU page replacement policies", "Build a program to implement SRTF and Priority page replacement policies", "Develop a program to implement dynamic partitioning placement algorithms", "Build a program to implement FCFS and SSTF disk scheduling algorithm", "Case Study","","","")
    var cnexp=arrayOf<String?>("Classify the types of cabling used in networking", "Survey various networking devices using Packet Tracer", "Apply CRC/ Hamming code for error detection and correction", "Explain Basic Networking Operations and troubleshooting", "Prepare a network and configure it for IP addressing, subnetting, masking", "Demonstrate working of Static Routing Protocols", "Demonstrate working of Dynamic Routing Protocols", "Show implementation of Socket programming using TCP and Remote Login using", "Case Study","","","")
    var cgexp=arrayOf<String?>("Develop a program for DDA and Bresenham Line Drawing algorithms", "Implement midpoint Circlea/Ellipse algorithm", "Develop a program for Boundary fill and Flood fill algorithm", "Develop a program for Basic transformation on 2D objects", "Design and develop a program for line Clipping Algorithm", "Design and Develop a program for Polygon clipping", "a) Develop a program for Bezier curve for n control points\nb) Design a program to draw Fractals", "Implement Basic primitives using OpenGL", "Case Study","","","")
    var selected = "AM-IV"
    var amount = 1
    var noexp=0

    var noval = ""
    var exp_1_input = ""
    var exp_2_input = ""
    var exp_3_input = ""
    var exp_4_input = ""
    var exp_5_input = ""
    var exp_6_input = ""
    var exp_7_input = ""
    var exp_8_input = ""
    var exp_9_input = ""
    var exp_10_input = ""
    var exp_11_input = ""
    var exp_12_input = ""

    var fm1 = ""
    var fm2 = ""
    var fm3 = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fmc_exp_marks_input)

        val bundle = intent.extras
        if (bundle != null) {
            noval = bundle.getString("noval").toString()

            exp_1_input = bundle.getString("exp_1_input").toString()
            exp_2_input = bundle.getString("exp_1_input").toString()
            exp_3_input = bundle.getString("exp_2_input").toString()
            exp_4_input = bundle.getString("exp_3_input").toString()
            exp_5_input = bundle.getString("exp_4_input").toString()
            exp_6_input = bundle.getString("exp_5_input").toString()
            exp_7_input = bundle.getString("exp_6_input").toString()
            exp_8_input = bundle.getString("exp_7_input").toString()
            exp_9_input = bundle.getString("exp_8_input").toString()
            exp_10_input = bundle.getString("exp_9_input").toString()
            exp_11_input = bundle.getString("exp_10_input").toString()
            exp_12_input = bundle.getString("exp_11_input").toString()

            fm1 = bundle.getString("fm1").toString()
            fm2 = bundle.getString("fm2").toString()
            fm3 = bundle.getString("fm3").toString()

            amount = bundle.getInt("amount")
        }
        val expdop1 = findViewById<TextInputEditText>(R.id.expdop_1_input)
        val expdop2 = findViewById<TextInputEditText>(R.id.expdop_2_input)
        val expdop3 = findViewById<TextInputEditText>(R.id.expdop_3_input)
        val expdop4 = findViewById<TextInputEditText>(R.id.expdop_4_input)
        val expdop5 = findViewById<TextInputEditText>(R.id.expdop_5_input)
        val expdop6 = findViewById<TextInputEditText>(R.id.expdop_6_input)
        val expdop7 = findViewById<TextInputEditText>(R.id.expdop_7_input)
        val expdop8 = findViewById<TextInputEditText>(R.id.expdop_8_input)
        val expdop9 = findViewById<TextInputEditText>(R.id.expdop_9_input)
        val expdop10 = findViewById<TextInputEditText>(R.id.expdop_10_input)
        val expdop11 = findViewById<TextInputEditText>(R.id.expdop_11_input)
        val expdop12 = findViewById<TextInputEditText>(R.id.expdop_12_input)
        val expdoc1 = findViewById<TextInputEditText>(R.id.expdoc_1_input)
        val expdoc2 = findViewById<TextInputEditText>(R.id.expdoc_2_input)
        val expdoc3 = findViewById<TextInputEditText>(R.id.expdoc_3_input)
        val expdoc4 = findViewById<TextInputEditText>(R.id.expdoc_4_input)
        val expdoc5 = findViewById<TextInputEditText>(R.id.expdoc_5_input)
        val expdoc6 = findViewById<TextInputEditText>(R.id.expdoc_6_input)
        val expdoc7 = findViewById<TextInputEditText>(R.id.expdoc_7_input)
        val expdoc8 = findViewById<TextInputEditText>(R.id.expdoc_8_input)
        val expdoc9 = findViewById<TextInputEditText>(R.id.expdoc_9_input)
        val expdoc10 = findViewById<TextInputEditText>(R.id.expdoc_10_input)
        val expdoc11 = findViewById<TextInputEditText>(R.id.expdoc_11_input)
        val expdoc12 = findViewById<TextInputEditText>(R.id.expdoc_12_input)
        val expdop_1 = findViewById<TextInputLayout>(R.id.expdop_1)
        val expdop_2 = findViewById<TextInputLayout>(R.id.expdop_2)
        val expdop_3 = findViewById<TextInputLayout>(R.id.expdop_3)
        val expdop_4 = findViewById<TextInputLayout>(R.id.expdop_4)
        val expdop_5 = findViewById<TextInputLayout>(R.id.expdop_5)
        val expdop_6 = findViewById<TextInputLayout>(R.id.expdop_6)
        val expdop_7 = findViewById<TextInputLayout>(R.id.expdop_7)
        val expdop_8 = findViewById<TextInputLayout>(R.id.expdop_8)
        val expdop_9 = findViewById<TextInputLayout>(R.id.expdop_9)
        val expdop_10 = findViewById<TextInputLayout>(R.id.expdop_10)
        val expdop_11 = findViewById<TextInputLayout>(R.id.expdop_11)
        val expdop_12 = findViewById<TextInputLayout>(R.id.expdop_12)
        val expdoc_1 = findViewById<TextInputLayout>(R.id.expdoc_1)
        val expdoc_2 = findViewById<TextInputLayout>(R.id.expdoc_2)
        val expdoc_3 = findViewById<TextInputLayout>(R.id.expdoc_3)
        val expdoc_4 = findViewById<TextInputLayout>(R.id.expdoc_4)
        val expdoc_5 = findViewById<TextInputLayout>(R.id.expdoc_5)
        val expdoc_6 = findViewById<TextInputLayout>(R.id.expdoc_6)
        val expdoc_7 = findViewById<TextInputLayout>(R.id.expdoc_7)
        val expdoc_8 = findViewById<TextInputLayout>(R.id.expdoc_8)
        val expdoc_9 = findViewById<TextInputLayout>(R.id.expdoc_9)
        val expdoc_10 = findViewById<TextInputLayout>(R.id.expdoc_10)
        val expdoc_11 = findViewById<TextInputLayout>(R.id.expdoc_11)
        val expdoc_12 = findViewById<TextInputLayout>(R.id.expdoc_12)
        val top2 = findViewById<TextView>(R.id.top2)
        val top3 = findViewById<TextView>(R.id.top3)

        for(i in amount+1..12){
            when(i){
                2->expdop_2.visibility= View.INVISIBLE
                3->expdop_3.visibility= View.INVISIBLE
                4->expdop_4.visibility= View.INVISIBLE
                5->expdop_5.visibility= View.INVISIBLE
                6->expdop_6.visibility= View.INVISIBLE
                7->expdop_7.visibility= View.INVISIBLE
                8->expdop_8.visibility= View.INVISIBLE
                9->expdop_9.visibility= View.INVISIBLE
                10->expdop_10.visibility= View.INVISIBLE
                11->expdop_11.visibility= View.INVISIBLE
                12->expdop_12.visibility= View.INVISIBLE
            }
        }
        val nextpage: Button = findViewById(R.id.buton1)
        val exported: Button = findViewById(R.id.buton)
        nextpage.setOnClickListener {
            expdop_1.visibility= View.INVISIBLE
            expdop_2.visibility= View.INVISIBLE
            expdop_3.visibility= View.INVISIBLE
            expdop_4.visibility= View.INVISIBLE
            expdop_5.visibility= View.INVISIBLE
            expdop_6.visibility= View.INVISIBLE
            expdop_7.visibility= View.INVISIBLE
            expdop_8.visibility= View.INVISIBLE
            expdop_9.visibility= View.INVISIBLE
            expdop_10.visibility= View.INVISIBLE
            expdop_11.visibility= View.INVISIBLE
            expdop_12.visibility= View.INVISIBLE
            nextpage.visibility= View.INVISIBLE
            top2.visibility= View.INVISIBLE
            for(i in 1..amount){
                when(i){
                    1->expdoc_1.visibility= View.VISIBLE
                    2->expdoc_2.visibility= View.VISIBLE
                    3->expdoc_3.visibility= View.VISIBLE
                    4->expdoc_4.visibility= View.VISIBLE
                    5->expdoc_5.visibility= View.VISIBLE
                    6->expdoc_6.visibility= View.VISIBLE
                    7->expdoc_7.visibility= View.VISIBLE
                    8->expdoc_8.visibility= View.VISIBLE
                    9->expdoc_9.visibility= View.VISIBLE
                    10->expdoc_10.visibility= View.VISIBLE
                    11->expdoc_11.visibility= View.VISIBLE
                    12->expdoc_12.visibility= View.VISIBLE
                }
            }
            top3.visibility= View.VISIBLE
            exported.visibility= View.VISIBLE
        }
        exported.setOnClickListener{
            noexp = Integer.parseInt(noval)
            val marks1=exp_1_input
            val marks2=exp_2_input
            val marks3=exp_3_input
            val marks4=exp_4_input
            val marks5=exp_5_input
            val marks6=exp_6_input
            val marks7=exp_7_input
            val marks8=exp_8_input
            val marks9=exp_9_input
            val marks10=exp_10_input
            val marks11=exp_11_input
            val marks12=exp_12_input
            val famarks1 =fm1
            val famarks2=fm2
            val famarks3=fm3
            val first = kotlin.math.ceil((noexp.toDouble() / 2)).toInt()
            var sub= arrayOfNulls<String?>(12)
            when(selected){
                "AM-IV"->sub = amtuts
                "Design and Analysis of Algorithm"->sub=daaexp
                "Operating System"->sub=osexp
                "Computer Networks"->sub=cnexp
                "Computer Graphics"->sub=cgexp
            }
//            setContentView(R.layout.fmc_pdf_page_2)

            var avg1=0
            var avg2=0
            var count=0
            for(i in 1..first){
                when(i){
                    1->avg1+=Integer.parseInt(marks1)
                    2->avg1+=Integer.parseInt(marks2)
                    3->avg1+=Integer.parseInt(marks3)
                    4->avg1+=Integer.parseInt(marks4)
                    5->avg1+=Integer.parseInt(marks5)
                    6->avg1+=Integer.parseInt(marks6)
                }
                count++
            }
            avg1 = kotlin.math.ceil((avg1.toDouble() / count)).toInt()
            if(avg1<20)avg1=20
            if(avg1%2==1)avg1++
            count = 0
            for(i in (first+1)..noexp){
                when(i){
                    2->avg2+=Integer.parseInt(marks2)
                    3->avg2+=Integer.parseInt(marks3)
                    4->avg2+=Integer.parseInt(marks4)
                    5->avg2+=Integer.parseInt(marks5)
                    6->avg2+=Integer.parseInt(marks6)
                    7->avg2+=Integer.parseInt(marks7)
                    8->avg2+=Integer.parseInt(marks8)
                    9->avg2+=Integer.parseInt(marks9)
                    10->avg2+=Integer.parseInt(marks10)
                    11->avg2+=Integer.parseInt(marks11)
                    12->avg2+=Integer.parseInt(marks12)
                }
                count++
            }
            avg2 = kotlin.math.ceil((avg2.toDouble() / count)).toInt()
            if(avg2<20)avg2=20
            if(avg2%2==1) avg2++

            val state1 = avg1%20
            val level1 = avg1/20
            val state2 = avg2%20
            val level2 = avg2/20

            val score1: IntArray = intArrayOf(level1,level1,level1,level1,level1)
            val score2: IntArray = intArrayOf(level2,level2,level2,level2,level2)
            for (i in 0..state1 step 2)
                when(i){
                    2->score1[2]++
                    4->score1[3]++
                    6->{score1[0]++
                        score1[2]--
                        score1[3]--}
                    8->score1[3]++
                    10->score1[2]++
                    12->{score1[2]--
                        score1[4]++}
                    14->score1[2]++
                    16->{score1[1]++
                        score1[4]--}
                    18->{score1[2]--
                        score1[4]++}
                }
            for (i in 0..state2 step 2)
                when(i){
                    2->score2[2]++
                    4->score2[3]++
                    6->{score2[0]++
                        score2[2]--
                        score2[3]--}
                    8->score2[3]++
                    10->score2[2]++
                    12->{score2[2]--
                        score2[4]++}
                    14->score2[2]++
                    16->{score2[1]++
                        score2[4]--}
                    18->{score2[2]--
                        score2[4]++}
                }
            val intent = Intent(this, FmcExport::class.java)
            intent.putExtra("numExp", noexp)
            intent.putExtra("expdoc1", expdoc1.text.toString())
            intent.putExtra("expdoc2", expdoc2.text.toString())
            intent.putExtra("expdoc3", expdoc3.text.toString())
            intent.putExtra("expdoc4", expdoc4.text.toString())
            intent.putExtra("expdoc5", expdoc5.text.toString())
            intent.putExtra("expdoc6", expdoc6.text.toString())
            intent.putExtra("expdoc7", expdoc7.text.toString())
            intent.putExtra("expdoc8", expdoc8.text.toString())
            intent.putExtra("expdoc9", expdoc9.text.toString())
            intent.putExtra("expdoc10", expdoc10.text.toString())
            intent.putExtra("expdoc11", expdoc11.text.toString())
            intent.putExtra("expdoc12", expdoc12.text.toString())

            intent.putExtra("expdop1", expdop1.text.toString())
            intent.putExtra("expdop2", expdop2.text.toString())
            intent.putExtra("expdop3", expdop3.text.toString())
            intent.putExtra("expdop4", expdop4.text.toString())
            intent.putExtra("expdop5", expdop5.text.toString())
            intent.putExtra("expdop6", expdop6.text.toString())
            intent.putExtra("expdop7", expdop7.text.toString())
            intent.putExtra("expdop8", expdop8.text.toString())
            intent.putExtra("expdop9", expdop9.text.toString())
            intent.putExtra("expdop10", expdop10.text.toString())
            intent.putExtra("expdop11", expdop11.text.toString())
            intent.putExtra("expdop12", expdop12.text.toString())


            intent.putExtra("marks1", marks1)
            intent.putExtra("marks2", marks2)
            intent.putExtra("marks3", marks3)
            intent.putExtra("marks4", marks4)
            intent.putExtra("marks5", marks5)
            intent.putExtra("marks6", marks6)
            intent.putExtra("marks7", marks7)
            intent.putExtra("marks8", marks8)
            intent.putExtra("marks9", marks9)
            intent.putExtra("marks10", marks10)
            intent.putExtra("marks11", marks11)
            intent.putExtra("marks12", marks12)
            intent.putExtra("sub", sub)
            intent.putExtra("score1", score1)
            intent.putExtra("score2", score2)
            intent.putExtra("avg1", avg1)
            intent.putExtra("avg2", avg1)
            intent.putExtra("amount", amount)
            intent.putExtra("first", first)
            startActivity(intent)
        }
    }
}
