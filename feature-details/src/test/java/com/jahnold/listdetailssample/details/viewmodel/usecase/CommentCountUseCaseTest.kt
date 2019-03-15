@file:Suppress("MemberVisibilityCanBePrivate", "HasPlatformType")

package com.jahnold.listdetailssample.details.viewmodel.usecase

import com.google.common.truth.Truth
import com.jahnold.listdetailssample.base.data.domain.Comment
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
class CommentCountUseCaseTest {

    @Mock lateinit var networkRepository: NetworkRepository
    @Mock lateinit var schedulerHelper: SchedulerHelper

    val subscriber = TestObserver.create<Int>()
    val comments = PublishSubject.create<List<Comment>>()

    lateinit var useCase: CommentCountUseCase

    @Before
    fun setUp() {

        useCase = CommentCountUseCase(networkRepository, schedulerHelper)

        whenever(schedulerHelper.io()).thenReturn(Schedulers.trampoline())
        whenever(schedulerHelper.mainThread()).thenReturn(Schedulers.trampoline())
        whenever(networkRepository.getComments()).thenReturn(comments)
    }

    @Test
    fun `getAction - should return correct comment count for post id`() {

        useCase.getAction(1).subscribe(subscriber)
        comments.onNext(listOf(COMMENT1, COMMENT2, COMMENT3))

        val result = subscriber.values().first()

        Truth.assertThat(result).isEqualTo(2)
    }

    companion object {

        private val COMMENT1 = Comment(
            id = 1,
            postId = 1,
            name = "",
            email = "",
            body = ""
        )

        private val COMMENT2 = Comment(
            id = 2,
            postId = 1,
            name = "",
            email = "",
            body = ""
        )

        private val COMMENT3 = Comment(
            id = 3,
            postId = 2,
            name = "",
            email = "",
            body = ""
        )
    }
}