package com.jahnold.listdetailssample.details.di

import androidx.lifecycle.ViewModel
import com.jahnold.listdetailssample.base.di.BaseModule
import com.jahnold.listdetailssample.base.di.NetworkModule
import com.jahnold.listdetailssample.base.di.ViewModelKey
import com.jahnold.listdetailssample.base.di.ViewModelModule
import com.jahnold.listdetailssample.details.view.DetailsFragment
import com.jahnold.listdetailssample.details.viewmodel.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
    BaseModule::class,
    DetailsDataModule::class,
    NetworkModule::class
])
abstract class DetailsModule {

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel
}