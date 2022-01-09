package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log

import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.text.TextUtils
import android.util.Patterns
import android.widget.TextView
import androidx.appcompat.app.ActionBar

import com.example.gdsc_hackathon.dataModel.Prefs
import com.example.gdsc_hackathon.extensions.hideKeyboard
import com.example.gdsc_hackathon.extensions.openEmailApp
import com.example.gdsc_hackathon.extensions.showSnackBar
import com.example.gdsc_hackathon.extensions.showSnackBarWithAction
import com.example.gdsc_hackathon.utils.NetworkUtils.isNetworkAvailable
import com.example.gdsc_hackathon.utils.hideSoftKeyboard

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.GoogleAuthProvider
import com.royrodriguez.transitionbutton.TransitionButton

class SignInActivity : AppCompatActivity() {
//    private lateinit var googleLoginButton: RelativeLayout
    private lateinit var emailLoginButton: TransitionButton
    private lateinit var registerButton: TransitionButton
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var emailEditTextLayout: TextInputLayout
    private lateinit var passwordEditTextLayout: TextInputLayout
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var forgotPassword: TextView

    companion object {
        private const val RC_SIGN_IN = 120
    }

    private var TAG = "SignIn Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        val actionBar: ActionBar? = supportActionBar
        supportActionBar?.hide()

        registerButton = findViewById(R.id.register)
//        googleLoginButton = findViewById(R.id.google_sign_in_layout)
        emailLoginButton = findViewById(R.id.signin_with_email_button)
        emailEditText = findViewById(R.id.email_edit_text)
        passwordEditText = findViewById(R.id.password_edit_text)
        forgotPassword = findViewById(R.id.forgot_password)

        emailEditTextLayout = findViewById(R.id.emailLayoutSignInScreen)
        passwordEditTextLayout = findViewById(R.id.passwordLayoutSignInScreen)


        // get data if redirected from personal information activity
        var verifyTag:String = ""
        val bundle = intent.extras
        if (bundle != null){
            verifyTag = bundle.getString("activity").toString()
        }
        // show snackbar to open email client apps for verifying mail
        if (verifyTag == "fromPersonalInformationActivity")
        {
            showSnackBarWithAction(
                this,
                "Email verification has been sent", R.string.open_email){
                openEmailApp(this, this@SignInActivity)
            }
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        registerButton.setOnClickListener {
            registerButton.startAnimation()
            val handler = Handler()
            handler.postDelayed({
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.EXPAND
                ) {
                    startActivity(Intent(applicationContext, SignUpActivity::class.java))
                }
            }, 500)
        }

//        googleLoginButton.setOnClickListener {
//            signInWithGoogle()
//        }

        emailLoginButton.setOnClickListener {
            hideSoftKeyboard(this)
            emailEditTextLayout.error = ""
            passwordEditTextLayout.error = ""
            emailLoginButton.startAnimation()

            var isSuccessful = false

            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if(!isNetworkAvailable(this )){
                showSnackBar(this, "No Internet")
                emailLoginButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }
            if (email.isEmpty()) {
                emailEditTextLayout.error = "This field is mandatory"
                emailLoginButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            if (!email.contains("tcetmumbai")) {
                emailEditTextLayout.error = "Please use college email"
                emailLoginButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            if (!isValidEmail(email)) {
                emailEditTextLayout.error = "Please enter a valid email"
                emailLoginButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                passwordEditTextLayout.error = "This field is mandatory"
                emailLoginButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            if (password.length < 8) {
                passwordEditTextLayout.error = "Wrong password"
                emailLoginButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }
            mAuth = FirebaseAuth.getInstance()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { it ->
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        if(!user!!.isEmailVerified){
                            emailEditTextLayout.error = "Please verify the email"
                            emailLoginButton.stopAnimation(
                                TransitionButton.StopAnimationStyle.SHAKE,
                                null
                            )
                            showSnackBarWithAction(
                                this,
                                "Email verification has been sent", R.string.open_email){
                                openEmailApp(this, this@SignInActivity)
                            }
                            mAuth.signOut()
                            return@addOnCompleteListener
                        }
                        Firebase.firestore.collection("users").document(user!!.uid).get()
                            .addOnCompleteListener { task ->
                                val doc = task.result
                                if (doc != null && !doc.exists()) {
                                    emailEditTextLayout.error =
                                        "User does not exist, please sign up"
                                    emailLoginButton.stopAnimation(
                                        TransitionButton.StopAnimationStyle.SHAKE,
                                        null
                                    )
                                    isSuccessful = false
                                    return@addOnCompleteListener
                                }
                                isSuccessful = true
                                val prefs = Prefs(applicationContext)
                                prefs.username = doc.getString("username").toString()
                                prefs.email = doc.getString("email").toString()
                                prefs.department = doc.getString("department").toString()
                                prefs.name = doc.getString("name").toString()
                                prefs.uid = doc.getString("uid").toString()
                                prefs.status = 1
                                emailLoginButton.stopAnimation(
                                    TransitionButton.StopAnimationStyle.EXPAND
                                )
                                {
                                    showSnackBar(this, "Login Successful!")
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                    finish()
                                }
                            }
                            .addOnFailureListener {
                                showSnackBar(this, "Something went wrong, please try again")
                                emailLoginButton.stopAnimation(
                                    TransitionButton.StopAnimationStyle.SHAKE,
                                    null
                                )
                            }
                    }
                    else{
                        showSnackBar(this, "Something went wrong, please try again")
                        emailLoginButton.stopAnimation(
                            TransitionButton.StopAnimationStyle.SHAKE,
                            null
                        )
                    }
                }
                .addOnFailureListener {
                    showSnackBar(this, it.message.toString())
                    emailLoginButton.stopAnimation(
                        TransitionButton.StopAnimationStyle.SHAKE,
                        null
                    )
                }
        }

        forgotPassword.setOnClickListener {

            val email = emailEditText.text.toString().trim()

            if (email.isEmpty() || !isValidEmail(email)) {
                Toast.makeText(applicationContext, "Enter Proper Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Email Sent if Account Exists",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(applicationContext, "Please Try Again", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener {
                    Log.e("LOOK",it.message!!)
                    Toast.makeText(applicationContext, "Please Try Again", Toast.LENGTH_LONG).show()
                }
        }


        val settings = getSharedPreferences("prefs", 0)
        val editor = settings.edit()
        editor.putBoolean("firstRun", false)
        editor.apply()

        val firstRun = settings.getBoolean("firstRun", true)
        Log.d("TAG1", "firstRun: " + java.lang.Boolean.valueOf(firstRun).toString())
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
                        Firebase.firestore.collection("users").document(user!!.uid).get()
                            .addOnCompleteListener { t ->
                                val doc = t.result
                                if (doc != null && !doc.exists()) {
                                    Toast.makeText(
                                        applicationContext,
                                        "User Does Not Exist. Please Signup",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    FirebaseAuth.getInstance().signOut()
//                                        googleSignInClient.signOut()
                                    return@addOnCompleteListener
                                } else if (doc != null && doc.exists()) {
                                    val prefs = Prefs(applicationContext)
                                    prefs.username = doc.getString("username").toString()
                                    prefs.email = doc.getString("email").toString()
                                    prefs.department = doc.getString("department").toString()
                                    prefs.name = doc.getString("name").toString()
                                    prefs.uid = doc.getString("uid").toString()
                                    prefs.status = 1
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please Use College Email",
                        Toast.LENGTH_LONG
                    ).show()

                    googleSignInClient.signOut()
                }
            }
    }


    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
    }
}

 // todo: why are we checking if password is valid on sign in? just say "please enter correct password"
//fun isValidPassword(password: String?): Boolean {
//    val pattern: Pattern
//    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
//    pattern = Pattern.compile(PASSWORD_PATTERN)
//    val matcher: Matcher = pattern.matcher(password)
//    return matcher.matches()
//}
