package com.jahnold.listdetailssample.navigation

import com.jahnold.listdetailssample.base.navigation.Navigator
import com.jahnold.listdetailssample.base.view.BaseFragment
import com.jahnold.listdetailssample.details.view.DetailsFragment
import com.jahnold.listdetailssample.list.view.ListFragment
import javax.inject.Inject

class NavigatorImpl @Inject constructor(): Navigator {

    override fun getFragmentInstance(destination: Navigator.Fragments): BaseFragment {
        return when (destination) {

            Navigator.Fragments.List -> ListFragment()
            Navigator.Fragments.Details -> DetailsFragment()
        }
    }
}