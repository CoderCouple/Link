package com.matrix.link.ui.onboarding

import android.content.Context
import com.matrix.link.R

class OnBoardingUtility {
    companion object{
        fun getPageList(context: Context): List<OnBoardingPageModel> {
            val list: MutableList<OnBoardingPageModel> = ArrayList<OnBoardingPageModel>()
            list.add(
                OnBoardingPageModel(
                    R.raw.link_task_list,
                    context.getString(R.string.task_slide_title),
                    context.getString(R.string.task_slide_description)
                )
            )
            list.add(
                OnBoardingPageModel(
                    R.raw.link_bookmark,
                    context.getString(R.string.bookmark_slide_title),
                    context.getString(R.string.bookmark_slide_description)
                )
            )
            list.add(
                OnBoardingPageModel(
                    R.raw.link_reminder,
                    context.getString(R.string.reminder_slide_title),
                    context.getString(R.string.reminder_slide_description)
                )
            )
            list.add(
                OnBoardingPageModel(
                    R.raw.link_interactivity,
                    context.getString(R.string.interactivity_slide_title),
                    context.getString(R.string.interactivity_slide_description)
                )
            )
            return list
        }
    }
}