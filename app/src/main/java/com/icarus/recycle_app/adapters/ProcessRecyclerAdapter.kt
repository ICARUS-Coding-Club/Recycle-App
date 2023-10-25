package com.icarus.recycle_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.databinding.CvProcessItemBinding
import com.icarus.recycle_app.dto.RecycleProcess

class ProcessRecyclerAdapter(private val items: List<RecycleProcess>) : RecyclerView.Adapter<ProcessRecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(pos: Int)
    }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProcessRecyclerAdapter.ViewHolder {
        val binding = CvProcessItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProcessRecyclerAdapter.ViewHolder,
        position: Int) {


    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: CvProcessItemBinding) : RecyclerView.ViewHolder(binding.root){

    }
}
