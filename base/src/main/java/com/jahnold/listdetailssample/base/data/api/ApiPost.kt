package com.jahnold.listdetailssample.base.data.api

data class ApiPost(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)