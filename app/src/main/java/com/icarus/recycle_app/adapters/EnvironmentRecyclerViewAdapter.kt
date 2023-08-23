package com.icarus.recycle_app.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.databinding.CvCardviewEnvironmentTipInfoBinding
import com.icarus.recycle_app.dto.EnvironmentTip

class EnvironmentRecyclerViewAdapter(private val environmentTips: List<EnvironmentTip>): RecyclerView.Adapter<EnvironmentRecyclerViewAdapter.ViewHolder>() {

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

    class ViewHolder(val binding: CvCardviewEnvironmentTipInfoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: EnvironmentTip) {
            binding.tvTitle.text = item.title
            binding.tvContent.text = item.content
        }

    }
}