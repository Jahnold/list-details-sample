package com.jahnold.listdetailssample.base.network

import com.jahnold.listdetailssample.base.data.api.ApiComment
import com.jahnold.listdetailssample.base.data.api.ApiPost
import com.jahnold.listdetailssample.base.data.api.ApiUser
import com.jahnold.listdetailssample.base.data.domain.Comment
import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.data.domain.User
import com.jahnold.listdetailssample.base.transformers.Transformer
import io.reactivex.Observable
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val restApi: RestApi,
    private val commentTransformer: Transformer<ApiComment, Comment>,
    private val postTransformer: Transformer<ApiPost, Post>,
    private val userTransformer: Transformer<ApiUser, User>
) {

    fun getComments(): Observable<List<Comment>> {

        return restApi
            .getComments()
            .map { comments -> comments.map { commentTransformer.transform(it) } }
            .toObservable()
    }

    fun getPosts(): Observable<List<Post>> {

        return restApi
            .getPosts()
            .map { posts -> posts.map { postTransformer.transform(it) } }
            .toObservable()
    }

    fun getUsers(): Observable<List<User>> {

        return restApi
            .getUsers()
            .map { users -> users.map { userTransformer.transform(it) } }
            .toObservable()
    }
}