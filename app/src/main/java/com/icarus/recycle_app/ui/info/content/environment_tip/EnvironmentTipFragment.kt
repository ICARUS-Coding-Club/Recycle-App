package com.icarus.recycle_app.ui.info.content.environment_tip

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.EnvironmentRecyclerViewAdapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentTipBinding
import com.icarus.recycle_app.databinding.FragmentEnvironmentalProtectionBinding
import com.icarus.recycle_app.dto.EnvironmentTip
import com.icarus.recycle_app.ui.info.content.environmental_protection.EnvironmentalProtectionViewModel

class EnvironmentTipFragment : Fragment() {

    private var _binding : FragmentEnvironmentTipBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EnvironmentTipFragment()
    }

    private lateinit var viewModel: EnvironmentTipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnvironmentTipBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EnvironmentTipViewModel::class.java)

        val items = ArrayList<EnvironmentTip>()
        items.add(EnvironmentTip("0", "환경의날", "환경의날은 이런 것"))
        items.add(EnvironmentTip("0", "환경의날", "환경의날은 이런 것"))
        items.add(EnvironmentTip("0", "환경의날", "환경의날은 이런 것"))
        items.add(EnvironmentTip("0", "환경의날", "환경의날은 이런 것"))
        items.add(EnvironmentTip("0", "환경의날", "환경의날은 이런 것"))
        items.add(EnvironmentTip("0", "환경의날", "환경의날은 이런 것"))

        val adapter = EnvironmentRecyclerViewAdapter(items)
        binding.rvEnvironmentTip.adapter = adapter
        binding.rvEnvironmentTip.setHasFixedSize(true)



        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}