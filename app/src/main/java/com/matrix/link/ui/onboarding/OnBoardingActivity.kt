package com.matrix.link.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.matrix.link.R
import com.matrix.link.ui.landing.LandingActivity
import com.matrix.link.ui.welcome.WelcomeActivity

class OnBoardingActivity : AppCompatActivity(), OnBoardingNavigator {
    lateinit var pages: List<OnBoardingPageModel>
    lateinit var onBoardingViewPager: ViewPager2
    lateinit var onBoardingPageViewAdapter: OnBoardingPageViewAdapter
    lateinit var indicatorLayout: LinearLayout
    lateinit var  btnNext: Button
    lateinit var  btnLetsGetStarted: Button
    lateinit var  btnSkip: Button
    lateinit var  animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        pages = OnBoardingUtility.getPageList(applicationContext)

        onBoardingPageViewAdapter = OnBoardingPageViewAdapter(pages)
        onBoardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        indicatorLayout = findViewById(R.id.indicatorLayout)
        btnNext = findViewById(R.id.btnNext)
        btnLetsGetStarted = findViewById(R.id.btnLetsGetStarted)
        btnSkip = findViewById(R.id.btnSkip)

        onBoardingViewPager.setAdapter(onBoardingPageViewAdapter)
        onBoardingViewPager.setOffscreenPageLimit(2)

        setupIndicators()
        setupActiveIndicators(0)

        onBoardingViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setupActiveIndicators(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == 3) {
                    animation = AnimationUtils.loadAnimation(
                        this@OnBoardingActivity,
                        R.anim.onboarding_button_animation
                    )
                    btnLetsGetStarted.animation = animation
                    btnLetsGetStarted.visibility = View.VISIBLE
                } else {
                    btnLetsGetStarted.visibility = View.INVISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })


        btnNext.setOnClickListener {
            if (onBoardingViewPager.getCurrentItem() + 1 < pages.size) {
                onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1)
            } else {
                navigateToWelcomePage()
                finish()
            }
        }

        btnSkip.setOnClickListener {
            navigateToWelcomePage()
            finish()
        }


        btnLetsGetStarted.setOnClickListener {
            navigateToWelcomePage()
            finish()
        }
    }


    fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(pages.size)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 0, 8, 0)
        for (i in pages.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.onboarding_indicator_inactive
                )
            )
            indicators[i]!!.layoutParams = params
            indicatorLayout.addView(indicators[i])
        }
    }


    fun setupActiveIndicators(childIndex: Int) {
        val childCount = indicatorLayout.childCount
        for (i in 0 until childCount) {
            val indicator = indicatorLayout.getChildAt(i) as ImageView
            if (childIndex == i) {
                indicator.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                indicator.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.onboarding_indicator_inactive
                    )
                )
            }
        }
    }

    override fun navigateToWelcomePage() {
        startActivity(Intent(applicationContext, WelcomeActivity::class.java))
    }
}