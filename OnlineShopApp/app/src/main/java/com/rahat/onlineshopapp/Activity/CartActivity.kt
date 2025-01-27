package com.rahat.onlineshopapp.Activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahat.onlineshopapp.Adapter.CartAdapter
import com.rahat.onlineshopapp.Helper.ChangeNumberItemsListener
import com.rahat.onlineshopapp.Helper.ManagmentCart
import com.rahat.onlineshopapp.R
import com.rahat.onlineshopapp.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding:ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()

    }

    private fun initCartList() {
        binding.cartView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.cartView.adapter = CartAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                calculateCart()
            }

        })
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery  = 15.0
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/100.0
        val total = Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100
        val itemTotal = Math.round(managmentCart.getTotalFee()*100)/100


        with(binding){
            totalFeeTxt.text = "$${itemTotal}"
            textTxt.text = "$$tax"                          //its taxTxt instead textTxt
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener { finish() }
    }
}