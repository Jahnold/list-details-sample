package com.jahnold.listdetailssample.details.viewmodel.usecase

import com.google.common.truth.Truth
import com.jahnold.listdetailssample.base.data.domain.User
import com.jahnold.listdetailssample.base.network.NetworkRepository
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserNameUseCaseTest {

    @Mock lateinit var networkRepository: NetworkRepository
    @Mock lateinit var schedulerHelper: SchedulerHelper

    val subscriber = TestObserver.create<String>()
    val users = PublishSubject.create<List<User>>()

    lateinit var useCase: UserNameUseCase

    @Before
    fun setUp() {

        useCase = UserNameUseCase(networkRepository, schedulerHelper)

        whenever(schedulerHelper.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerHelper.mainThread()).thenReturn(Schedulers.trampoline())
        whenever(networkRepository.getUsers()).thenReturn(users)
    }

    @Test
    fun `getAction  - should return the user name for the given id`() {

        useCase.getAction(USER1.id).subscribe(subscriber)
        users.onNext(listOf(USER1, USER2))

        val result = subscriber.values().first()

        Truth.assertThat(result).isEqualTo(USER1.name)
    }

    companion object {

        val USER1 = User(
            id = 1,
            name = "Matt"
        )

        private val USER2 = User(
            id = 2,
            name = "Hazal"
        )
    }
}