package com.example.gdsc_hackathon.activities

import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.text.Editable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.databinding.ActivityAddToDoItemBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import java.io.File


class AddToDoItem : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddToDoItemBinding
    private var isUpdate: Boolean = false
    private var backPressed: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToDoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitToDo.setOnClickListener(this)
        if (intent.extras != null) {
            val getTitle: String? = intent.extras!!.getString(TITLE)?.trim()
            val getDescription: String? = intent.extras!!.getString(DESCRIPTION)?.trim()
            if ((getTitle != null) and (getTitle!!.isNotEmpty())) {
                binding.titleInAddTodo.text = Editable.Factory.getInstance().newEditable(getTitle)
                binding.titleInAddTodo.isEnabled = false
                binding.titleInAddTodo.setTextColor(Color.BLACK)
                binding.relativeLayoutAddTodoList.removeView(binding.addNewTodo)
                binding.submitToDo.text = getString(R.string.to_edit)
                isUpdate = true

            }
            if ((getDescription != null) and (getDescription!!.isNotEmpty())) {
                binding.descriptionInAddTodo.text =
                    Editable.Factory.getInstance().newEditable(getDescription)
                binding.descriptionInAddTodo.isEnabled = false
                binding.descriptionInAddTodo.setTextColor(Color.BLACK)
            }
        }


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.submit_to_do -> {
                val title: String = binding.titleInAddTodo.text.toString().trim { it <= ' ' }
                val description: String =
                    binding.descriptionInAddTodo.text.toString().trim { it <= ' ' }
                Dexter.withContext(this).withPermission(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                ).withListener(object : PermissionListener {
                    override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                        saveDataInLocalStorage(title, description)
                    }

                    override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                        Toast.makeText(
                            this@AddToDoItem,
                            "Cant able to save in local storage",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: PermissionRequest?,
                        p1: PermissionToken?
                    ) {
                        showRationalDialogForPermission()

                    }

                }).onSameThread().check()
            }

        }
    }

    private fun showRationalDialogForPermission() {
        AlertDialog.Builder(this)
            .setMessage("Allowing access of storage")
            .setPositiveButton("GO TO SETTINGS")
            { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            .setNegativeButton("cancel") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun saveDataInLocalStorage(title: String, description: String) {
        if (title.isEmpty()) {
            Toast.makeText(
                this@AddToDoItem,
                "Please write title to your todo list",
                Toast.LENGTH_LONG
            ).show()

        }
        if (description.isEmpty()) {
            Toast.makeText(
                this@AddToDoItem,
                "Please write description to your todo list",
                Toast.LENGTH_LONG
            ).show()
        } else {
            if (isUpdate) {
                isUpdate = false
                binding.titleInAddTodo.isEnabled = true
                binding.descriptionInAddTodo.isEnabled = true
                binding.submitToDo.text = getString(R.string.add_todo)
            } else {
                try {
                    val path = filesDir.absolutePath.toString()
                    val directory = File("$path/$FILE_DIRECTORY")
                    if (!directory.exists()) {
                        directory.mkdir()
                    }
                    val file = File(directory, "$title.txt")

                    if (file.exists()) {
                        AlertDialog.Builder(this)
                            .setMessage("File with same title already exist should We override already existed file")
                            .setPositiveButton("Yes")
                            { _, _ ->
                                file.appendText(description)
                                Toast.makeText(this@AddToDoItem, "Saved file", Toast.LENGTH_LONG)
                                    .show()
                                finish()
                            }
                            .setNegativeButton("Cancel") { dialog, _ ->
                                dialog.dismiss()
                            }.show()

                    } else {
                        file.appendText(description)
                        Toast.makeText(this@AddToDoItem, "Saved file", Toast.LENGTH_LONG)
                            .show()
                        finish()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }


    }

    override fun onBackPressed() {

        val title: String = binding.titleInAddTodo.text.toString().trim { it <= ' ' }
        val description: String = binding.descriptionInAddTodo.text.toString().trim { it <= ' ' }
        if (title.isNotEmpty() or description.isNotEmpty()) {

            if (intent.extras != null) {
                if ((title != intent.extras!!.getString(TITLE)
                        ?.trim()) or (description != intent.extras!!.getString(
                        DESCRIPTION
                    )?.trim())
                ) {

                    if (backPressed) {

                        super.onBackPressed()
                    } else {

                        Toast.makeText(
                            this@AddToDoItem,
                            "File is change. latest content is not saved. Click on save to save the file" +
                                    "Press once again to exit!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    backPressed = true

                } else {

                    super.onBackPressed()
                }

            } else {
                if (backPressed) {

                    super.onBackPressed()
                } else {

                    Toast.makeText(

                        this@AddToDoItem, "File is not saved. Click on save to save the file" +
                                "Press once again to exit!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                backPressed = true

            }

        } else {

            super.onBackPressed()
        }
    }

    companion object {
        const val TITLE: String = "title"
        const val DESCRIPTION: String = "description"
        const val FILE_DIRECTORY: String = "todo_list"


    }

}