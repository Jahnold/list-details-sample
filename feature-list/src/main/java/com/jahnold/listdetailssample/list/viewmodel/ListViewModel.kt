package com.jahnold.listdetailssample.list.viewmodel

import androidx.lifecycle.ViewModel
import com.jahnold.listdetailssample.base.navigation.NavigationService
import com.jahnold.listdetailssample.base.navigation.Navigator
import com.jahnold.listdetailssample.base.util.PersistenceHelper
import com.jahnold.listdetailssample.list.data.ListData
import com.jahnold.listdetailssample.list.viewmodel.usecase.PostsUseCase
import io.reactivex.Observable
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val postsUseCase: PostsUseCase,
    private val persistenceHelper: PersistenceHelper,
    private val navigationService: NavigationService
): ViewModel() {

    fun getPosts(): Observable<List<ListData>> {
        return postsUseCase.getAction()
    }

    fun selectPost(id: Long) {
        persistenceHelper.savePostId(id)
        navigationService.sendNavigationEvent(Navigator.Fragments.Details)
    }
}