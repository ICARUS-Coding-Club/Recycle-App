package com.icarus.recycle_app.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.ViewPager2Adapter
import com.icarus.recycle_app.databinding.FragmentHomeBinding
import com.icarus.recycle_app.databinding.FragmentOnboardingFirstBinding
import com.icarus.recycle_app.databinding.FragmentOnboardingSecondBinding

class OnBoardingSecondFragment : Fragment() {

    private var _binding: FragmentOnboardingSecondBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOnboardingSecondBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.viewPager2)
        binding.btnNext.setOnClickListener {
            viewPager2?.currentItem = 2
        }


        return root
    }

}