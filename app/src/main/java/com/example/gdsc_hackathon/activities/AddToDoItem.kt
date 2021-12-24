package com.example.gdsc_hackathon.activities

import android.Manifest
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.provider.Settings
import android.util.Log
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
import java.io.FileOutputStream



class AddToDoItem : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddToDoItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddToDoItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.submitToDo.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
       when(p0?.id){
           R.id.submit_to_do ->{
               val title : String =  binding.titleInAddTodo.text.toString().trim{it <= ' '}
               val description : String = binding.descriptionInAddTodo.text.toString().trim{it <= ' '}
               Dexter.withContext(this).withPermission(
                   Manifest.permission.READ_EXTERNAL_STORAGE,
               ).withListener(object : PermissionListener {
                   override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                       saveDataInLocalStorage(title,description)
                   }

                   override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                       Toast.makeText(this@AddToDoItem, "Cant able to save in local storage", Toast.LENGTH_LONG).show()

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
    private fun showRationalDialogForPermission(){
        AlertDialog.Builder(this)
            .setMessage("Allowing access of storage")
            .setPositiveButton("GO TO SETTINGS")
            {_,_->
                try{
                    val intent  = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName,null)
                    intent.data = uri
                    startActivity(intent)
                }catch(e: ActivityNotFoundException){
                    e.printStackTrace()
                }
            }
            .setNegativeButton("cancel"){
                    dialog, _->dialog.dismiss()
            }.show()
    }

    private fun saveDataInLocalStorage(title: String, description: String) {
  // val data = mapOf(TITLE  to title,DESCRIPTION to description)
        val fileOutputStream: FileOutputStream

        try {
            val directory = filesDir.absolutePath.toString()
            val file = File(directory , title)
            Log.d("dsds",directory)
            if(file.exists()){
                Toast.makeText(this@AddToDoItem, "File exist change name", Toast.LENGTH_LONG).show()
            }
            else{
                fileOutputStream = openFileOutput(title, Context.MODE_APPEND)
                fileOutputStream.write(description.toByteArray())
                Toast.makeText(this@AddToDoItem, " save data in local storage", Toast.LENGTH_LONG).show()
           finish()
            }

        }catch (e: Exception){
            e.printStackTrace()
        }


    }

    companion  object{
//        private const val TITLE : String = "title"
//        private const val DESCRIPTION : String = "description"
         const val FILE_DIRECTORY : String = "todo_list"


    }

}
