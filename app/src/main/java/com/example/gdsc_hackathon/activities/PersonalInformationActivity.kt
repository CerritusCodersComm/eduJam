package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs
import com.example.gdsc_hackathon.extensions.showSnackBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.royrodriguez.transitionbutton.TransitionButton

class PersonalInformationActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var userNameEditText: EditText
    private lateinit var nameLayout: TextInputLayout
    private lateinit var userNameLayout: TextInputLayout
    private lateinit var semesterSpinnerLayout: TextInputLayout
    private lateinit var departmentSpinnerLayout: TextInputLayout
    private lateinit var getStartedButton: TransitionButton
    private lateinit var semesterSpinner: AutoCompleteTextView
    private lateinit var deptSpinner: AutoCompleteTextView
    private var selectedDeptPosition = -1
    private var selectedSemesterPosition = -1

    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private var TAG = "PersonalInformationActivity"

    var name = ""
    private var userName = ""
    private var department = ""
    private var semester = ""

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
        nameLayout = findViewById(R.id.nameLayout)
        userNameEditText = findViewById(R.id.username_edit_text)
        userNameLayout = findViewById(R.id.usernameLayout)
        getStartedButton = findViewById(R.id.get_started_button)
        semesterSpinner = findViewById(R.id.semester_spinner)
        semesterSpinnerLayout = findViewById(R.id.semester_spinner_layout)
        deptSpinner = findViewById(R.id.dept_spinner)
        departmentSpinnerLayout = findViewById(R.id.dept_spinner_layout)

        val departmentArray = arrayOf("Computer Science", "Information Technology", "AI/ML", "AI/DS")

        if (deptSpinner != null) {
            val deptSpinnerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, departmentArray
            )
            deptSpinner.setAdapter(deptSpinnerAdapter)
        }
        deptSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedDeptPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val semesterArray = resources.getStringArray(R.array.semesters_list)
        if (semesterSpinner != null) {
            val semesterSpinnerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, semesterArray
            )
            semesterSpinner.setAdapter(semesterSpinnerAdapter)
        }
        semesterSpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                selectedSemesterPosition = position
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val signupMode = intent.getStringExtra("signupMode")
//        if (signupMode == "GOOGLE") {
//            confirmPasswordEditText.hint = "Have a Nice Day!"
//            confirmPasswordEditText.isEnabled = false
//            confirmPasswordEditText.visibility = View.INVISIBLE
//        }


        getStartedButton.setOnClickListener {
            var isSuccessful = false
            getStartedButton.startAnimation()
            name = nameEditText.text.trim().toString()
            userName = userNameEditText.text.trim().toString()
            department = deptSpinner.text.toString().trim()
            semester = semesterSpinner.text.toString()

            if (name.isEmpty())  {
                nameLayout.error = "This field is mandatory!"
                getStartedButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }
            if(userName.isEmpty()){
                userNameLayout.error = "This field is mandatory!"
                getStartedButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
        }
            if (signupMode == "GOOGLE") {
                signInWithGoogle()
            }
            else {
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
                                "username" to userName,
                                "semester" to semester,
                                "questionsAsked" to 0,
                                "answersSelected" to 0,
                                "questionsReplied" to 0
                            )

                            val prefs = Prefs(applicationContext)
                            prefs.username = userName
                            prefs.email = user.email
                            prefs.department = department
                            prefs.name = name
                            prefs.uid = user.uid
                            prefs.status = 1

                            Firebase.firestore.collection("users").document(user.uid)
                                .set(usr)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Log.w("LOOK", "USER CREATED ON FIREBASE")
                                        mAuth.signInWithEmailAndPassword(email, password)
                                        isSuccessful = true
                                    } else {
                                        showSnackBar(
                                            this,
                                            "Something went wrong, Please try again"
                                        )
                                    }
                                }
                        } else {
                            showSnackBar(
                                this,
                                "Something went wrong, Please try again"
                            )
                        }
                    }
                    .addOnFailureListener {
                        mAuth.signOut()
                    }

            }
            val handler = Handler()
            handler.postDelayed({
                if (isSuccessful) {
                    getStartedButton.stopAnimation(
                        TransitionButton.StopAnimationStyle.EXPAND) {
                        showSnackBar(this, "Registration Successful!")
                        startActivity(
                            Intent(
                                applicationContext,
                                MainActivity::class.java
                            )
                        )
                        finish()
                    }
                } else {
                    showSnackBar(this, "Something went wrong, please try again")
                    getStartedButton.stopAnimation(
                        TransitionButton.StopAnimationStyle.SHAKE,
                        null
                    )
                }
            }, 1000)
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
                            "username" to userName,
                            "semester" to semester,
                            "questionsAsked" to 0,
                            "answersSelected" to 0,
                            "questionsReplied" to 0
                        )

                        val prefs = Prefs(applicationContext)
                        prefs.username = userName
                        prefs.email = user.email
                        prefs.department = department
                        prefs.name = name
                        prefs.uid = user.uid
                        prefs.status = 1

                        Firebase.firestore.collection("users").document(user.uid)
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
                                    showSnackBar(
                                        this,
                                        "Something went wrong, Please try again"
                                        )
                                }
                            }

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        showSnackBar(
                            this,
                            "Please use college email"
                        )

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