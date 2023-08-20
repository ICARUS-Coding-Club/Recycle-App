package com.icarus.recycle_app.ui.search.image.trash_request

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityTrashRequestBinding

class TrashRequestActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityTrashRequestBinding

    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityTrashRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()




    }

    private fun initListener() {
        binding.ibBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

}