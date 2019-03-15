package com.jahnold.listdetailssample.details.viewmodel.usecase

import com.jahnold.listdetailssample.base.data.domain.User
import com.jahnold.listdetailssample.base.network.NetworkRepository
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import com.jahnold.listdetailssample.base.viewmodel.UseCase1
import io.reactivex.Observable
import javax.inject.Inject

class UserNameUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val schedulerHelper: SchedulerHelper
): UseCase1<Long, String> {

    override fun getAction(input: Long): Observable<String> {

        return networkRepository
            .getUsers()
            .map { users -> users.find { it.id == input } }
            .map { user: User? -> user?.name.orEmpty() }
            .subscribeOn(schedulerHelper.io())
            .observeOn(schedulerHelper.mainThread())
    }
}