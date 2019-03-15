package com.jahnold.listdetailssample.list.viewmodel.usecase

import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.network.NetworkRepository
import com.jahnold.listdetailssample.base.transformers.Transformer
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import com.jahnold.listdetailssample.base.viewmodel.UseCase0
import com.jahnold.listdetailssample.list.data.ListData
import io.reactivex.Observable
import javax.inject.Inject

class PostsUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val transformer: Transformer<Post, ListData>,
    private val schedulerHelper: SchedulerHelper
): UseCase0<List<ListData>> {

    override fun getAction(): Observable<List<ListData>> {

        return networkRepository
            .getPosts()
            .map { posts -> posts.map { transformer.transform(it) } }
            .subscribeOn(schedulerHelper.io())
            .observeOn(schedulerHelper.mainThread())
    }
}