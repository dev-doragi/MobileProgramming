package com.example.project6_2

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("deprecation")
class MainActivity : AppCompatActivity() {
    lateinit var edtUrl : EditText
    lateinit var btnGo : Button
    lateinit var btnBack : Button
    lateinit var web : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.anajuoyo)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtUrl = findViewById<EditText>(R.id.edtUrl) // URL을 입력할 에디트 텍스트
        btnGo = findViewById<Button>(R.id.btnGo)
        btnBack = findViewById<Button>(R.id.btnBack)
        web = findViewById<WebView>(R.id.webView1)

        web.webViewClient = CookWebViewClient()

        var webSet = web.settings
        webSet.builtInZoomControls = true

        btnGo.setOnClickListener {
            web.loadUrl(edtUrl.text.toString())
        }

        btnBack.setOnClickListener {
            web.goBack()
        }
    }

    class CookWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}