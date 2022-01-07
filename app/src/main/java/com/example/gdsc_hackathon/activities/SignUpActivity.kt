package com.example.gdsc_hackathon.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.gdsc_hackathon.R
import com.example.gdsc_hackathon.dataModel.Prefs
import com.example.gdsc_hackathon.extensions.showSnackBar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Matcher
import java.util.regex.Pattern
import com.royrodriguez.transitionbutton.TransitionButton

// todo create user only in personal activity, let it be sign up with google or simple signing up
class SignUpActivity : AppCompatActivity() {
    private lateinit var registerButton: TransitionButton
    private lateinit var signinButton: TransitionButton
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditTextLayout: TextInputLayout
    private lateinit var confirmPasswordEditTextLayout: TextInputLayout
    private lateinit var emailEditTextLayout: TextInputLayout
    private lateinit var googleSignupButton: RelativeLayout

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_activity)

        val actionBar: ActionBar? = supportActionBar
        supportActionBar?.hide()

        registerButton = findViewById(R.id.register_signup_screen)
        signinButton = findViewById(R.id.signin_with_email_button_signup_screen)

        emailEditText = findViewById(R.id.email_edit_text_signup_screen)
        passwordEditText = findViewById(R.id.password_edit_text_signup_screen)
        confirmPasswordEditText = findViewById(R.id.confirm_password_edit_text_signup_screen)

        emailEditTextLayout = findViewById(R.id.emailLayoutSignUpScreen)
        passwordEditTextLayout = findViewById(R.id.passwordLayoutSignUpScreen)
        confirmPasswordEditTextLayout = findViewById(R.id.confirm_password_layout_sign_up_screen)

        googleSignupButton = findViewById(R.id.signup_with_google_signup_screen)

        mAuth = FirebaseAuth.getInstance()

        val prefs = Prefs(applicationContext)
        val status = prefs.status
        if(status == 1){
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }

        registerButton.setOnClickListener {
            // Start the loading animation when the user tap the button
            registerButton.startAnimation()
            var isSuccessful = false
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (email.isEmpty()) {
                emailEditTextLayout.error = "This field is mandatory"
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }
            if (!isValidEmail(email)) {
                emailEditTextLayout.error = "Please enter correct email"
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
            }

            if (!email.contains("@tcetmumbai.in")) {
                emailEditTextLayout.error = "Please Use College Email"
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }


            if (password.isEmpty() || password.length < 8) {
                passwordEditTextLayout.error = "Password needs to be longer than 8 characters"
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            if (!isValidPassword(password))  {
                passwordEditTextLayout.error = "Password needs to have upper alphabets,smaller alphabets, numbers and characters: Sample Password: Hello@1234"
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            if(password != confirmPassword){
                confirmPasswordEditTextLayout.error = "Passwords don't match!"
                registerButton.stopAnimation(
                    TransitionButton.StopAnimationStyle.SHAKE,
                    null
                )
                return@setOnClickListener
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this
                ) { task ->
                    showSnackBar(this,"User Creation Successful!")
                    if (!task.isSuccessful) {
                        isSuccessful=false
                        showSnackBar(this,"Authentication failed.")

                    } else {
                        registerButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND
                        ) {
                            val intent = Intent(this, PersonalInformationActivity::class.java)
                            intent.putExtra("email",email)
                            intent.putExtra("password",password)
                            mAuth.signOut()
                            startActivity(intent)
                            finish()
                        }
                    }
                }
        }

        signinButton.setOnClickListener {
            signinButton.startAnimation()
            val handler = Handler()
            handler.postDelayed({
                signinButton.stopAnimation(TransitionButton.StopAnimationStyle.EXPAND
                    ) {
                    startActivity(Intent(applicationContext,SignInActivity::class.java))
                    finish()
                    }
            }, 500)
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

    private fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

}