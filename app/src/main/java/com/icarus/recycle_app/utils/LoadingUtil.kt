package com.icarus.recycle_app.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.databinding.CvLoadingDialogBinding

class LoadingUtil(context: Context): Dialog(context) {
    private lateinit var binding: CvLoadingDialogBinding

    private lateinit var loadingTitle: String
    private lateinit var loadingInfo: String
    private lateinit var loadingInfoContents: List<String>
    private lateinit var viewList: List<View>
    private var arrowUp: Int = 0
    private var arrowDown: Int = 0

    private var isInfoClicked = false

    private lateinit var recyclerAdapter: LoadingInfoRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CvLoadingDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViewProperty()
        initListener()

        recyclerAdapter = LoadingInfoRecyclerAdapter(loadingInfoContents)

        binding.rvLoadingInfoContent.adapter = recyclerAdapter
        binding.rvLoadingInfoContent.setHasFixedSize(true)

    }

    private fun initListener() {
        binding.cvCenter.setOnClickListener {
            isInfoClicked = !isInfoClicked

            if (isInfoClicked) {
                binding.ivLoadingInfoTitle.setBackgroundResource(arrowUp)
                binding.tvLoadingInfoTitle.visibility = View.VISIBLE
                binding.cvBottom.visibility = View.VISIBLE
            } else {
                binding.ivLoadingInfoTitle.setBackgroundResource(arrowDown)
                binding.tvLoadingInfoTitle.visibility = View.GONE
                binding.cvBottom.visibility = View.GONE
            }
        }
    }

    private fun initViewProperty() {
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.tvLoadingInfoTitle.visibility = View.GONE
        binding.cvBottom.visibility = View.GONE
    }

    fun initUtil(
        loadingTitle: String,
        loadingInfo: String,
        loadingInfoContents: List<String>,
        viewList: List<View>,
        arrowDown: Int,
        arrowUp: Int
    ) {
        this.loadingTitle = loadingTitle
        this.loadingInfo = loadingInfo
        this.loadingInfoContents = loadingInfoContents
        this.viewList = viewList
        this.arrowDown = arrowDown
        this.arrowUp = arrowUp
    }

    fun changeVisibility(visibility: Int) {
        for (view in viewList) {
            view.visibility = visibility
        }
    }

    fun show(loadingTitle: String) {
        super.show()
        binding.tvLoadingMessage.text = loadingTitle
    }


    inner class LoadingInfoRecyclerAdapter(
        private val loadingInfoContents: List<String>
    ): RecyclerView.Adapter<LoadingInfoRecyclerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): LoadingInfoRecyclerAdapter.ViewHolder {
            val textView = TextView(parent.context).apply {
                val dp = 10f
                val px = (dp * context.resources.displayMetrics.density + 0.5f).toInt()

                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(px, px, px, px)
                }
            }
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(
            holder: LoadingInfoRecyclerAdapter.ViewHolder,
            position: Int
        ) {
            holder.textView.text = loadingInfoContents[position]
        }

        override fun getItemCount(): Int = loadingInfoContents.size

        inner class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    }

}