package com.jahnold.listdetailssample.details.viewmodel

import androidx.lifecycle.ViewModel
import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.transformers.Transformer
import com.jahnold.listdetailssample.base.util.PersistenceHelper
import com.jahnold.listdetailssample.details.data.DetailsData
import com.jahnold.listdetailssample.details.viewmodel.usecase.CommentCountUseCase
import com.jahnold.listdetailssample.details.viewmodel.usecase.PostUseCase
import com.jahnold.listdetailssample.details.viewmodel.usecase.UserNameUseCase
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val persistenceHelper: PersistenceHelper,
    private val postUseCase: PostUseCase,
    private val userNameUseCase: UserNameUseCase,
    private val commentCountUseCase: CommentCountUseCase,
    private val transformer: Transformer<Triple<Post, String, Int>, DetailsData>
): ViewModel() {

    fun getDetailsData(): Observable<Result> {

        val postId = persistenceHelper.getPostId()

        return postUseCase
            .getAction(postId)
            .flatMap { post ->
                Observable
                    .zip(
                        userNameUseCase.getAction(post.userId),
                        commentCountUseCase.getAction(postId),
                        BiFunction<String, Int, Result>{ name, count ->
                            val data = transformer.transform(Triple(post, name, count))
                            return@BiFunction Result.Success(data)
                        }
                    )
            }
            .startWith(Result.Loading)
            .onErrorReturn { Result.Error }
    }

    sealed class Result(
        val isLoadingVisible: Boolean,
        val isContentVisible: Boolean,
        val isErrorVisible: Boolean
    ) {
        object Loading: Result(true, false, false)
        data class Success(val data: DetailsData): Result(false, true, false)
        object Error: Result(false, false, true)
    }
}