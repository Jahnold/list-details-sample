package com.jahnold.listdetailssample.di

import com.jahnold.listdetailssample.App
import com.jahnold.listdetailssample.details.di.DetailsModule
import com.jahnold.listdetailssample.list.di.ListModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainModule::class,
        ActivityModule::class,
        ListModule::class,
        DetailsModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}