package com.icarus.recycle_app.adapters

import android.R
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import com.icarus.recycle_app.dto.Trash


class HomeAdapter(private val trashes: List<Trash>, context: Context?): BaseAdapter() {
    private var mContext: Context? = context


    override fun getCount(): Int {
        return trashes.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = AbsListView.LayoutParams(200, 200) // 이미지 크기 조절
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView = convertView as ImageView
        }
        imageView.setImageResource(R.drawable.ic_delete)
        return imageView
    }
}