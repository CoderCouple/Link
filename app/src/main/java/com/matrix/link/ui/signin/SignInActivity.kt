package com.matrix.link.ui.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import com.google.android.material.textfield.TextInputEditText
import com.matrix.link.R
import com.matrix.link.ui.password_reset_flow.PasswordResetFlowActivity

class SignInActivity : AppCompatActivity(), SignInNavigator {

    private lateinit var btnBackArrow: ImageView
    private lateinit var etUserName: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var cbRememberMe: CheckBox
    private lateinit var btnForgotPassword: Button
    private lateinit var btnSignIn: Button
    private lateinit var btnCreateAccount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnBackArrow = findViewById(R.id.sign_in_page_back_btn)
        btnBackArrow.setOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })

        btnForgotPassword = findViewById(R.id.sign_in_forgot_password)
        btnForgotPassword.setOnClickListener {
            navigateForgotPasswordScreen()
        }

    }

    override fun navigateForgotPasswordScreen() {
        val intent = Intent(this, PasswordResetFlowActivity::class.java)
        startActivity(intent)
    }

}