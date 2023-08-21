package com.icarus.recycle_app.ui.info.content

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityInfoContentBinding
import com.icarus.recycle_app.databinding.ActivityMainBinding
import com.icarus.recycle_app.ui.info.content.ui.info.recycling_symbol.RecyclingSymbolFragment

/**
 * 쓰레기 안내를 표시할 액티비티
 */
class InfoContentActivity : AppCompatActivity() {


    private var _binding: ActivityInfoContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityInfoContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }









        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecyclingSymbolFragment.newInstance())
                .commitNow()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, RecyclingSymbolFragment.newInstance())
            .commitNow()

    }
}