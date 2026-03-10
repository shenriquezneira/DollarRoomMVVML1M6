package com.example.dolarroommvvml1m6.View

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.dolarroommvvml1m6.R
import com.example.dolarroommvvml1m6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val firstFragment = FirstFragment()

        if( savedInstanceState == null){
            // 🔹 Agregamos el Fragment al layout usando commitNow() para que sea sincrónico

            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main,firstFragment)
                .commitNow()

        }


    }





}