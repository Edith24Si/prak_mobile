package com.example.nonaaapps.Home.pertemuan5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.nonaaapps.R
import com.example.nonaaapps.databinding.ActivityFifthBinding // Pastikan import binding ini sesuai

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inisialisasi ViewBinding
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root) // Menggunakan root dari binding

        // Penyesuaian Edge to Edge (menggunakan binding.root agar tidak perlu mencari R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 1. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Activity Fifth"
            subtitle = "Ini adalah subtitle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            // Mengatur icon back custom (pastikan ic_arrow_back sudah kamu buat)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }

        // 2. Setup onClick Listener ke WebViewActivity
        binding.btnWebView.setOnClickListener {
            // Membuka Activity WebView
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }
    }

    // 3. Menampilkan Option Menu di Toolbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    // 4. Menangani aksi saat menu atau tombol back diklik
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed() // Tombol back bawaan
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}