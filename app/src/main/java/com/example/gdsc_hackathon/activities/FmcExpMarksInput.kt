package com.example.gdsc_hackathon.activities

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
            setContentView(R.layout.fmc_pdf_page_2)
            val TK5 = findViewById<TextView>(R.id.TK5)
            val TK4 = findViewById<TextView>(R.id.TK4)
            val TK3 = findViewById<TextView>(R.id.TK3)
            val TK2 = findViewById<TextView>(R.id.TK2)
            val TK1 = findViewById<TextView>(R.id.TK1)
            val TD1 = findViewById<TextView>(R.id.TD1)
            val TD2 = findViewById<TextView>(R.id.TD2)
            val TD3 = findViewById<TextView>(R.id.TD3)
            val TD4 = findViewById<TextView>(R.id.TD4)
            val TD5 = findViewById<TextView>(R.id.TD5)
            val LI5 = findViewById<TextView>(R.id.LI5)
            val LI4 = findViewById<TextView>(R.id.LI4)
            val LI3 = findViewById<TextView>(R.id.LI3)
            val LI2 = findViewById<TextView>(R.id.LI2)
            val LI1 = findViewById<TextView>(R.id.LI1)
            val B5 = findViewById<TextView>(R.id.B5)
            val B4 = findViewById<TextView>(R.id.B4)
            val B3 = findViewById<TextView>(R.id.B3)
            val B2 = findViewById<TextView>(R.id.B2)
            val B1 = findViewById<TextView>(R.id.B1)
            val C1 = findViewById<TextView>(R.id.C1)
            val C2 = findViewById<TextView>(R.id.C2)
            val C3 = findViewById<TextView>(R.id.C3)
            val C4 = findViewById<TextView>(R.id.C4)
            val C5 = findViewById<TextView>(R.id.C5)
            val ATK5 = findViewById<TextView>(R.id.ATK5)
            val ATK4 = findViewById<TextView>(R.id.ATK4)
            val ATK3 = findViewById<TextView>(R.id.ATK3)
            val ATK2 = findViewById<TextView>(R.id.ATK2)
            val ATK1 = findViewById<TextView>(R.id.ATK1)
            val ATD1 = findViewById<TextView>(R.id.ATD1)
            val ATD2 = findViewById<TextView>(R.id.ATD2)
            val ATD3 = findViewById<TextView>(R.id.ATD3)
            val ATD4 = findViewById<TextView>(R.id.ATD4)
            val ATD5 = findViewById<TextView>(R.id.ATD5)
            val ALI5 = findViewById<TextView>(R.id.ALI5)
            val ALI4 = findViewById<TextView>(R.id.ALI4)
            val ALI3 = findViewById<TextView>(R.id.ALI3)
            val ALI2 = findViewById<TextView>(R.id.ALI2)
            val ALI1 = findViewById<TextView>(R.id.ALI1)
            val AB5 = findViewById<TextView>(R.id.AB5)
            val AB4 = findViewById<TextView>(R.id.AB4)
            val AB3 = findViewById<TextView>(R.id.AB3)
            val AB2 = findViewById<TextView>(R.id.AB2)
            val AB1 = findViewById<TextView>(R.id.AB1)
            val AC1 = findViewById<TextView>(R.id.AC1)
            val AC2 = findViewById<TextView>(R.id.AC2)
            val AC3 = findViewById<TextView>(R.id.AC3)
            val AC4 = findViewById<TextView>(R.id.AC4)
            val AC5 = findViewById<TextView>(R.id.AC5)
            val STK = findViewById<TextView>(R.id.STK)
            val STD = findViewById<TextView>(R.id.STD)
            val SLI = findViewById<TextView>(R.id.SLI)
            val SB = findViewById<TextView>(R.id.BS)
            val SC = findViewById<TextView>(R.id.CS)
            val ASTK = findViewById<TextView>(R.id.ASTK)
            val ASTD = findViewById<TextView>(R.id.ASTD)
            val ASLI = findViewById<TextView>(R.id.ASLI)
            val ASB = findViewById<TextView>(R.id.ABS)
            val ASC = findViewById<TextView>(R.id.ACS)
            val afinal = findViewById<TextView>(R.id.afinal)
            val bfinal = findViewById<TextView>(R.id.bfinal)
            val totalc1 = findViewById<TextView>(R.id.totalc1)
            val totalc2 = findViewById<TextView>(R.id.totalc2)
            val ttotal = findViewById<TextView>(R.id.ttotal)
            val avgtotal = findViewById<TextView>(R.id.avgtotal)
            val s1tk = findViewById<TextView>(R.id.s1tk)
            val s1td = findViewById<TextView>(R.id.s1td)
            val s1li = findViewById<TextView>(R.id.s1li)
            val s1b = findViewById<TextView>(R.id.s1b)
            val s1c = findViewById<TextView>(R.id.s1c)
            val s2tk = findViewById<TextView>(R.id.s2tk)
            val s2td = findViewById<TextView>(R.id.s2td)
            val s2li = findViewById<TextView>(R.id.s2li)
            val s2b = findViewById<TextView>(R.id.s2b)
            val s2c = findViewById<TextView>(R.id.s2c)
            val ttk = findViewById<TextView>(R.id.ttk)
            val ttd = findViewById<TextView>(R.id.ttd)
            val tli = findViewById<TextView>(R.id.tli)
            val tb = findViewById<TextView>(R.id.tb)
            val tc = findViewById<TextView>(R.id.tc)
            val avgtk = findViewById<TextView>(R.id.avgtk)
            val avgtd = findViewById<TextView>(R.id.avgtd)
            val avgli = findViewById<TextView>(R.id.avgli)
            val avgb = findViewById<TextView>(R.id.avgb)
            val avgc = findViewById<TextView>(R.id.avgc)
            val cyc1 = findViewById<TextView>(R.id.cyc1)
            val cyc2 = findViewById<TextView>(R.id.cyc2)
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
            afinal.text = avg1.toString()
            bfinal.text = avg2.toString()
            totalc1.text = avg1.toString()
            totalc2.text = avg2.toString()
            val state1 = avg1%20
            val level1 = avg1/20
            val state2 = avg2%20
            val level2 = avg2/20
            ttotal.text = (avg1+avg2).toString()
            avgtotal.text = ((avg1+avg2)/2).toString()
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
            when(score1[0]){
                1->TK1.text= (2714).toChar().toString()
                2->TK2.text= (2714).toChar().toString()
                3->TK3.text= (2714).toChar().toString()
                4->TK4.text= (2714).toChar().toString()
                5->TK5.text= (2714).toChar().toString()
            }
            when(score1[1]){
                1->TD1.text= (2714).toChar().toString()
                2->TD2.text= (2714).toChar().toString()
                3->TD3.text= (2714).toChar().toString()
                4->TD4.text= (2714).toChar().toString()
                5->TD5.text= (2714).toChar().toString()
            }
            when(score1[2]){
                1->LI1.text= (2714).toChar().toString()
                2->LI2.text= (2714).toChar().toString()
                3->LI3.text= (2714).toChar().toString()
                4->LI4.text= (2714).toChar().toString()
                5->LI5.text= (2714).toChar().toString()
            }
            when(score1[3]){
                1->B1.text= (2714).toChar().toString()
                2->B2.text= (2714).toChar().toString()
                3->B3.text= (2714).toChar().toString()
                4->B4.text= (2714).toChar().toString()
                5->B5.text= (2714).toChar().toString()
            }
            when(score1[4]){
                1->C1.text= (2714).toChar().toString()
                2->C2.text= (2714).toChar().toString()
                3->C3.text= (2714).toChar().toString()
                4->C4.text= (2714).toChar().toString()
                5->C5.text= (2714).toChar().toString()
            }
            when(score2[0]){
                1->ATK1.text= (2714).toChar().toString()
                2->ATK2.text= (2714).toChar().toString()
                3->ATK3.text= (2714).toChar().toString()
                4->ATK4.text= (2714).toChar().toString()
                5->ATK5.text= (2714).toChar().toString()
            }
            when(score2[1]){
                1->ATD1.text= (2714).toChar().toString()
                2->ATD2.text= (2714).toChar().toString()
                3->ATD3.text= (2714).toChar().toString()
                4->ATD4.text= (2714).toChar().toString()
                5->ATD5.text= (2714).toChar().toString()
            }
            when(score2[2]){
                1->ALI1.text= (2714).toChar().toString()
                2->ALI2.text= (2714).toChar().toString()
                3->ALI3.text= (2714).toChar().toString()
                4->ALI4.text= (2714).toChar().toString()
                5->ALI5.text= (2714).toChar().toString()
            }
            when(score2[3]){
                1->AB1.text= (2714).toChar().toString()
                2->AB2.text= (2714).toChar().toString()
                3->AB3.text= (2714).toChar().toString()
                4->AB4.text= (2714).toChar().toString()
                5->AB5.text= (2714).toChar().toString()
            }
            when(score2[4]){
                1->AC1.text= (2714).toChar().toString()
                2->AC2.text= (2714).toChar().toString()
                3->AC3.text= (2714).toChar().toString()
                4->AC4.text= (2714).toChar().toString()
                5->AC5.text= (2714).toChar().toString()
            }
            cyc1.text = "1-$first"
            val bruh = first+1
            cyc2.text = "$bruh - $noexp"
            s1tk.text = (score1[0]*6).toString()
            STK.text = s1tk.text
            s1td.text = (score1[1]*6).toString()
            STD.text = s1td.text
            s1li.text = (score1[2]*2).toString()
            SLI.text = s1li.text
            s1b.text = (score1[3]*2).toString()
            SB.text = s1b.text
            s1c.text = (score1[4]*4).toString()
            SC.text = s1c.text
            s2tk.text = (score2[0]*6).toString()
            ASTK.text = s2tk.text
            s2td.text = (score2[1]*6).toString()
            ASTD.text = s2td.text
            s2li.text = (score2[2]*2).toString()
            ASLI.text = s2li.text
            s2b.text = (score2[3]*2).toString()
            ASB.text = s2b.text
            s2c.text = (score2[4]*4).toString()
            ASC.text = s2c.text
            ttk.text = (score1[0]*6+score2[0]*6).toString()
            ttd.text = (score1[1]*6+score2[1]*6).toString()
            tli.text = (score1[2]*2+score2[2]*2).toString()
            tb.text = (score1[3]*2+score2[3]*2).toString()
            tc.text = (score1[4]*4+score2[4]*4).toString()
            avgtk.text = ((score1[0]*6+score2[0]*6)/2).toString()
            avgtd.text = ((score1[1]*6+score2[1]*6)/2).toString()
            avgli.text = ((score1[2]*2+score2[2]*2)/2).toString()
            avgb.text = ((score1[3]*2+score2[3]*2)/2).toString()
            avgc.text = ((score1[4]*4+score2[4]*4)/2).toString()
            setContentView(R.layout.fmc_pdf_page_1)
            val sr1 = findViewById<TextView>(R.id.sr1)
            val sr2 = findViewById<TextView>(R.id.sr2)
            val sr3 = findViewById<TextView>(R.id.sr3)
            val sr4 = findViewById<TextView>(R.id.sr4)
            val sr5 = findViewById<TextView>(R.id.sr5)
            val sr6 = findViewById<TextView>(R.id.sr6)
            val sr7 = findViewById<TextView>(R.id.sr7)
            val sr8 = findViewById<TextView>(R.id.sr8)
            val sr9 = findViewById<TextView>(R.id.sr9)
            val sr10 = findViewById<TextView>(R.id.sr10)
            val sr11 = findViewById<TextView>(R.id.sr11)
            val sr12 = findViewById<TextView>(R.id.sr12)
            val exp1 = findViewById<TextView>(R.id.aexp1)
            val exp2 = findViewById<TextView>(R.id.aexp2)
            val exp3 = findViewById<TextView>(R.id.aexp3)
            val exp4 = findViewById<TextView>(R.id.aexp4)
            val exp5 = findViewById<TextView>(R.id.aexp5)
            val exp6 = findViewById<TextView>(R.id.aexp6)
            val exp7 = findViewById<TextView>(R.id.aexp7)
            val exp8 = findViewById<TextView>(R.id.aexp8)
            val exp9 = findViewById<TextView>(R.id.aexp9)
            val exp10 = findViewById<TextView>(R.id.aexp10)
            val exp11 = findViewById<TextView>(R.id.aexp11)
            val exp12 = findViewById<TextView>(R.id.aexp12)
            val amarks1 = findViewById<TextView>(R.id.marks1)
            val amarks2 = findViewById<TextView>(R.id.marks2)
            val amarks3 = findViewById<TextView>(R.id.marks3)
            val amarks4 = findViewById<TextView>(R.id.marks4)
            val amarks5 = findViewById<TextView>(R.id.marks5)
            val amarks6 = findViewById<TextView>(R.id.marks6)
            val amarks7 = findViewById<TextView>(R.id.marks7)
            val amarks8 = findViewById<TextView>(R.id.marks8)
            val amarks9 = findViewById<TextView>(R.id.marks9)
            val amarks10 = findViewById<TextView>(R.id.marks10)
            val amarks11 = findViewById<TextView>(R.id.marks11)
            val amarks12 = findViewById<TextView>(R.id.marks12)
            val dopexp1 = findViewById<TextView>(R.id.expdop1)
            val dopexp2 = findViewById<TextView>(R.id.expdop2)
            val dopexp3 = findViewById<TextView>(R.id.expdop3)
            val dopexp4 = findViewById<TextView>(R.id.expdop4)
            val dopexp5 = findViewById<TextView>(R.id.expdop5)
            val dopexp6 = findViewById<TextView>(R.id.expdop6)
            val dopexp7 = findViewById<TextView>(R.id.expdop7)
            val dopexp8 = findViewById<TextView>(R.id.expdop8)
            val dopexp9 = findViewById<TextView>(R.id.expdop9)
            val dopexp10 = findViewById<TextView>(R.id.expdop10)
            val dopexp11 = findViewById<TextView>(R.id.expdop11)
            val dopexp12 = findViewById<TextView>(R.id.expdop12)
            val docexp1 = findViewById<TextView>(R.id.expdoc1)
            val docexp2 = findViewById<TextView>(R.id.expdoc2)
            val docexp3 = findViewById<TextView>(R.id.expdoc3)
            val docexp4 = findViewById<TextView>(R.id.expdoc4)
            val docexp5 = findViewById<TextView>(R.id.expdoc5)
            val docexp6 = findViewById<TextView>(R.id.expdoc6)
            val docexp7 = findViewById<TextView>(R.id.expdoc7)
            val docexp8 = findViewById<TextView>(R.id.expdoc8)
            val docexp9 = findViewById<TextView>(R.id.expdoc9)
            val docexp10 = findViewById<TextView>(R.id.expdoc10)
            val docexp11 = findViewById<TextView>(R.id.expdoc11)
            val docexp12 = findViewById<TextView>(R.id.expdoc12)
            for(i in 1..noexp){
                when(i){
                    1-> {
                        sr1.text = "1"
                        dopexp1.text = expdop1.text
                        docexp1.text = expdoc1.text
                        exp1.text = sub[0]
                        amarks1.text = marks1
                    }
                    2->{
                        sr2.text = "2"
                        dopexp2.text = expdop2.text
                        docexp2.text = expdoc2.text
                        exp2.text = sub[1]
                        amarks2.text = marks2
                    }
                    3->{
                        sr3.text = "3"
                        docexp3.text = expdoc3.text
                        dopexp3.text = expdop3.text
                        exp3.text = sub[2]
                        amarks3.text = marks3
                    }
                    4->{
                        sr4.text = "4"
                        dopexp4.text = expdop4.text
                        docexp4.text = expdoc4.text
                        exp4.text = sub[3]
                        amarks4.text = marks4
                    }
                    5->{
                        sr5.text = "5"
                        dopexp5.text = expdop5.text
                        docexp5.text = expdoc5.text
                        exp5.text = sub[4]
                        amarks5.text = marks5
                    }
                    6->{
                        sr6.text = "6"
                        dopexp6.text = expdop6.text
                        docexp6.text = expdoc6.text
                        exp6.text = sub[5]
                        amarks6.text = marks6
                    }
                    7->{
                        sr7.text = "7"
                        dopexp7.text = expdop7.text
                        docexp7.text = expdoc7.text
                        exp7.text = sub[6]
                        amarks7.text = marks7
                    }
                    8->{
                        sr8.text = "8"
                        dopexp8.text = expdop8.text
                        docexp8.text = expdoc8.text
                        exp8.text = sub[7]
                        amarks8.text = marks8
                    }
                    9->{
                        sr9.text = "9"
                        dopexp9.text = expdop9.text
                        docexp9.text = expdoc9.text
                        exp9.text = sub[8]
                        amarks9.text = marks9
                    }
                    10->{
                        sr10.text = "10"
                        dopexp10.text = expdop10.text
                        docexp10.text = expdoc10.text
                        exp10.text = sub[9]
                        amarks10.text = marks10
                    }
                    11->{
                        sr11.text = "11"
                        dopexp11.text = expdop11.text
                        docexp11.text = expdoc11.text
                        amarks11.text = marks11
                        exp11.text = sub[10]
                        amarks11.text = marks11
                    }
                    12->{
                        sr12.text = "12"
                        dopexp12.text = expdop12.text
                        docexp12.text = expdoc12.text
                        amarks12.text = marks12
                        exp12.text = sub[11]
                        amarks12.text = marks12
                    }
                }
            }
        }
    }
}