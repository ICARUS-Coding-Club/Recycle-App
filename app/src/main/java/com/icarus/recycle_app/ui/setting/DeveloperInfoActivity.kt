package com.icarus.recycle_app.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.ActivityDeveloperInfoBinding

class DeveloperInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeveloperInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeveloperInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}