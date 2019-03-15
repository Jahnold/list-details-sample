package com.jahnold.listdetailssample.base.transformers

import com.jahnold.listdetailssample.base.data.api.ApiComment
import com.jahnold.listdetailssample.base.data.domain.Comment

class CommentTransformer: Transformer<ApiComment, Comment> {

    override fun transform(input: ApiComment): Comment {
        return Comment(
            id = input.id,
            postId = input.postId,
            name = input.name,
            email = input.email,
            body = input.body
        )
    }
}