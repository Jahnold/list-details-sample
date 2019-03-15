package com.jahnold.listdetailssample.base.navigation

import com.jahnold.listdetailssample.base.view.BaseFragment

interface Navigator {

    sealed class Fragments {
        object List: Fragments()
        object Details: Fragments()
    }

    fun getFragmentInstance(destination: Fragments): BaseFragment
}