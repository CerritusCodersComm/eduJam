package com.example.gdsc_hackathon.fragments

import android.os.Bundle
import com.example.gdsc_hackathon.R
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences
import android.view.LayoutInflater
import com.example.gdsc_hackathon.utils.SettingsPreferences
import android.view.View
import android.view.ViewGroup

import androidx.preference.*

import androidx.core.content.ContextCompat





class SettingsFragment : PreferenceFragmentCompat() {

    private val themeSetting: SettingsPreferences? = null
    private lateinit var  themeSwitch: Preference
    private lateinit var  rootView: View
    private lateinit var  sharedPreferences: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference_settings)

        // find all preferences
        sharedPreferences =
            requireContext().getSharedPreferences(SettingsPreferences.PREFERENCES, MODE_PRIVATE)

        loadSharedPreferences()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = super.onCreateView(inflater, container, savedInstanceState)!!

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // find all preferences
        val email = findPreference("acra.user.email") as EditTextPreference?

        themeSwitch = (findPreference(getString(R.string.pref_dark_theme_key)) as SwitchPreference?)!!
        // set preference change listener
        themeSwitch.setOnPreferenceChangeListener { _: Preference?, newValue: Any ->
            themeSwitchListener(newValue as Boolean)
            themeSwitch.isVisible = newValue
            true
        }
    }

    private fun loadSharedPreferences() {
        val theme = sharedPreferences.getString(SettingsPreferences.CUSTOM_THEME, SettingsPreferences.LIGHT_THEME)
        themeSetting?.setCustomTheme(theme)
    }

    private fun themeSwitchListener(bool: Boolean) {

        if(bool) themeSetting?.setCustomTheme(SettingsPreferences.DARK_THEME)
        else themeSetting?.setCustomTheme(SettingsPreferences.LIGHT_THEME)

            val editor: SharedPreferences.Editor =
                requireContext().getSharedPreferences(SettingsPreferences.PREFERENCES, MODE_PRIVATE).edit()
            editor.putString(SettingsPreferences.CUSTOM_THEME, themeSetting?.getCustomTheme())
            editor.apply()
            updateView()
    }

    private fun updateView() {
        val black = ContextCompat.getColor(requireContext(), R.color.background_floating_material_dark)
        val white = ContextCompat.getColor(requireContext(), R.color.switch_thumb_disabled_material_light)
        if (themeSetting?.getCustomTheme().equals(SettingsPreferences.DARK_THEME)) {
            rootView.setBackgroundColor(black)
        } else {
            rootView.setBackgroundColor(white)
        }
    }
}