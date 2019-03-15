package com.jahnold.listdetailssample.details.viewmodel.usecase

import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.network.NetworkRepository
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import com.jahnold.listdetailssample.base.viewmodel.UseCase1
import io.reactivex.Observable
import javax.inject.Inject

class PostUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val schedulerHelper: SchedulerHelper
): UseCase1<Long, Post> {

    override fun getAction(input: Long): Observable<Post> {

        return networkRepository
            .getPosts()
            .map { posts -> posts.find { it.id == input } ?: throw IllegalStateException("Post $input not found") }
            .subscribeOn(schedulerHelper.io())
            .observeOn(schedulerHelper.mainThread())
    }
}