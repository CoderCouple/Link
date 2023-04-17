package com.matrix.link.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import com.matrix.link.R
import com.matrix.link.ui.landing.LandingActivity
import com.matrix.link.ui.onboarding.OnBoardingActivity
import com.matrix.link.ui.welcome.WelcomeActivity

//https://proandroiddev.com/splash-screen-in-android-3bd9552b92a5
class SplashActivity : AppCompatActivity(), SplashNavigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //for making the android window full screen
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        //Add manual delay and open a new activity
        Handler().postDelayed(Runnable {
            navigateToOnBoardingActivity()
            finish()
        }, 1500)
    }

    override fun navigateToOnBoardingActivity() {
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
    }

    override fun navigateToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }
}