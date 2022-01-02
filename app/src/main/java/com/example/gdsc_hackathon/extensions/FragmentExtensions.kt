package com.example.gdsc_hackathon.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.gdsc_hackathon.R
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.accessibility.AccessibilityEventCompat.setAction

fun Fragment.closeKeyboard() {
    val inputMethodManager =
        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

fun View.closeKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Fragment.showSnackBar(activity: Activity, message: String?) {
    val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(rootView, message!!, Snackbar.LENGTH_SHORT)
    snackbar.anchorView = activity.findViewById(R.id.bottom_navigation)
    snackbar.show()
}

fun Fragment.showSnackBarWithIntentMessage(activity: Activity, message: String?, actionMessage: String?, sendMessage: String?) {
    val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(rootView, message!!, Snackbar.LENGTH_LONG)
    snackbar.setAction(actionMessage){
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        share.putExtra(Intent.EXTRA_TEXT, sendMessage)
        activity.startActivity(Intent.createChooser(share, "Share Quote!"))
    }
    snackbar.anchorView = activity.findViewById(R.id.bottom_navigation)
    snackbar.show()
}

fun Context.copyToClipboard(text: CharSequence){
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label",text)
    clipboard.setPrimaryClip(clip)
}

fun Fragment.showSnackBarWithAction(activity: Activity, message: String?,@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    val rootView = activity.window.decorView.findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(rootView, message!!, Snackbar.LENGTH_SHORT)
    snackbar.setAction(actionRes, listener)
    snackbar.anchorView = activity.findViewById(R.id.bottom_navigation)
    snackbar.show()
}
fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

