package com.example.gdsc_hackathon.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import androidx.viewpager2.widget.ViewPager2
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.activities.SignInActivity
import com.example.gdsc_hackathon.activities.SignUpActivity
import com.example.gdsc_hackathon.adapters.OnboardingViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.example.gdsc_hackathon.activities.MainActivity
import com.example.gdsc_hackathon.dataModel.Prefs
import com.google.firebase.auth.FirebaseAuth

class OnBoardActivity : AppCompatActivity() {

    private lateinit var mViewPager: ViewPager2
    private lateinit var registerButtonOnBoard: Button
    private lateinit var loginButtonOnBoard: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)

        registerButtonOnBoard = findViewById(R.id.registerButtonOnBoard)
        registerButtonOnBoard.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButtonOnBoard = findViewById(R.id.loginButtonOnBoard)
        loginButtonOnBoard.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)        }

        mViewPager = findViewById(R.id.viewPager)
        mViewPager.adapter = OnboardingViewPagerAdapter(this, this)
        TabLayoutMediator(findViewById(R.id.pageIndicator), mViewPager) { _, _ -> }.attach()
        mViewPager.offscreenPageLimit = 1

        val prefs = Prefs(applicationContext)
        val status = prefs.status
        if(status == 1 && FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }
}
