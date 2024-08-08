package com.locotoinnovations.composeviewpager.network

import com.locotoinnovations.composeviewpager.network.model.Post
import retrofit2.http.GET

fun interface PostService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}