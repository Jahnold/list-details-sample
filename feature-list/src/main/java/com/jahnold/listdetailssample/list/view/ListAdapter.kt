package com.jahnold.listdetailssample.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jahnold.listdetailssample.list.R
import com.jahnold.listdetailssample.list.data.ListData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ListAdapter @Inject constructor(): RecyclerView.Adapter<ListViewHolder>() {

    private val items = mutableListOf<ListData>()
    private val clicks = PublishSubject.create<Long>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ListViewHolder(view, clicks)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setData(data: List<ListData>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    fun getItemClicks(): Observable<Long> {
        return clicks
    }
}