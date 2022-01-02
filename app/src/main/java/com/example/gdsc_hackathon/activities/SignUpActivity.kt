package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Matcher
import java.util.regex.Pattern




class SignUpActivity : AppCompatActivity() {
    private lateinit var registerButton: Button
    private lateinit var signinButton: Button
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var googleSignupButton: RelativeLayout

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        registerButton = findViewById(R.id.register_signup_screen)
        signinButton = findViewById(R.id.signin_with_email_button_signup_screen)

        emailEditText = findViewById(R.id.email_edit_text_signup_screen)
        passwordEditText = findViewById(R.id.password_edit_text_signup_screen)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text_signup_screen)

        googleSignupButton = findViewById(R.id.signup_with_google_signup_screen)

        mAuth = FirebaseAuth.getInstance()

        val prefs = Prefs(applicationContext)
        val status = prefs.status
        if(status == 1){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }


        registerButton.setOnClickListener {

            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.toString().trim()

            if (email.isEmpty() || password.isEmpty() || !isValidEmail(email)) {
                Toast.makeText(applicationContext, "Please Enter Correct Values", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
//
//            if (!email.contains("tcet", true) || !email.contains("thakur", true)){
//                Toast.makeText(
//                    applicationContext,
//                    "Please Use College Email",
//                    Toast.LENGTH_LONG
//                ).show()
//                return@setOnClickListener
//                }

//            if(password!=confirmpassword){
//                Toast.makeText(applicationContext, "Passwords Don't Match", Toast.LENGTH_LONG)
//                    .show()
//                return@setOnClickListener
//            }

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this
                ) { task ->
                    Toast.makeText(
                        this,
                        "createUserWithEmail:onComplete:" + task.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Authentication failed." + task.exception,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val intent = Intent(this, PersonalInformationActivity::class.java)

                        intent.putExtra("email",email)
                        intent.putExtra("password",password)
                        mAuth.signOut()
                        startActivity(intent)
                        finish()
                    }
                }
    //            mAuth.createUserWithEmailAndPassword(email, password)
    //                .addOnCompleteListener(this, OnCompleteListener {
    //                        task ->
    //                    Toast.makeText(this,"createUserWithEmail:onComplete"+task.exception,Toast.LENGTH_SHORT).show()
    //                    Log.e("User Sign Up error",task.exception.toString())
    //
    //                    if (!task.isSuccessful){
    ////                    Toast.makeText(this,"User Not created",Toast.LENGTH_SHORT).show()
    //                        return@OnCompleteListener
    //                    }else{
    //                        val intent = Intent(this, PersonalInformationActivity::class.java)
    //
    //                        intent.putExtra("email",email)
    //                        intent.putExtra("password",password)
    //                        mAuth.signOut()
    //                        startActivity(intent)
    //                        finish()
    //                    }
    //                })
    //                .addOnFailureListener { e->
    //                    Log.e("LOOK",e.message.toString())
    //                    if(e.message.toString() == "The email address is already in use by another account."){
    //                        val intent = Intent(this, PersonalInformationActivity::class.java)
    //
    //                        intent.putExtra("email",email)
    //                        intent.putExtra("password",password)
    //                        intent.putExtra("signupMode","EMAIL")
    //                        startActivity(intent)
    //                        finish()
    //                    }
    //                }


//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
        }

        signinButton.setOnClickListener {
            startActivity(Intent(applicationContext,SignInActivity::class.java))
            finish()
        }

        googleSignupButton.setOnClickListener {
            val inten = Intent(applicationContext,PersonalInformationActivity::class.java)
            inten.putExtra("signupMode","GOOGLE")
            startActivity(inten)
            finish()
        }
        
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target!!).matches()
    }

    fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

}