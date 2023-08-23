package com.icarus.recycle_app.ui.info.content.environment_tip

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R

class EnvironmentTipFragment : Fragment() {

    companion object {
        fun newInstance() = EnvironmentTipFragment()
    }

    private lateinit var viewModel: EnvironmentTipViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_environment_tip, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EnvironmentTipViewModel::class.java)
        // TODO: Use the ViewModel
    }

}