package com.matrix.link.network.repository

import com.matrix.link.network.model.Post
import com.matrix.link.network.service.PostRxService
import io.reactivex.Single
import javax.inject.Inject

class PostRepository @Inject constructor(private val postRxService: PostRxService) {
    fun getPosts() : Single<List<Post>> {
        return postRxService.getPosts()
    }



}