package com.jahnold.listdetailssample.list.di

import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.transformers.Transformer
import com.jahnold.listdetailssample.list.data.ListData
import com.jahnold.listdetailssample.list.data.ListDataTransformer
import dagger.Module
import dagger.Provides

@Module
object ListDataModule {

    @Provides
    @JvmStatic
    fun providesListDataTransformer(): Transformer<Post, ListData> =
        ListDataTransformer()
}