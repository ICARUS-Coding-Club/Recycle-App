package com.icarus.recycle_app.ui.info.content.environmental_protection

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R

class EnvironmentalProtectionFragment : Fragment() {

    companion object {
        fun newInstance() = EnvironmentalProtectionFragment()
    }

    private lateinit var viewModel: EnvironmentalProtectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_environmental_protection, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EnvironmentalProtectionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}