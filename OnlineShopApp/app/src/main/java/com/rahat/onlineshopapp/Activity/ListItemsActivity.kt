package com.rahat.onlineshopapp.Activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahat.onlineshopapp.Adapter.ListItemsAdapter
import com.rahat.onlineshopapp.ViewModel.MainViewModel
import com.rahat.onlineshopapp.databinding.ActivityListItemsBinding

class ListItemsActivity : BaseActivity() {

    private lateinit var binding: ActivityListItemsBinding
    private val viewModel = MainViewModel()
    private var id: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getBundle()
        initList()

    }

    private fun initList() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            id?.let {
                viewModel.loadItems(it).observe(this@ListItemsActivity, Observer {
                    listView.layoutManager =
                        LinearLayoutManager(this@ListItemsActivity,LinearLayoutManager.VERTICAL,false)
                    listView.adapter = ListItemsAdapter(it)
                    progressBar.visibility = View.GONE
                })
            }

            backBtn.setOnClickListener { finish() }
        }
    }

    private fun getBundle() {
        id = intent.getStringExtra("id")!!
        title = intent.getStringExtra("title")!!

        binding.categoryTxt.text = title

    }
}

//    private fun retrieveIntentData() {
//        id = intent.getStringExtra("id")
//        title = intent.getStringExtra("title")
//        binding.categoryTxt.text = title ?: "No Title Available"
//    }
//
//    private fun initList() {
//        binding.progressBar.visibility = View.VISIBLE
//        id?.let { categoryId ->
//            viewModel.loadItems(categoryId).observe(this, Observer { itemList ->
//                if (itemList.isNotEmpty()) {
//                    binding.listView.layoutManager = LinearLayoutManager(
//                        this,
//                        LinearLayoutManager.VERTICAL,
//                        false
//                    )
//                    binding.listView.adapter = ListItemsAdapter(itemList)
//                } else {
//                    // Show a message if no items are found
//                    binding.emptyView.visibility = View.VISIBLE
//                    binding.listView.visibility = View.GONE
//                }
//                binding.progressBar.visibility = View.GONE
//            })
//        } ?: run {
//            // If no categoryId is passed
//            binding.progressBar.visibility = View.GONE
//            Log.d("ListItemsActivity", "Category ID is null")
//        }
//    }
//
//    private fun setupUI() {
//        binding.backBtn.setOnClickListener { finish() }
//    }
//}
