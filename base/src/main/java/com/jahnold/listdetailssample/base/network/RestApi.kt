package com.jahnold.listdetailssample.base.network

import com.jahnold.listdetailssample.base.data.api.ApiComment
import com.jahnold.listdetailssample.base.data.api.ApiPost
import com.jahnold.listdetailssample.base.data.api.ApiUser
import io.reactivex.Single
import retrofit2.http.GET

interface RestApi {

    @GET("posts")
    fun getPosts(): Single<List<ApiPost>>

    @GET("users")
    fun getUsers(): Single<List<ApiUser>>

    @GET("comments")
    fun getComments(): Single<List<ApiComment>>
}