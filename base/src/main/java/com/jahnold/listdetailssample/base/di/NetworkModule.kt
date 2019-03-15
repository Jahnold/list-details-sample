package com.jahnold.listdetailssample.base.di

import com.jahnold.listdetailssample.base.data.api.ApiComment
import com.jahnold.listdetailssample.base.data.api.ApiPost
import com.jahnold.listdetailssample.base.data.api.ApiUser
import com.jahnold.listdetailssample.base.data.domain.Comment
import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.data.domain.User
import com.jahnold.listdetailssample.base.network.RestApi
import com.jahnold.listdetailssample.base.transformers.CommentTransformer
import com.jahnold.listdetailssample.base.transformers.PostTransformer
import com.jahnold.listdetailssample.base.transformers.Transformer
import com.jahnold.listdetailssample.base.transformers.UserTransformer
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    @JvmStatic
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @JvmStatic
    fun providesRestApi(retrofit: Retrofit): RestApi =
            retrofit.create(RestApi::class.java)

    @Provides
    @JvmStatic
    fun providesUserTransformer(): Transformer<ApiUser, User> =
        UserTransformer()

    @Provides
    @JvmStatic
    fun providesCommentTransformer(): Transformer<ApiComment, Comment> =
        CommentTransformer()

    @Provides
    @JvmStatic
    fun providesPostTransformer(): Transformer<ApiPost, Post> =
        PostTransformer()
}