package com.icarus.recycle_app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainBottomSheetDialog : BottomSheetDialogFragment() {
    private var mListener: BottomSheetListener? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.dialog_main_fragment_bottom_sheet, container, false)
        val button1 = v.findViewById<Button>(R.id.button1)
        val button2 = v.findViewById<Button>(R.id.button2)
        button1.setOnClickListener { v1: View? ->
            mListener!!.onButtonClicked("Button 1 clicked")
            dismiss()
        }
        button2.setOnClickListener { v12: View? ->
            mListener!!.onButtonClicked("Button 2 clicked")
            dismiss()
        }
        return v
    }

    interface BottomSheetListener {
        fun onButtonClicked(text: String?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as BottomSheetListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
                        + " must implement BottomSheetListener"
            )
        }
    }
}