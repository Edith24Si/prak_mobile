package com.example.nonaaapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nonaaapps.AuthActivity
import com.example.nonaaapps.Home.pertemuan10.TenthActivity
import com.example.nonaaapps.Home.pertemuan2.SecondActivity
import com.example.nonaaapps.Home.pertemuan4.FourthActivity
import com.example.nonaaapps.Home.pertemuan5.FifthActivity
import com.example.nonaaapps.Home.pertemuan7.SevenActivity
import com.example.nonaaapps.Home.pertemuan9.NinthActivity
import com.example.nonaaapps.Home.pertemuan_3.ThirdActivity
import com.example.nonaaapps.Home.photo.PhotoAdapter
import com.example.nonaaapps.data.api.CatFactApiClient
import com.example.nonaaapps.data.api.PhotoApiClient
import com.example.nonaaapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /** Ganti menjadi versi binding */
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        binding.btnP2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        binding.btnP3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }
        binding.btnP4.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }
        binding.btnP5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }
        binding.btnP6.setOnClickListener {
            startActivity(Intent(requireContext(), AuthActivity::class.java))
        }
        binding.btnP7.setOnClickListener {
            startActivity(Intent(requireContext(), SevenActivity::class.java))
        }

        binding.btnP9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }
        binding.btnP10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }
        binding.btnP13.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.nonaaapps.Home.pertemuan_13.ThirteenthActivity::class.java))
        }
        binding.bntLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Ya") { dialog, _ ->
                    sharedPref.edit() {
                        clear()
                        apply()
                    }
                    requireActivity().finish()
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")

                }
                .show()
        }

        binding.btnRefresh.setOnClickListener {
            loadCatFact()
        }

        loadCatFact()
        loadPhoto()

    }

    private fun loadCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal mengambil fakta kucing."
            }
        }
    }

    private fun loadPhoto() {
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                val adapter = PhotoAdapter(photos)
                binding.rvGallery.adapter = adapter

                /** List Tampil Vertical*/
//                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())

                /** List Tampil Horizontal */
//                binding.rvGallery.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

                /** List Tampil Grid */
                binding.rvGallery.layoutManager = GridLayoutManager(requireContext(), 2)

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



