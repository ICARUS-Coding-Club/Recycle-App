package com.icarus.recycle_app.ui.info.content.trash_place

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R
import com.icarus.recycle_app.databinding.FragmentTrashPlaceBinding

class TrashPlaceFragment : Fragment() {

    private var _binding: FragmentTrashPlaceBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = TrashPlaceFragment()
    }

    private lateinit var viewModel: TrashPlaceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrashPlaceBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(TrashPlaceViewModel::class.java)





        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}