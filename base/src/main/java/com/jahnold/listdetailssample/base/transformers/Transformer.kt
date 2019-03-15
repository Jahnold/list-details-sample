package com.jahnold.listdetailssample.base.transformers

interface Transformer<I, O> {
    fun transform(input: I): O
}