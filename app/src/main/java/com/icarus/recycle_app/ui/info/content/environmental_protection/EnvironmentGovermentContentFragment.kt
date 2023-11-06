package com.icarus.recycle_app.ui.info.content.environmental_protection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.adapters.ViewPager2Adapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentTipContentBinding
import com.icarus.recycle_app.dto.EnvironmentTip
import com.icarus.recycle_app.ui.info.content.environment_tip.EnvironmentTipImageFragment

class EnvironmentGovermentContentFragment : Fragment() {

    private var _binding: FragmentEnvironmentTipContentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EnvironmentGovermentContentFragment()
    }

    private lateinit var viewModel: EnvironmentGovermentViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnvironmentTipContentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity())[EnvironmentGovermentViewModel::class.java]

        if (viewModel.isClickedPosition.value != null && !viewModel.environmentTipList.value.isNullOrEmpty()) {
            val trash: EnvironmentTip = viewModel.environmentTipList.value!![viewModel.isClickedPosition.value!!]

            val imageFragments = ArrayList<Fragment>()
            imageFragments.add(EnvironmentTipImageFragment(trash.image))

            val adapter = ViewPager2Adapter(imageFragments, requireActivity().supportFragmentManager, lifecycle)
            binding.vpTitleImage.adapter = adapter

            binding.indicator.setViewPager(binding.vpTitleImage)


            binding.tvTitle.text = trash.title
            binding.tvInfo.text = trash.reporter + " " + trash.time
            binding.tvContent.text = trash.body


        }


        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}