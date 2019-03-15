package com.jahnold.listdetailssample.details.data

import com.jahnold.listdetailssample.base.data.domain.Post
import com.jahnold.listdetailssample.base.transformers.Transformer

class DetailsDataTransformer: Transformer<Triple<Post, String, Int>, DetailsData> {

    override fun transform(input: Triple<Post, String, Int>): DetailsData {

        val (post, name, count) = input
        return DetailsData(
            title = post.title,
            body = post.body,
            name = name,
            comments = count.toString()
        )
    }
}