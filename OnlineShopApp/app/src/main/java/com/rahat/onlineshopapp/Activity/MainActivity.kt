package com.rahat.onlineshopapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahat.onlineshopapp.Adapter.BestSellerAdapter
import com.rahat.onlineshopapp.Adapter.CategoryAdapter
import com.rahat.onlineshopapp.ViewModel.MainViewModel
import com.rahat.onlineshopapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategories()
        initBestSeller()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }
    }

    private fun initCategories() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.ViewCategory.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            binding.ViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
    }

    private fun initBestSeller() {
        binding.progressBarBestSeller.visibility = View.VISIBLE
        viewModel.bestSeller.observe(this, Observer {
            binding.ViewBestSeller.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            binding.ViewBestSeller.adapter = BestSellerAdapter(it)
            binding.progressBarBestSeller.visibility = View.GONE
        })
    }
}


