package com.icarus.recycle_app.ui.info.content.environmental_protection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentEnvironmentalProtectionBinding
import com.icarus.recycle_app.databinding.FragmentRecyclingAdvantageBinding

class EnvironmentalProtectionFragment : Fragment() {

    private var _binding : FragmentEnvironmentalProtectionBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EnvironmentalProtectionFragment()
    }

    private lateinit var viewModel: EnvironmentalProtectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEnvironmentalProtectionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EnvironmentalProtectionViewModel::class.java)


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}