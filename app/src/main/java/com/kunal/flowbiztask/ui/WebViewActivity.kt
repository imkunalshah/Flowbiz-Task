package com.kunal.flowbiztask.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kunal.flowbiztask.R
import com.saksham.customloadingdialog.hideDialog
import com.saksham.customloadingdialog.showDialog

class WebViewActivity : AppCompatActivity() {
    lateinit var webView:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.webView)
        webView.loadUrl(intent.extras?.getString("link").toString())

        showDialog(
            this,               //context or this
            false,          //dismiss dialog onBackPressed
            R.raw.load               //lottie file json stored in res/raw
        )

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                hideDialog()
            }
        }
    }
}