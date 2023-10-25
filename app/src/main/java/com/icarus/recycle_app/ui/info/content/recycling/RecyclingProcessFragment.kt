package com.icarus.recycle_app.ui.info.content.recycling

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.ProcessRecyclerAdapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentalProtectionBinding
import com.icarus.recycle_app.dto.RecycleProcess

class RecyclingProcessFragment : Fragment() {

    private var _binding : FragmentEnvironmentalProtectionBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = RecyclingProcessFragment()
    }

    private lateinit var viewModel: EnvironmentalProtectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEnvironmentalProtectionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(EnvironmentalProtectionViewModel::class.java)

        val items = listOf(RecycleProcess("금속 캔", listOf(ContextCompat.getDrawable(requireContext(), R.drawable.can)!!)),
            RecycleProcess("종이팩", listOf(ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!)),


            RecycleProcess("페트병", listOf(ContextCompat.getDrawable(requireContext(), R.drawable.can)!!)),


            RecycleProcess("플라스틱", listOf(ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!)),


            RecycleProcess("유리병", listOf(ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!)),


            RecycleProcess("비닐", listOf(ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!,
                ContextCompat.getDrawable(requireContext(), R.drawable.can)!!)))



        val adapter = ProcessRecyclerAdapter(items, childFragmentManager, lifecycle)
        binding.recyclerview.adapter = adapter






        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}