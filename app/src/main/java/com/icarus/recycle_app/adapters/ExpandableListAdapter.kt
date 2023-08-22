package com.icarus.recycle_app.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.icarus.recycle_app.R
import com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol.RecyclingSymbolFragment

class ExpandableListAdapter (
    private val context: Context,
    private val items: List<RecyclingSymbolFragment.Item>
    ) : BaseExpandableListAdapter() {


    override fun getGroupCount(): Int = items.size

    override fun getChildrenCount(groupPosition: Int): Int = items[groupPosition].details.size

    override fun getGroup(groupPosition: Int): Any = items[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any = items[groupPosition].details[childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.cv_expandable_list_group, parent, false)
        }

        val ivArrowMark = view!!.findViewById<TextView>(R.id.ibArrow)

        if (isExpanded) {
            ivArrowMark.setBackgroundResource(R.drawable.ic_arrow_up1_balck)
        } else {
            ivArrowMark.setBackgroundResource(R.drawable.ic_arrow_down1_black)
        }



        return view
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup?
    ):  View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.cv_expandable_list_child, parent, false)
        }

        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}



