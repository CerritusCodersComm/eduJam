package com.example.gdsc_hackathon.dataModel

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("data", Context.MODE_PRIVATE)

    var status : Int
        get() = preferences.getInt("status", -1)
        set(value) = preferences.edit().putInt("status", value).apply()

    var username: String?
        get() = preferences.getString("username", "anam1")
        set(value) = preferences.edit().putString("username", value).apply()

    var name: String?
        get() = preferences.getString("name", "anam")
        set(value) = preferences.edit().putString("name", value).apply()

    var department: String?
        get()= preferences.getString("department", "comp")
        set(value) = preferences.edit().putString("department", value).apply()

    var uid: String?
        get()= preferences.getString("uid", "1000")
        set(value) = preferences.edit().putString("uid", value).apply()

    var email: String?
        get()= preferences.getString("email", "anamansari062@gmail.com")
        set(value) = preferences.edit().putString("email", value).apply()

    var onBoardingShownStatus: Int
        get() = preferences.getInt("onBoardingShownStatus", -1)
        set(value) = preferences.edit().putInt("onBoardingShownStatus", value).apply()

}