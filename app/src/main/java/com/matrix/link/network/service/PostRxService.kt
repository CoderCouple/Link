package com.matrix.link.network.service

import com.matrix.link.network.model.Post
import io.reactivex.Single
import retrofit2.http.GET

interface PostRxService {

    @GET("posts")
    fun getPosts(): Single<List<Post>>

}
