package com.matrix.link.ui.landing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matrix.link.LinkApplication
import com.matrix.link.R
import com.matrix.link.ui.bookmarks.BookmarksFragment
import com.matrix.link.ui.home.HomeFragment
import com.matrix.link.ui.recorder.RecorderFragment
import com.matrix.link.ui.reminder.ReminderFragment
import com.matrix.link.ui.task.TaskFragment

class LandingActivity : AppCompatActivity(), LandingNavigator, BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var bottomNavigationView : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        LinkApplication.getAppComponent().inject(this)

        setUpBottomNavigationView()
        setUpFragments()
    }

    private fun setUpFragments(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,HomeFragment.newInstance(),HomeFragment.javaClass.name)
            .addToBackStack(HomeFragment.javaClass.name)
            .commit()
    }

    private fun setUpBottomNavigationView(){
        bottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        bottomNavigationView.isItemHorizontalTranslationEnabled = false
        bottomNavigationView.labelVisibilityMode = BottomNavigationView.LABEL_VISIBILITY_LABELED
    }

     override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bottom_nav_home ->{
                navigateToHomeFragment()
                item.isChecked = true
                return true
            }
            R.id.bottom_nav_task -> {
                navigateToTaskFragment()
                item.isChecked = true
                return true
            }
            R.id.bottom_nav_link -> {
                navigateToBookmarkFragment()
                item.isChecked = true
                return true
            }
            R.id.bottom_nav_reminder -> {
                navigateToReminderFragment()
                item.isChecked = true
                return true
            }
            R.id.bottom_nav_recorder -> {
                navigateToMeetingsFragment()
                item.isChecked = true
                return true
            }
        }
        return false
    }

    override fun navigateToHomeFragment() {
        val homeFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,homeFragment,
                HomeFragment.javaClass.name)
            .commit()
    }

    override fun navigateToTaskFragment() {
        val taskFragment = TaskFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,taskFragment,
                TaskFragment::class.simpleName)
            .commit()
    }

    override fun navigateToBookmarkFragment() {
        val bookmarksFragment = BookmarksFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,bookmarksFragment,
                BookmarksFragment.javaClass.name)
            .commit()
    }

    override fun navigateToReminderFragment() {
        val reminderFragment = ReminderFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,reminderFragment,
                ReminderFragment.javaClass.name)
            .commit()
    }

    override fun navigateToMeetingsFragment() {
        val meetingFragment = RecorderFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.landing_fragment_container,meetingFragment,
                RecorderFragment.javaClass.name)
            .commit()
    }

}