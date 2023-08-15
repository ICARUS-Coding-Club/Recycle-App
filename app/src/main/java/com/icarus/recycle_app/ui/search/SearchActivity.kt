package com.icarus.recycle_app.ui.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentSearchBarcodeBinding
import com.icarus.recycle_app.ui.search.barcode.SearchBarcodeFragment
import com.icarus.recycle_app.ui.search.image.SearchImageFragment

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchImageFragment.newInstance())
                .commitNow()
        }

        val intent = intent
        val typeClickValue = intent.getIntExtra("type_click", -1)

        when (typeClickValue) {
            0 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchImageFragment.newInstance())
                    .commitNow()
            }
            1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SearchBarcodeFragment.newInstance())
                    .commitNow()
            }
            else -> {
                // 오류 처리 또는 기본 로직
            }
        }


    }
}