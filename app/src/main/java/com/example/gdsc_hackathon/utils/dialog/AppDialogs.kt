package com.example.gdsc_hackathon.utils.dialog

import android.view.View
import com.example.gdsc_hackathon.R

sealed class AppDialogs(
  val title: Int?,
  val message: Int?,
  val positiveMessage: Int,
  val negativeMessage: Int?,
  val cancelable: Boolean = true,
  val icon: Int? = null,
  val neutralMessage: Int? = null,
  val getView: (() -> View)? = null
) {
  object DeleteTodo : AppDialogs(
    R.string.delete_todo_title,
    R.string.delete_todo_message,
    android.R.string.yes,
    android.R.string.cancel,
    true,
    R.drawable.ic_baseline_delete_24
  )
  interface HasBodyFormatArgs {
    val args: List<Any>
  }

}
