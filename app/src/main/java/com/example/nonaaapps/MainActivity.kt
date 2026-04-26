package com.example.nonaaapps

import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD
=======
import android.util.Log
import androidx.activity.enableEdgeToEdge
>>>>>>> origin/main
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nonaaapps.databinding.ActivityMainBinding
import com.example.nonaaapps.pertemuan4.FourthActivity
<<<<<<< HEAD
import com.example.nonaaapps.pertemuan5.WebViewActivity
import com.example.nonaaapps.pertemuan6.AuthActivity
=======
import com.example.nonaaapps.pertemuan_3.ThirdResultActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
>>>>>>> origin/main

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
<<<<<<< HEAD
=======
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.btnToFourth.setOnClickListener {
>>>>>>> origin/main

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Portal Regulasi Desa"

        // Tombol ke FourthActivity (yang sudah ada sebelumnya)
        binding.btnToFourth.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
<<<<<<< HEAD
=======



>>>>>>> origin/main
        }
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, _ ->
                    val editor = sharedPref.edit()
                    editor.clear()
                    editor.apply()

<<<<<<< HEAD
        // Tombol WebView
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra("url", "https://nona-produkhukumbinadesa.alwaysdata.net")
            startActivity(intent)
        }

        // Tombol Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->
                    getSharedPreferences("RegulasiDesaPref", MODE_PRIVATE)
                        .edit().clear().apply()
                    startActivity(Intent(this, AuthActivity::class.java))
=======
                    dialog.dismiss()



                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
>>>>>>> origin/main
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}