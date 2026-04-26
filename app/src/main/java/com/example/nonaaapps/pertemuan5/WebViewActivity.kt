package com.example.nonaaapps.pertemuan5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// Pastikan import binding ini sesuai dengan nama file layout XML-mu
import com.example.nonaaapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    // Deklarasi ViewBinding
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi ViewBinding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Penyesuaian Edge to Edge (menggunakan binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Mengaktifkan Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Bina Desa"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 2. Mengatur WebView
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        val url = intent.getStringExtra("url") ?: "https://nona-produkhukumbinadesa.alwaysdata.net"

        binding.webView.loadUrl(url)

        // 3. Agar Toolbar hide/show saat web di-scroll
        binding.webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                binding.appBar.setExpanded(false, true) // sembunyikan toolbar
            } else if (scrollY < oldScrollY) {
                binding.appBar.setExpanded(true, true) // tampilkan toolbar
            }
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    // 5. Mengaktifkan fungsi tombol panah back (kembali) di Toolbar atas
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}