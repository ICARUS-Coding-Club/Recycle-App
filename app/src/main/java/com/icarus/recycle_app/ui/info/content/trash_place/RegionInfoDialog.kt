package com.icarus.recycle_app.ui.info.content.trash_place

import android.annotation.SuppressLint
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.PermissionRequest
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.RecyclerView
import com.icarus.recycle_app.adapters.RegionInfoAdapter
import com.icarus.recycle_app.databinding.FragmentDaumAddressDialogBinding
import com.icarus.recycle_app.dto.RegionInfo
import com.icarus.recycle_app.dto.RegionTrashPlaceInfo
import com.icarus.recycle_app.listener.OnItemClickListener
import com.icarus.recycle_app.utils.ServerConnectHelper

class RegionInfoDialog(private val regionInfoList: ArrayList<RegionInfo>) : DialogFragment() {

    private lateinit var linearLayout: LinearLayout



    override fun onStart() {
        super.onStart()
        val dialog = dialog

        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        linearLayout = LinearLayout(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL


            val adapter = RegionInfoAdapter(regionInfoList)
            adapter.listener = object : OnItemClickListener {
                override fun onClick(id: Int) {
                    val serverConnection = ServerConnectHelper()
                    serverConnection.requestRegionTrashPlaceInfo = object : ServerConnectHelper.RequestRegionTrashPlaceInfo {
                        override fun onSuccess(regionTrashPlaceInfo: RegionTrashPlaceInfo) {
                            Log.d("asd", "$regionTrashPlaceInfo 도착 ")
                        }

                        override fun onFailure() {
                            Log.d("asd", "실패")
                        }

                    }
                    serverConnection.getRegionTrashPlaceInfo(id)
                }
            }

            val recyclerView = RecyclerView(context)
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)

            addView(recyclerView)

        }

        return linearLayout
    }




}