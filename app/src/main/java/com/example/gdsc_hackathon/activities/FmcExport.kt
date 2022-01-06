package com.example.gdsc_hackathon.activities

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.extensions.showSnackBar
import com.example.gdsc_hackathon.utils.dialog.AlertDialogShower
import com.example.gdsc_hackathon.utils.dialog.AppDialogs
import com.gkemon.XMLtoPDF.PdfGenerator
import com.gkemon.XMLtoPDF.PdfGenerator.PageSize
import com.gkemon.XMLtoPDF.PdfGeneratorListener
import com.gkemon.XMLtoPDF.model.FailureResponse
import com.gkemon.XMLtoPDF.model.SuccessResponse

class FmcExport : AppCompatActivity() {

    var fileName:String = ""
    var noexp=0

    var noval = ""
    var expdoc1 = ""
    var expdoc2 = ""
    var expdoc3 = ""
    var expdoc4 = ""
    var expdoc5 = ""
    var expdoc6 = ""
    var expdoc7 = ""
    var expdoc8 = ""
    var expdoc9 = ""
    var expdoc10 = ""
    var expdoc11 = ""
    var expdoc12 = ""

    var expdop1 = ""
    var expdop2 = ""
    var expdop3 = ""
    var expdop4 = ""
    var expdop5 = ""
    var expdop6 = ""
    var expdop7 = ""
    var expdop8 = ""
    var expdop9 = ""
    var expdop10 = ""
    var expdop11 = ""
    var expdop12 = ""

    var marks1 = ""
    var marks2 = ""
    var marks3 = ""
    var marks4 = ""
    var marks5 = ""
    var marks6 = ""
    var marks7 = ""
    var marks8 = ""
    var marks9 = ""
    var marks10 = ""
    var marks11 = ""
    var marks12 = ""

    var avg1 = 0
    var avg2 = 0
    var first = 0
    var amount = 0

    var score1:IntArray = intArrayOf(1)
    var score2:IntArray = intArrayOf(1)

    var sub = arrayOfNulls<String?>(12)

    var fm1 = ""
    var fm2 = ""
    var fm3 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fmc_pdf_page_1)

        val bundle = intent.extras
        if (bundle != null) {
            noval = bundle.getString("noval").toString()
            noexp = bundle.getInt("numExp")
            expdoc1 = bundle.getString("expdoc1").toString()
            expdoc2 = bundle.getString("expdoc2").toString()
            expdoc3 = bundle.getString("expdoc3").toString()
            expdoc4 = bundle.getString("expdoc4").toString()
            expdoc5 = bundle.getString("expdoc5").toString()
            expdoc6 = bundle.getString("expdoc6").toString()
            expdoc7 = bundle.getString("expdoc7").toString()
            expdoc8 = bundle.getString("expdoc8").toString()
            expdoc9 = bundle.getString("expdoc9").toString()
            expdoc10 = bundle.getString("expdoc10").toString()
            expdoc11 = bundle.getString("expdoc11").toString()
            expdoc12= bundle.getString("expdoc12").toString()

            expdop1 = bundle.getString("expdop1").toString()
            expdop2 = bundle.getString("expdop2").toString()
            expdop3 = bundle.getString("expdop3").toString()
            expdop4 = bundle.getString("expdop4").toString()
            expdop5 = bundle.getString("expdop5").toString()
            expdop6 = bundle.getString("expdop6").toString()
            expdop7 = bundle.getString("expdop7").toString()
            expdop8 = bundle.getString("expdop8").toString()
            expdop9 = bundle.getString("expdop9").toString()
            expdop10 = bundle.getString("expdop10").toString()
            expdop11 = bundle.getString("expdop11").toString()
            expdop12= bundle.getString("expdop12").toString()

            marks1 = bundle.getString("marks1").toString()
            marks2 = bundle.getString("marks2").toString()
            marks3 = bundle.getString("marks3").toString()
            marks4 = bundle.getString("marks4").toString()
            marks5 = bundle.getString("marks5").toString()
            marks6 = bundle.getString("marks6").toString()
            marks7 = bundle.getString("marks7").toString()
            marks8 = bundle.getString("marks8").toString()
            marks9 = bundle.getString("marks9").toString()
            marks10 = bundle.getString("marks10").toString()
            marks11= bundle.getString("marks11").toString()
            marks12 = bundle.getString("marks12").toString()

            sub = bundle.getSerializable("sub") as Array<String?>
            score1 = bundle.getIntArray("score1")!!
            score2 = bundle.getIntArray("score2")!!
            avg1 = bundle.getInt("avg1")
            avg2 = bundle.getInt("avg2")

            first = bundle.getInt("first")
            amount = bundle.getInt("amount")
        }

        // pdf 1
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

        // pdf 1 calculations
        for (i in 1..noexp) {
            when (i) {
                1 -> {
                    sr1.text = "1"
                    dopexp1.text = expdop1
                    docexp1.text = expdoc1
                    exp1.text = sub[0]
                    amarks1.text = marks1.toString()
                }
                2 -> {
                    sr2.text = "2"
                    dopexp2.text = expdop2
                    docexp2.text = expdoc2
                    exp2.text = sub[1]
                    amarks2.text = marks2.toString()
                }
                3 -> {
                    sr3.text = "3"
                    docexp3.text = expdoc3
                    dopexp3.text = expdop3
                    exp3.text = sub[2]
                    amarks3.text = marks3.toString()
                }
                4 -> {
                    sr4.text = "4"
                    dopexp4.text = expdop4
                    docexp4.text = expdoc4
                    exp4.text = sub[3]
                    amarks4.text = marks4.toString()
                }
                5 -> {
                    sr5.text = "5"
                    dopexp5.text = expdop5
                    docexp5.text = expdoc5
                    exp5.text = sub[4]
                    amarks5.text = marks5.toString()
                }
                6 -> {
                    sr6.text = "6"
                    dopexp6.text = expdop6
                    docexp6.text = expdoc6
                    exp6.text = sub[5]
                    amarks6.text = marks6.toString()
                }
                7 -> {
                    sr7.text = "7"
                    dopexp7.text = expdop7
                    docexp7.text = expdoc7
                    exp7.text = sub[6]
                    amarks7.text = marks7.toString()
                }
                8 -> {
                    sr8.text = "8"
                    dopexp8.text = expdop8
                    docexp8.text = expdoc8
                    exp8.text = sub[7]
                    amarks8.text = marks8.toString()
                }
                9 -> {
                    sr9.text = "9"
                    dopexp9.text = expdop9
                    docexp9.text = expdoc9
                    exp9.text = sub[8]
                    amarks9.text = marks9.toString()
                }
                10 -> {
                    sr10.text = "10"
                    dopexp10.text = expdop10
                    docexp10.text = expdoc10
                    exp10.text = sub[9]
                    amarks10.text = marks10.toString()
                }
                11 -> {
                    sr11.text = "11"
                    dopexp11.text = expdop11
                    docexp11.text = expdoc11
                    amarks11.text = marks11.toString()
                    exp11.text = sub[10]
                    amarks11.text = marks11.toString()
                }
                12 -> {
                    sr12.text = "12"
                    dopexp12.text = expdop12
                    docexp12.text = expdoc12
                    amarks12.text = marks12.toString()
                    exp12.text = sub[11]
                    amarks12.text = marks12.toString()
                }
            }

            // pdf 2 views
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


            // pdf 2 calculations
            when (score1[0]) {
                1 -> TK1.text = "✓"
                2 -> TK2.text = "✓"
                3 -> TK3.text = "✓"
                4 -> TK4.text = "✓"
                5 -> TK5.text = "✓"
            }
            when (score1[1]) {
                1 -> TD1.text = "✓"
                2 -> TD2.text = "✓"
                3 -> TD3.text = "✓"
                4 -> TD4.text = "✓"
                5 -> TD5.text = "✓"
            }
            when (score1[2]) {
                1 -> LI1.text = "✓"
                2 -> LI2.text = "✓"
                3 -> LI3.text = "✓"
                4 -> LI4.text = "✓"
                5 -> LI5.text = "✓"
            }
            when (score1[3]) {
                1 -> B1.text = "✓"
                2 -> B2.text = "✓"
                3 -> B3.text = "✓"
                4 -> B4.text = "✓"
                5 -> B5.text = "✓"
            }
            when (score1[4]) {
                1 -> C1.text = "✓"
                2 -> C2.text = "✓"
                3 -> C3.text = "✓"
                4 -> C4.text = "✓"
                5 -> C5.text = "✓"
            }
            when (score2[0]) {
                1 -> ATK1.text = "✓"
                2 -> ATK2.text = "✓"
                3 -> ATK3.text = "✓"
                4 -> ATK4.text = "✓"
                5 -> ATK5.text = "✓"
            }
            when (score2[1]) {
                1 -> ATD1.text = "✓"
                2 -> ATD2.text = "✓"
                3 -> ATD3.text = "✓"
                4 -> ATD4.text = "✓"
                5 -> ATD5.text = "✓"
            }
            when (score2[2]) {
                1 -> ALI1.text = "✓"
                2 -> ALI2.text = "✓"
                3 -> ALI3.text = "✓"
                4 -> ALI4.text = "✓"
                5 -> ALI5.text = "✓"
            }
            when (score2[3]) {
                1 -> AB1.text = "✓"
                2 -> AB2.text = "✓"
                3 -> AB3.text = "✓"
                4 -> AB4.text = "✓"
                5 -> AB5.text = "✓"
            }
            when (score2[4]) {
                1 -> AC1.text = "✓"
                2 -> AC2.text = "✓"
                3 -> AC3.text = "✓"
                4 -> AC4.text = "✓"
                5 -> AC5.text = "✓"
            }
            cyc1.text = "1-$first"
            val bruh = first + 1
            cyc2.text = "$bruh - $noexp"
            s1tk.text = (score1[0] * 6).toString()
            STK.text = s1tk.text
            s1td.text = (score1[1] * 6).toString()
            STD.text = s1td.text
            s1li.text = (score1[2] * 2).toString()
            SLI.text = s1li.text
            s1b.text = (score1[3] * 2).toString()
            SB.text = s1b.text
            s1c.text = (score1[4] * 4).toString()
            SC.text = s1c.text
            s2tk.text = (score2[0] * 6).toString()
            ASTK.text = s2tk.text
            s2td.text = (score2[1] * 6).toString()
            ASTD.text = s2td.text
            s2li.text = (score2[2] * 2).toString()
            ASLI.text = s2li.text
            s2b.text = (score2[3] * 2).toString()
            ASB.text = s2b.text
            s2c.text = (score2[4] * 4).toString()
            ASC.text = s2c.text
            ttk.text = (score1[0] * 6 + score2[0] * 6).toString()
            ttd.text = (score1[1] * 6 + score2[1] * 6).toString()
            tli.text = (score1[2] * 2 + score2[2] * 2).toString()
            tb.text = (score1[3] * 2 + score2[3] * 2).toString()
            tc.text = (score1[4] * 4 + score2[4] * 4).toString()
            avgtk.text = ((score1[0] * 6 + score2[0] * 6) / 2).toString()
            avgtd.text = ((score1[1] * 6 + score2[1] * 6) / 2).toString()
            avgli.text = ((score1[2] * 2 + score2[2] * 2) / 2).toString()
            avgb.text = ((score1[3] * 2 + score2[3] * 2) / 2).toString()
            avgc.text = ((score1[4] * 4 + score2[4] * 4) / 2).toString()

            afinal.text = avg1.toString()
            bfinal.text = avg2.toString()
            totalc1.text = avg1.toString()
            totalc2.text = avg2.toString()

            ttotal.text = (avg1+avg2).toString()
            avgtotal.text = ((avg1+avg2)/2).toString()
        }
        val view = layoutInflater.inflate(R.layout.custom_edit_text_dialog,null)
        val alertDialogShower = AlertDialogShower(this)

//        alertDialogShower.show(AppDialogs.FmcFilePath { view })

//        alertDialogShower.show(
//            AppDialogs.FmcFilePath
//            { val fileNameEditText = view.findViewById<EditText>(R.id.dialog_EditText)
//              fileName = fileNameEditText.text.toString()
//            },
//            {  view  }
//        )
        // creating PDF
        createPdf(this, fileName)
    }

    private fun createPdf(activity:Activity, fileName:String){
        PdfGenerator.getBuilder()
            .setContext(activity)
            .fromViewIDSource()
            .fromViewID(activity,R.id.page_1,R.id.page_2)
            .setDefaultPageSize(PageSize.A4)
            .setFileName(fileName) /* It is file name
			 * FolderA creates first.Then FolderB inside FolderB and also FolderC inside the FolderB and finally
			 * the pdf file named "Test-PDF.pdf" will be store inside the FolderB. */
            .openPDFafterGeneration(true)
            /* It true then the generated pdf will be shown after generated. */
            .build(object : PdfGeneratorListener() {
                override fun onFailure(failureResponse: FailureResponse) {
                    super.onFailure(failureResponse)
                    showSnackBar(activity, "$failureResponse")
                    /* If pdf is not generated by an error then you will findout the reason behind it
				 * from this FailureResponse. */
                }

                override fun onStartPDFGeneration() {
                    showSnackBar(activity, "started")
                    /*When PDF generation begins to start*/
                }

                override fun onFinishPDFGeneration() {
                    showSnackBar(activity, "finished")
                    /*When PDF generation is finished*/
                }

                override fun showLog(log: String) {
                    super.showLog(log)
//                    showSnackBar(this@FmcExport, "$log")

                    /*It shows logs of events inside the pdf generation process*/
                }

                override fun onSuccess(response: SuccessResponse) {
                    super.onSuccess(response)
                    showSnackBar(this@FmcExport, "success")

                    /* If PDF is generated successfully then you will find SuccessResponse
				 * which holds the PdfDocument,File and path (where generated pdf is stored)*/
                }
            })
    }
}