package com.matrix.link.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.matrix.link.R

data class OnBoardingPageModel(val image: Int, val title: String, val description: String)

class OnBoardingPageViewAdapter(pages: List<OnBoardingPageModel>) :
    RecyclerView.Adapter<OnBoardingPageViewAdapter.OnBoardingPageViewHolder>() {
    var pages: List<OnBoardingPageModel>

    init {
        this.pages = pages
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingPageViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_onboarding_view_pager, parent, false)
        return OnBoardingPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnBoardingPageViewHolder, position: Int) {
        holder.setPageView(pages[position])
    }

    override fun getItemCount(): Int {
        return pages.size
    }

    inner class OnBoardingPageViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var viewPagerImage: LottieAnimationView
        var viewPagerTitle: TextView
        var viewPagerDesc: TextView

        init {
            viewPagerImage = itemView.findViewById<LottieAnimationView>(R.id.viewPagerImage)
            viewPagerTitle = itemView.findViewById<TextView>(R.id.viewPagerTitle)
            viewPagerDesc = itemView.findViewById<TextView>(R.id.viewPagerDesc)
        }

        fun setPageView(model: OnBoardingPageModel) {
            viewPagerImage.setAnimation(model.image)
            viewPagerTitle.text = model.title
            viewPagerDesc.text = model.description
        }
    }
}
