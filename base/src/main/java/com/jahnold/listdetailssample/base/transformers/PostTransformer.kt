package com.jahnold.listdetailssample.base.transformers

import com.jahnold.listdetailssample.base.data.api.ApiPost
import com.jahnold.listdetailssample.base.data.domain.Post

class PostTransformer: Transformer<ApiPost, Post> {

    override fun transform(input: ApiPost): Post {

        return Post(
            id = input.id,
            userId = input.userId,
            title = input.title,
            body = input.body
        )
    }
}