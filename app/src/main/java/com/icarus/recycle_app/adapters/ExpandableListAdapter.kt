package com.icarus.recycle_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
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

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
        }

        val textView = view?.findViewById<TextView>(android.R.id.text1)
        textView?.text = items[groupPosition].title

        return view!!
    }

    override fun getChildView(
        groupPosition: Int, childPosition: Int, isLastChild: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View {
        var view = convertView
        if (view == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(android.R.layout.simple_expandable_list_item_2, parent, false)
        }

        val textView = view?.findViewById<TextView>(android.R.id.text1)
        textView?.text = items[groupPosition].details[childPosition]

        return view!!
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}



