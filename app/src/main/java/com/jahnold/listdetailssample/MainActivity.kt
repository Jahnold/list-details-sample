package com.jahnold.listdetailssample

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.jahnold.listdetailssample.base.navigation.Navigator
import com.jahnold.listdetailssample.base.view.BaseActivity
import com.jahnold.listdetailssample.base.viewmodel.ViewModelFactory
import com.jahnold.listdetailssample.list.view.ListFragment
import com.jahnold.listdetailssample.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity: BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var navigator: Navigator

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .apply {
                    replace(R.id.fragment_container, ListFragment())
                    commit()
                }
        }

        subscribeToNavigationEvents()
    }

    private fun subscribeToNavigationEvents() {

        viewModel
            .getNavigationEvents()
            .subscribe(
                { navigationToFragment(it) },
                { it.printStackTrace() }
            )
            .track()
    }

    private fun navigationToFragment(destination: Navigator.Fragments) {

        val fragment = navigator.getFragmentInstance(destination)
        supportFragmentManager
            .beginTransaction()
            .apply {
                add(R.id.fragment_container, fragment)
                addToBackStack(null)
                commit()
            }
    }

    override fun onBackPressed() {

        supportFragmentManager.popBackStack()
    }
}
