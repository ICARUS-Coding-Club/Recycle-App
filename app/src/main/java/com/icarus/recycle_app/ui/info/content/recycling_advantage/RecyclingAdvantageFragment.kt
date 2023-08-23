package com.icarus.recycle_app.ui.info.content.recycling_advantage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentRecyclingAdvantageBinding

class RecyclingAdvantageFragment : Fragment() {

    private var _binding : FragmentRecyclingAdvantageBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = RecyclingAdvantageFragment()
    }

    private lateinit var viewModel: RecyclingAdvantageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclingAdvantageBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(RecyclingAdvantageViewModel::class.java)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}