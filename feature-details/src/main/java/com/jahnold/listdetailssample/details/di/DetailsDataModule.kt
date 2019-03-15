package com.jahnold.listdetailssample.details.di

import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.transformers.Transformer
import com.jahnold.listdetailssample.details.data.DetailsData
import com.jahnold.listdetailssample.details.data.DetailsDataTransformer
import dagger.Module
import dagger.Provides

@Module
object DetailsDataModule {

    @Provides
    @JvmStatic
    fun providesDetailsDataTransformer(): Transformer<Triple<Post, String, Int>, DetailsData> =
        DetailsDataTransformer()
}