package com.rahat.onlineshopapp.Activity

import android.content.Intent
import android.os.Bundle
import com.rahat.onlineshopapp.R
import com.rahat.onlineshopapp.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        
    }
}