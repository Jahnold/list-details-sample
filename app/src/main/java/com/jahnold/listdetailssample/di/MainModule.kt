package com.jahnold.listdetailssample.di

import com.jahnold.listdetailssample.base.navigation.Navigator
import com.jahnold.listdetailssample.navigation.NavigatorImpl
import dagger.Module
import dagger.Provides

@Module
object MainModule {

    @Provides
    @JvmStatic
    fun providesNavigator(navigator: NavigatorImpl): Navigator = navigator
}