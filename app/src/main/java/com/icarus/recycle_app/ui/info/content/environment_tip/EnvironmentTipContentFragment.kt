package com.icarus.recycle_app.ui.info.content.environment_tip

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.ViewPager2Adapter
import com.icarus.recycle_app.databinding.FragmentEnvironmentTipContentBinding

class EnvironmentTipContentFragment : Fragment() {

    private var _binding: FragmentEnvironmentTipContentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = EnvironmentTipContentFragment()
    }

    private lateinit var viewModel: EnvironmentTipContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnvironmentTipContentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(EnvironmentTipContentViewModel::class.java)

        val imageFragments = ArrayList<Fragment>()
        imageFragments.add(EnvironmentTipImageFragment(R.drawable.i_box_green))
        imageFragments.add(EnvironmentTipImageFragment(R.drawable.i_box_green))
        imageFragments.add(EnvironmentTipImageFragment(R.drawable.i_box_green))

        val adapter = ViewPager2Adapter(imageFragments, requireActivity().supportFragmentManager, lifecycle)
        binding.vpTitleImage.adapter = adapter

        binding.indicator.setViewPager(binding.vpTitleImage)


        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}