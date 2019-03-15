package com.jahnold.listdetailssample.di

import androidx.lifecycle.ViewModel
import com.jahnold.listdetailssample.MainActivity
import com.jahnold.listdetailssample.base.di.ViewModelKey
import com.jahnold.listdetailssample.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: MainViewModel): ViewModel
}