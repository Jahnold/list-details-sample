package com.jahnold.listdetailssample.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jahnold.listdetailssample.base.view.BaseFragment
import com.jahnold.listdetailssample.details.R
import com.jahnold.listdetailssample.details.data.DetailsData
import com.jahnold.listdetailssample.details.viewmodel.DetailsViewModel
import javax.inject.Inject

class DetailsFragment: BaseFragment() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: DetailsViewModel

    private lateinit var title: TextView
    private lateinit var body: TextView
    private lateinit var name: TextView
    private lateinit var comments: TextView

    private lateinit var content: ViewGroup
    private lateinit var loading: ProgressBar
    private lateinit var error: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_details, container, false)

        title = view.findViewById(R.id.details_title)
        body = view.findViewById(R.id.details_body)
        name = view.findViewById(R.id.details_name)
        comments = view.findViewById(R.id.details_comments)

        content = view.findViewById(R.id.details_content)
        loading = view.findViewById(R.id.details_loading)
        error = view.findViewById(R.id.details_error)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToData()
    }

    private fun subscribeToData() {

        viewModel
            .getDetailsData()
            .subscribe(
                { result ->
                    setVisibilities(result)
                    if (result is DetailsViewModel.Result.Success) {
                        setViewFromData(result.data)
                    }
                },
                { it.printStackTrace() }
            )
            .track()
    }

    private fun setVisibilities(result: DetailsViewModel.Result) {
        content.isVisible = result.isContentVisible
        loading.isVisible = result.isLoadingVisible
        error.isVisible = result.isErrorVisible
    }

    private fun setViewFromData(data: DetailsData) {
        title.text = data.title
        body.text = data.body
        name.text = data.name
        comments.text = data.comments
    }
}