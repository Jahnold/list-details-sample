package com.jahnold.listdetailssample.base.util

import android.app.Application
import android.content.Context
import javax.inject.Inject

class PersistenceHelperImpl @Inject constructor(context: Application) : PersistenceHelper {

    private val prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE)

    override fun savePostId(id: Long) {
        prefs.edit().putLong(KEY_POST_ID, id).apply()
    }

    override fun getPostId(): Long {
        return prefs.getLong(KEY_POST_ID, -1)
    }

    companion object {

        private const val KEY_POST_ID = "key_post_id"
        private const val PREFS_FILE = "prefs.txt"
    }
}