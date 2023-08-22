package com.icarus.recycle_app.ui.search.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSearchBarcodeBinding
import com.icarus.recycle_app.ui.search.barcode.SearchBarcodeFragment
import com.icarus.recycle_app.ui.search.image.SearchImageFragment

class SearchListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_list)

    }
}