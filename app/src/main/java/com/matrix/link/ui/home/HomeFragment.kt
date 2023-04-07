package com.matrix.link.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matrix.link.LinkApplication
import com.matrix.link.R
import com.matrix.link.ui.base.BaseViewModelFactory
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<HomeViewModel>
    private val homeViewModel: HomeViewModel by lazy {
        viewModelFactory.get<HomeViewModel>(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LinkApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }


}