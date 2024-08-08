package com.locotoinnovations.composeviewpager.network.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("id")
    val id: Int,
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("title")
    val body: String,
    @SerializedName("body")
    val title: String
)