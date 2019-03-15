package com.jahnold.listdetailssample.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jahnold.listdetailssample.base.view.BaseFragment
import com.jahnold.listdetailssample.list.R
import com.jahnold.listdetailssample.list.viewmodel.ListViewModel
import javax.inject.Inject

class ListFragment: BaseFragment() {

    @Inject lateinit var adapter: ListAdapter
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ListViewModel
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        recycler = view.findViewById(R.id.posts_recycler)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        subscribeToData()
        subscribeToItemClicks()
    }

    private fun initRecycler() {

        recycler.layoutManager = LinearLayoutManager(recycler.context)
        recycler.adapter = adapter
    }

    private fun subscribeToData() {

        viewModel.getPosts()
            .subscribe(
                { result -> adapter.setData(result) },
                { it.printStackTrace() }
            )
            .track()
    }

    private fun subscribeToItemClicks() {

        adapter.getItemClicks()
            .subscribe(
                { id -> viewModel.selectPost(id)},
                { it.printStackTrace() }
            )
            .track()
    }
}