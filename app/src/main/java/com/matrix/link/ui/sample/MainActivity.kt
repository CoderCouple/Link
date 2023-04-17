package com.matrix.link.ui.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.matrix.link.LinkApplication
import com.matrix.link.R
import com.matrix.link.network.model.Post
import com.matrix.link.ui.base.BaseViewModelFactory
import java.util.ArrayList
import javax.inject.Inject

class MainActivity() : AppCompatActivity(), PostNavigator {

    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<PostViewModel>
    private val postViewModel: PostViewModel by lazy {
        viewModelFactory.get<PostViewModel>(this)
    }

    private lateinit var swipeRefreshLayout : SwipeRefreshLayout
    private lateinit var rcv: RecyclerView
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        LinkApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
        setUpRecyclerView()
        setUpSwipeRefreshLayout()
    }


    fun setUpRecyclerView(){
        rcv = findViewById(R.id.rcv)
        adapter = PostAdapter()
        rcv.setAdapter(adapter)
        rcv.setLayoutManager(LinearLayoutManager(this@MainActivity))
        rcv.hasFixedSize()
    }

    fun setUpSwipeRefreshLayout(){
        swipeRefreshLayout = findViewById(R.id.swipe_container)
        swipeRefreshLayout.isEnabled = true
        swipeRefreshLayout.setColorSchemeResources(R.color.black);
        swipeRefreshLayout.setOnRefreshListener({
            postViewModel.loadPosts()
            swipeRefreshLayout.setRefreshing(false)
        })
    }

    fun setUpViewModel(){
        postViewModel.loadPosts()
        postViewModel.postLiveData.observe(this) { postResponseList ->
            adapter.setPostResponseList(postResponseList.toMutableList())
        }
    }
    override fun navigateToHomeActivity() {
        TODO("Not yet implemented")
    }

}