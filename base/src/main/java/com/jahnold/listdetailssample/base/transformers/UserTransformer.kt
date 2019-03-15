package com.jahnold.listdetailssample.base.transformers

import com.jahnold.listdetailssample.base.data.api.ApiUser
import com.jahnold.listdetailssample.base.data.domain.User

class UserTransformer: Transformer<ApiUser, User> {

    override fun transform(input: ApiUser): User {

        return User(
            id = input.id,
            name = input.name
        )
    }
}