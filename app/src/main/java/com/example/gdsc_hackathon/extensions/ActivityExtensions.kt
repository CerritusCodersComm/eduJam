package com.example.gdsc_hackathon.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.widget.Toast
import android.content.Intent

fun openEmailApp(context: Context, activity:Activity){
    try {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_APP_EMAIL)
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        showSnackBar(activity, "There is no email client installed")
    }
}