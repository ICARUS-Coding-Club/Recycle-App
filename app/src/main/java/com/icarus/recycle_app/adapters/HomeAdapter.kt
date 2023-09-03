package com.icarus.recycle_app.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.createBitmap
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.onboarding.OnBoardingActivity
import com.icarus.recycle_app.ui.search.image.trash_request.TrashRequestActivity


class HomeAdapter(private var trashes: List<Trash>, context: Context?): BaseAdapter() {
    private var mContext: Context? = context


    override fun getCount(): Int {
        return trashes.size
    }

    override fun getItem(position: Int): Any? {
        return trashes[position]
    }

    override fun getItemId(position: Int): Long {
        return trashes[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val imageView: ImageView
        if (convertView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = AbsListView.LayoutParams(200, 200) // 이미지 크기 조절
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            imageView = convertView as ImageView

            mContext?.let {
                Glide.with(it).load(trashes[position].image).into(imageView)
            }

        }




        imageView.setOnClickListener {
            mContext?.startActivity(Intent(mContext, TrashRequestActivity::class.java))
        }

        return imageView
    }

    fun updateData(newTrashes: List<Trash>) {
        this.trashes = newTrashes
    }
}