package com.jahnold.listdetailssample.base.util

interface PersistenceHelper {

    fun savePostId(id: Long)
    fun getPostId(): Long
}