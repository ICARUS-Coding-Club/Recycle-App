package com.icarus.recycle_app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.databinding.CvRegionInfoBinding
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.listener.OnItemClickListener

class RegionInfoAdapter(private val regionInfoList: ArrayList<RegionInfo>): RecyclerView.Adapter<RegionInfoAdapter.ViewHolder>() {

    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegionInfoAdapter.ViewHolder {
        val binding = CvRegionInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RegionInfoAdapter.ViewHolder, position: Int) {
        holder.bind(regionInfoList[position])
    }

    override fun getItemCount(): Int = regionInfoList.size

    inner class ViewHolder(val binding: CvRegionInfoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RegionInfo) {
            binding.root.setOnClickListener {
                listener.onClick(item.id)
            }

            binding.provinceName.text = item.provinceName
            binding.districtName.text = item.districtName
            binding.sectorName.text = item.sectorName
            binding.targetAreaName.text = item.targetAreaName

        }
    }
}