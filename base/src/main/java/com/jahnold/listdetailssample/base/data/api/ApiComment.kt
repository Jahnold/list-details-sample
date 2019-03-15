package com.jahnold.listdetailssample.base.data.api

data class ApiComment(
    val id: Long,
    val postId: Long,
    val name: String,
    val email: String,
    val body: String
)