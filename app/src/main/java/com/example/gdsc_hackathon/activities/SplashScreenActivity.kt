package com.example.gdsc_hackathon.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW, WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW)
        val topAnimation= AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val middleAnimation= AnimationUtils.loadAnimation(this, R.anim.middle_animation)
        val first=binding.green
        val second=binding.blue
        val third=binding.greay
        val fourth=binding.red
        val fifth=binding.blue1
        val sixth=binding.green1
        val seventh=binding.greay1
        val title=binding.title
        first.animation = topAnimation
        second.animation = topAnimation
        third.animation = topAnimation
        fourth.animation = topAnimation
        fifth.animation = topAnimation
        sixth.animation = topAnimation
        seventh.animation = topAnimation
        title.animation=middleAnimation
        Handler(Looper.getMainLooper()).postDelayed({
            val startAct= Intent(this, MainActivity::class.java)
            startActivity(startAct)
            finish()
        }, 2000)


    }
}