package com.matrix.link.ui.welcome

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.LOLLIPOP
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
            val pair = android.util.Pair<View, String>(findViewById(R.id.btn_sign_up),"sign_up_transition")
            if(SDK_INT >= LOLLIPOP){
                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this@WelcomeActivity,pair)
                startActivity(signUpIntent,activityOptions.toBundle())
            } else{
                startActivity(signUpIntent)
            }
        })
        btnSignIn.setOnClickListener(View.OnClickListener {
            val signInIntent = Intent(this@WelcomeActivity, SignInActivity::class.java)
            val pair = android.util.Pair<View, String>(findViewById(R.id.btn_sign_in),"sign_in_transition")
            if(SDK_INT >= LOLLIPOP){
                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(this@WelcomeActivity,pair)
                startActivity(signInIntent,activityOptions.toBundle())
            } else{
                startActivity(signInIntent)
            }
        })
    }
}