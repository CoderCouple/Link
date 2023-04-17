package com.matrix.link.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.matrix.link.R
import com.matrix.link.ui.signin.SignInActivity
import com.matrix.link.ui.signup.SignUpActivity

class WelcomeActivity : AppCompatActivity() {
    lateinit var btnSignIn: Button
    lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_welcome)
        btnSignIn = findViewById<Button>(R.id.btn_sign_in)
        btnSignUp = findViewById<Button>(R.id.btn_sign_up)
        btnSignUp.setOnClickListener(View.OnClickListener {
            val signUpIntent = Intent(this@WelcomeActivity, SignUpActivity::class.java)
            startActivity(signUpIntent)
            finish()
        })
        btnSignIn.setOnClickListener(View.OnClickListener {
            val signInIntent = Intent(this@WelcomeActivity, SignInActivity::class.java)
            startActivity(signInIntent)
            finish()
        })
    }
}