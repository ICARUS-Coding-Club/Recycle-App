package com.icarus.recycle_app

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class recycleadapter(var context: Context, var arrayList: ArrayList<recycleitem>): BaseAdapter() {

    override fun getItem(p0: Int): Any {
        return arrayList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view:View = View.inflate(context , R.layout.grid_item_list,null)

        var icons: ImageView = view.findViewById(R.id.paper)
        var names: TextView = view.findViewById(R.id.name_text_view)
        var recycleitem: recycleitem=arrayList.get(p0)

        icons.setImageResource(recycleitem.icons!!)
        names.text=recycleitem.name

        return view
    }
}