package com.jahnold.listdetailssample.details.viewmodel

import com.google.common.truth.Truth
import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.util.PersistenceHelper
import com.jahnold.listdetailssample.details.data.DetailsData
import com.jahnold.listdetailssample.details.data.DetailsDataTransformer
import com.jahnold.listdetailssample.details.viewmodel.usecase.CommentCountUseCase
import com.jahnold.listdetailssample.details.viewmodel.usecase.PostUseCase
import com.jahnold.listdetailssample.details.viewmodel.usecase.UserNameUseCase
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsViewModelTest {

    @Mock lateinit var persistenceHelper: PersistenceHelper
    @Mock lateinit var postUseCase: PostUseCase
    @Mock lateinit var userNameUseCase: UserNameUseCase
    @Mock lateinit var commentCountUseCase: CommentCountUseCase
    @Mock lateinit var transformer: DetailsDataTransformer

    lateinit var viewModel: DetailsViewModel
    val subscriber = TestObserver.create<DetailsViewModel.Result>()

    @Before
    fun setUp() {

        viewModel = DetailsViewModel(
            persistenceHelper,
            postUseCase,
            userNameUseCase,
            commentCountUseCase,
            transformer
        )

        whenever(persistenceHelper.getPostId()).thenReturn(1L)
        whenever(postUseCase.getAction(any())).thenReturn(Observable.just(POST))
        whenever(userNameUseCase.getAction(any())).thenReturn(Observable.just(NAME))
        whenever(commentCountUseCase.getAction(any())).thenReturn(Observable.just(COUNT))
        whenever(transformer.transform(any())).thenReturn(DATA)
    }

    @Test
    fun `getDetailsData - should get current post id from persistence`() {

        viewModel.getDetailsData()
        verify(persistenceHelper).getPostId()
    }

    @Test
    fun `getDetailsData - should request data from the usecases`() {

        viewModel.getDetailsData().subscribe()
        verify(postUseCase).getAction(any())
        verify(userNameUseCase).getAction(any())
        verify(commentCountUseCase).getAction(any())
    }

    @Test
    fun `getDetailsData - should transform the results`() {

        viewModel.getDetailsData().subscribe()
        verify(transformer).transform(any())
    }

    @Test
    fun `getDetailData - should start with Loading`() {

        viewModel.getDetailsData().subscribe(subscriber)

        val result = subscriber.values().first()
        Truth.assertThat(result).isInstanceOf(DetailsViewModel.Result.Loading::class.java)
    }

    companion object {

        private val POST = Post(
            id = 1,
            userId = 123,
            title = "",
            body = ""
        )

        private const val NAME = "Matt"
        private const val COUNT = 5

        private val DATA = DetailsData(
            title = POST.title,
            body = POST.body,
            name = NAME,
            comments = COUNT.toString()
        )
    }
}