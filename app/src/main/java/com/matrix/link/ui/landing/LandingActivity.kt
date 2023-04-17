package com.matrix.link.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matrix.link.LinkApplication
import com.matrix.link.R
import com.matrix.link.ui.home.HomeFragment

class LandingActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        LinkApplication.getAppComponent().inject(this)

        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        setUpBottomNavigationView()
        setUpFragments()
    }

    private fun setUpFragments(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,HomeFragment.newInstance(),HomeFragment.TAG)
            .addToBackStack(HomeFragment.TAG)
            .commit()
    }

    private fun setUpBottomNavigationView(){
        bottomNavigationView.isItemHorizontalTranslationEnabled = false
        bottomNavigationView.labelVisibilityMode = BottomNavigationView.LABEL_VISIBILITY_LABELED
    }

     override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bottom_nav_home ->{
                item.isChecked = true
                //item.setIcon(R.drawable.ic_bottom_nav_home_filled)
            }
            R.id.bottom_nav_link -> {
                item.isChecked = true
                //item.setIcon(R.drawable.ic_bottom_nav_link_filled)
            }
            R.id.bottom_nav_task -> {
                item.isChecked = true
                //item.setIcon(R.drawable.ic_bottom_nav_task_filled)
            }
            R.id.bottom_nav_reminder -> {
                item.isChecked = true
                //item.setIcon(R.drawable.ic_bottom_nav_task_filled)
            }
            R.id.bottom_nav_account -> {
                item.isChecked = true
                //item.setIcon(R.drawable.ic_bottom_nav_task_filled)
            }
        }
        return false
    }
}