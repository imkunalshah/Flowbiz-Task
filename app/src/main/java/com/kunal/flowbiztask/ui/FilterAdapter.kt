package com.kunal.flowbiztask.ui

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.kunal.flowbiztask.R
import com.kunal.flowbiztask.data.network.models.Question

class FilterAdapter(
    private val context:Context,
    private val list: HashMap<Int,String>
): RecyclerView.Adapter<FilterAdapter.FilterVH>() {
    class FilterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cb:CheckBox = itemView.findViewById(R.id.cb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterAdapter.FilterVH {
        val rootView:View =
            LayoutInflater.from(context).inflate(R.layout.tags_item,parent, false)
        return FilterVH(rootView)
    }

    override fun onBindViewHolder(holder: FilterAdapter.FilterVH, position: Int) {
        holder.cb.text = list[position]

    }

    override fun getItemCount(): Int {
        return list.size
    }
}