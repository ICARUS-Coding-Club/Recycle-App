package com.icarus.recycle_app.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.icarus.recycle_app.R
import com.icarus.recycle_app.adapters.HomeAdapter
import com.icarus.recycle_app.databinding.FragmentHomeBinding
import com.icarus.recycle_app.dto.Trash
import com.icarus.recycle_app.ui.info.InfoFragment
import com.icarus.recycle_app.ui.search.SearchActivity
import com.icarus.recycle_app.ui.search.base.SearchListActivity
import com.icarus.recycle_app.ui.setting.SettingFragment
import com.icarus.recycle_app.ui.splash.SplashActivity


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val lists = listOf<Trash>(
            Trash(1,"종이","종이","1","2",1,true,"", R.drawable.can.toString())
        )
        binding.gridView.adapter = HomeAdapter(lists,activity)

        initListener()
        return root
    }

    private fun initListener(){
        binding.svCl1.setOnClickListener {

            startActivity(Intent(activity,SearchListActivity::class.java))

        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}