package com.jahnold.listdetailssample.list.di

import androidx.lifecycle.ViewModel
import com.jahnold.listdetailssample.base.di.BaseModule
import com.jahnold.listdetailssample.base.di.NetworkModule
import com.jahnold.listdetailssample.base.di.ViewModelKey
import com.jahnold.listdetailssample.base.di.ViewModelModule
import com.jahnold.listdetailssample.list.view.ListFragment
import com.jahnold.listdetailssample.list.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module(includes = [
    ViewModelModule::class,
    BaseModule::class,
    ListDataModule::class,
    NetworkModule::class
])
abstract class ListModule {

    @ContributesAndroidInjector
    abstract fun bindListFragment(): ListFragment

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel
}