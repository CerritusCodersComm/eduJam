package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PersonalInformationActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var userNameEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var getStartedButton: Button
    private var selectedPosition = -1

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private var TAG = "LOOK"

    var name = ""
    private var userName = ""
    private var confirmPassword = ""
    private var department = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_information_activity)

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        nameEditText = findViewById(R.id.name_edit_textw)
        userNameEditText = findViewById(R.id.username_edit_text)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text_personalinfo_screen)
        getStartedButton = findViewById(R.id.get_started_button)

        confirmPasswordEditText.visibility = View.INVISIBLE
        findViewById<TextInputLayout>(R.id.confirm_password_layout).visibility = View.INVISIBLE

        val arrayname = arrayOf("Computer Science", "Information Technology", "AI/ML", "AI/DS")

        val spinner = findViewById<AutoCompleteTextView>(R.id.dept_spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, arrayname
            )
            spinner.setAdapter(adapter)
        }
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedPosition = position
                Toast.makeText(applicationContext, arrayname[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val signupMode = intent.getStringExtra("signupMode")
        if (signupMode == "GOOGLE") {
            confirmPasswordEditText.hint = "Have a Nice Day!"
            confirmPasswordEditText.isEnabled = false
            confirmPasswordEditText.visibility = View.INVISIBLE
        }


        getStartedButton.setOnClickListener {
            name = nameEditText.text.trim().toString()
            userName = userNameEditText.text.trim().toString()
            department = "Information Technology"

            if (signupMode == "GOOGLE") {

                if (name.isEmpty() || userName.isEmpty()) {
                    Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                signInWithGoogle()

            }
            else {
                if (name.isEmpty() || userName.isEmpty()) {
                    Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val email = intent.getStringExtra("email")
                val password = intent.getStringExtra("password")


                mAuth.signInWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener {

                        val user = mAuth.currentUser

                        if (user != null) {

                            val usr = hashMapOf(
                                "uid" to user.uid,
                                "name" to name,
                                "email" to user.email,
                                "department" to department,
                                "username" to userName
                            )


                            Firebase.firestore.collection("users").document(user.email!!)
                                .set(usr)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Log.w("LOOK", "USER CREATED ON FIREBASE")
                                        mAuth.signInWithEmailAndPassword(email, password)
                                        startActivity(
                                            Intent(
                                                applicationContext,
                                                MainActivity::class.java
                                            )
                                        )
                                        finish()
                                    } else {
                                        Toast.makeText(
                                            applicationContext,
                                            "Please try again",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                    .addOnFailureListener {
                        mAuth.signOut()
                    }

            }
        }

    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception

            if (task.isSuccessful) {
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w(TAG, "Google sign in failed", e)
                }
            } else {
                Log.w(TAG, exception.toString())
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = mAuth.currentUser

                    if (user?.email!!.contains("tcet", true) || user.email!!.contains(
                            "thakur",
                            true
                        )
                    ) {

                        val usr = hashMapOf(
                            "uid" to user.uid,
                            "name" to name,
                            "email" to user.email,
                            "department" to department,
                            "username" to userName
                        )


                        Firebase.firestore.collection("users").document(user.email!!)
                            .set(usr)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    Log.w("LOOK", "USER CREATED ON FIREBASE")
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                } else {
                                    Toast.makeText(
                                        applicationContext,
                                        "Please try again",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Please Use College Email",
                            Toast.LENGTH_LONG
                        ).show()

                        googleSignInClient.signOut()
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
//                    updateUI(null)
                }
            }
    }

}