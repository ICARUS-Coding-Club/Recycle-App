package com.icarus.recycle_app.ui

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.icarus.recycle_app.R
import com.icarus.recycle_app.recycleadapter
import com.icarus.recycle_app.recycleitem
import java.util.UUID

class main : AppCompatActivity() , AdapterView.OnItemClickListener {


    private var gridView: GridView? = null
    private var arrayList:ArrayList<recycleitem> ? = null
    private var recycleadapter: recycleadapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var arrayList:ArrayList<recycleitem> = ArrayList()


        gridView=findViewById(R.id.grid_view)
        arrayList= ArrayList()
        arrayList=setDataList()
        recycleadapter = recycleadapter(applicationContext,arrayList!!)
        gridView?.adapter=recycleadapter
        gridView?.onItemClickListener=this
    }
    private fun setDataList() :ArrayList<recycleitem>{
        var arrayList:ArrayList<recycleitem> = ArrayList()

        arrayList.add(recycleitem(R.drawable.paper,"종이"))
        arrayList.add(recycleitem(R.drawable.plastic,"플라스틱"))
        arrayList.add(recycleitem(R.drawable.vinyl,"비닐"))
        arrayList.add(recycleitem(R.drawable.can,"캔"))
        arrayList.add(recycleitem(R.drawable.styrofoam,"스티로폼"))
        arrayList.add(recycleitem(R.drawable.glass,"유리"))

        return arrayList
    }


    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var recycleitem: recycleitem = arrayList!!.get(p2)
        Toast.makeText(applicationContext,recycleitem.name , Toast.LENGTH_LONG).show()
    }

}
