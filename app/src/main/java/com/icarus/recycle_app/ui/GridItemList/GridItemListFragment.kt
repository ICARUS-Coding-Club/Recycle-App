package com.icarus.recycle_app.ui.GridItemList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.icarus.recycle_app.databinding.GridItemListBinding

class GridItemListFragment : Fragment() {

    private var _binding: GridItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(GridItemListViewModel::class.java)

        _binding = GridItemListBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.nameTextView.text = "554"
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it



//        }


        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}