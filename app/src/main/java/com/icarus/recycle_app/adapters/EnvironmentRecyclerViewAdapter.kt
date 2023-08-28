package com.icarus.recycle_app.adapters

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.CvCardviewEnvironmentTipInfoBinding
import com.icarus.recycle_app.dto.EnvironmentTip

class EnvironmentRecyclerViewAdapter(
    private val environmentTips: List<EnvironmentTip>
): RecyclerView.Adapter<EnvironmentRecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(environmentTip: EnvironmentTip)
    }

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EnvironmentRecyclerViewAdapter.ViewHolder {
        val binding = CvCardviewEnvironmentTipInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: EnvironmentRecyclerViewAdapter.ViewHolder,
        position: Int) {

        holder.bind(environmentTips[position])


    }

    override fun getItemCount(): Int {
        return environmentTips.size
    }

    inner class ViewHolder(val binding: CvCardviewEnvironmentTipInfoBinding) : RecyclerView.ViewHolder(binding.root){


        fun bind(item: EnvironmentTip) {
            binding.tvTitle.text = item.title
            binding.tvContent.text = item.content

            binding.root.setOnClickListener {
                listener.onItemClicked(item)
            }


        }
    }
}