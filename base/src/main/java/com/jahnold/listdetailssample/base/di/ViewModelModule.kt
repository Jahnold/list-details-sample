package com.jahnold.listdetailssample.base.di

import androidx.lifecycle.ViewModelProvider
import com.jahnold.listdetailssample.base.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory
}