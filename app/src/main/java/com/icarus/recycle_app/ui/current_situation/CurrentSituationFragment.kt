package com.icarus.recycle_app.ui.current_situation

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.icarus.recycle_app.R

class CurrentSituationFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentSituationFragment()
    }

    private lateinit var viewModel: CurrentSituationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_situation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CurrentSituationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}