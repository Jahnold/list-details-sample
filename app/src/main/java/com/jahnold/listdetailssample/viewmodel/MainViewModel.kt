package com.jahnold.listdetailssample.viewmodel

import androidx.lifecycle.ViewModel
import com.jahnold.listdetailssample.base.navigation.NavigationService
import com.jahnold.listdetailssample.base.navigation.Navigator
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import io.reactivex.Observable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val navigationService: NavigationService,
    private val schedulerHelper: SchedulerHelper
) : ViewModel() {

    fun getNavigationEvents(): Observable<Navigator.Fragments> {
        return navigationService
            .getNavigationBroadcast()
            .observeOn(schedulerHelper.mainThread())
    }
}