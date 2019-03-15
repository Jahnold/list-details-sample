package com.jahnold.listdetailssample.list.data

import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.transformers.Transformer

class ListDataTransformer: Transformer<Post, ListData> {

    override fun transform(input: Post): ListData {
        return ListData(
            id = input.id,
            title = input.title
        )
    }
}