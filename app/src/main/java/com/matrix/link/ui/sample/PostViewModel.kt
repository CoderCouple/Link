package com.matrix.link.ui.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matrix.link.network.model.Post
import com.matrix.link.network.repository.PostRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {

    val postLiveData = MutableLiveData<List<Post>>()
    val compositeDisposable = CompositeDisposable()

    fun loadPosts() {
        compositeDisposable.add(postRepository.getPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                postLiveData.postValue(response)
            })
    }
}