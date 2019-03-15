package com.jahnold.listdetailssample.details.viewmodel.usecase

import com.jahnold.listdetailssample.base.network.NetworkRepository
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import com.jahnold.listdetailssample.base.viewmodel.UseCase1
import io.reactivex.Observable
import javax.inject.Inject

class CommentCountUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val schedulerHelper: SchedulerHelper
): UseCase1<Long, Int> {

    override fun getAction(input: Long): Observable<Int> {

        return networkRepository
            .getComments()
            .map { comments -> comments.filter { it.postId == input }.size }
            .subscribeOn(schedulerHelper.io())
            .observeOn(schedulerHelper.mainThread())
    }
}