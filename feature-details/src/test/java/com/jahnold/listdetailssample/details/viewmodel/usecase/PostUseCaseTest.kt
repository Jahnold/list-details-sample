@file:Suppress("MemberVisibilityCanBePrivate", "HasPlatformType")

package com.jahnold.listdetailssample.details.viewmodel.usecase

import com.google.common.truth.Truth
import com.jahnold.listdetailssample.base.data.domain.Post
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
class PostUseCaseTest {

    @Mock lateinit var networkRepository: NetworkRepository
    @Mock lateinit var schedulerHelper: SchedulerHelper

    val subscriber = TestObserver.create<Post>()
    val posts = PublishSubject.create<List<Post>>()

    lateinit var useCase: PostUseCase

    @Before
    fun setUp() {

        useCase = PostUseCase(networkRepository, schedulerHelper)

        whenever(schedulerHelper.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerHelper.mainThread()).thenReturn(Schedulers.trampoline())
        whenever(networkRepository.getPosts()).thenReturn(posts)
    }

    @Test
    fun `getAction - should return post matching id`() {

        useCase.getAction(1).subscribe(subscriber)
        posts.onNext(listOf(POST1, POST2, POST3))

        val result = subscriber.values().first()

        Truth.assertThat(result).isEqualTo(POST1)
    }

    companion object {

        val POST1 = Post(
            id = 1,
            userId = 123,
            title = "",
            body = ""
        )

        private val POST2 = Post(
            id = 2,
            userId = 123,
            title = "",
            body = ""
        )

        private val POST3 = Post(
            id = 3,
            userId = 123,
            title = "",
            body = ""
        )
    }
}