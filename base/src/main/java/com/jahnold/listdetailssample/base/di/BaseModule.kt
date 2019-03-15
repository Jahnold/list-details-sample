package com.jahnold.listdetailssample.base.di

import com.jahnold.listdetailssample.base.util.PersistenceHelper
import com.jahnold.listdetailssample.base.util.PersistenceHelperImpl
import com.jahnold.listdetailssample.base.util.SchedulerHelper
import com.jahnold.listdetailssample.base.util.SchedulerHelperImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BaseModule {

    @Singleton
    @Binds
    abstract fun bindSchedulerHelper(schedulerHelperImpl: SchedulerHelperImpl): SchedulerHelper

    @Singleton
    @Binds
    abstract fun bindPrefsHelper(persistenceHelperImpl: PersistenceHelperImpl): PersistenceHelper
}