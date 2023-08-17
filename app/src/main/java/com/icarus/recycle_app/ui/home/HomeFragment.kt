package com.icarus.recycle_app.ui.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.icarus.recycle_app.adapters.HomeAdapter
import com.icarus.recycle_app.databinding.FragmentHomeBinding
import com.icarus.recycle_app.dto.Trash


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
            Trash(1,"종이","종이","1","2",1,true,false,R.drawable.ic_delete.toString())
        )
        binding.gridView.adapter = HomeAdapter(lists,activity)


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}