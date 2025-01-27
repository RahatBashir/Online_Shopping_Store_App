package com.rahat.onlineshopapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.rahat.onlineshopapp.Model.CategoryModel
import com.rahat.onlineshopapp.Model.ItemsModel

class MainRepository {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadCategory():LiveData<MutableList<CategoryModel>>{
        val categoryLiveData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("Category")

        ref.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }
                categoryLiveData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return categoryLiveData
    }

    fun loadBestSeller():LiveData<MutableList<ItemsModel>>{
        val bestSellerLiveData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("BestSeller")

        ref.addValueEventListener( object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                for (childSnapshot in snapshot.children){
                    val list = childSnapshot.getValue(ItemsModel::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }
                bestSellerLiveData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return bestSellerLiveData
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>> {
        val itemsLiveData = MutableLiveData<MutableList<ItemsModel>>()
        val ref = firebaseDatabase.getReference("Items")

        val query: Query = ref.orderByChild("categoryId").equalTo(categoryId)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemsModel>()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        val item = childSnapshot.getValue(ItemsModel::class.java)
                        if (item != null) {
                            lists.add(item)
                        }
                    }
                    itemsLiveData.value = lists
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MainRepository", "Error loading items: ${error.message}")
            }
        })
        return itemsLiveData
    }

}