package com.icarus.recycle_app.ui.current_situation

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.icarus.recycle_app.ui.current_situation.placeholder.PlaceholderContent.PlaceholderItem
import com.icarus.recycle_app.databinding.ItemCurrentSituationBinding

class CurrentSituationAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<CurrentSituationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemCurrentSituationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
/*        holder.image = item.*/
        holder.title.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemCurrentSituationBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.ivImage
        val title: TextView = binding.tvTitle

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }

}