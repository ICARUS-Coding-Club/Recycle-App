package com.icarus.recycle_app.ui.info.content.recycling_advantage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R

class RecyclingAdvantageFragment : Fragment() {

    companion object {
        fun newInstance() = RecyclingAdvantageFragment()
    }

    private lateinit var viewModel: RecyclingAdvantageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycling_advantage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecyclingAdvantageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}