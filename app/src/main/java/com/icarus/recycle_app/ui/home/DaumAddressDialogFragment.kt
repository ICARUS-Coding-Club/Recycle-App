package com.icarus.recycle_app.ui.home

import android.annotation.TargetApi
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
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
import androidx.fragment.app.DialogFragment
import com.icarus.recycle_app.databinding.FragmentDaumAddressDialogBinding


class DaumAddressDialogFragment : DialogFragment() {

    private var _binding: FragmentDaumAddressDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var handler: Handler


    inner class JavaScriptInterface {
        @JavascriptInterface
        fun processDATA(data: String?) {
            val intent = Intent()
            intent.putExtra("data", data)
        }
    }


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
        _binding = FragmentDaumAddressDialogBinding.inflate(inflater, container, false)

        setupWebView()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupWebView() {
        true.also { binding.webView.settings.javaScriptEnabled = it }
        binding.webView.addJavascriptInterface(JavaScriptInterface(), "Android")
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                view?.evaluateJavascript("javascript:sample2_execDaumPostcode();", null)
            }
        }
        val blogspot = "https://wngnu.blogspot.com/p/api-window.html"
        binding.webView.loadUrl(blogspot)




    }

}
