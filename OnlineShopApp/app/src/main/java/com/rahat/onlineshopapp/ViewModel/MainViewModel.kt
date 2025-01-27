package com.rahat.onlineshopapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rahat.onlineshopapp.Model.CategoryModel
import com.rahat.onlineshopapp.Model.ItemsModel
import com.rahat.onlineshopapp.Repository.MainRepository

class MainViewModel:ViewModel() {

    private val repository = MainRepository()

    val category:LiveData<MutableList<CategoryModel>> = repository.loadCategory()
    val bestSeller: LiveData<MutableList<ItemsModel>> = repository.loadBestSeller()

    fun loadItems(categoryId: String):LiveData<MutableList<ItemsModel>> {
        return repository.loadItems(categoryId)
    }
}