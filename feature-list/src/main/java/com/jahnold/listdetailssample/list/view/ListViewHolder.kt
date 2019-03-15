package com.jahnold.listdetailssample.list.view

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jahnold.listdetailssample.list.R
import com.jahnold.listdetailssample.list.data.ListData
import io.reactivex.subjects.PublishSubject

class ListViewHolder(item: View, private val clickSubject: PublishSubject<Long>): RecyclerView.ViewHolder(item) {

    private val title: TextView = item.findViewById(R.id.post_title)
    private val layout: ViewGroup = item.findViewById(R.id.post_layout)

    fun bind(data: ListData) {

        title.text = data.title
        layout.setOnClickListener { clickSubject.onNext(data.id) }
    }
}