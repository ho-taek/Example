package com.example.example

import android.app.Activity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.example.base.BaseActivity
import com.example.example.databinding.ActivityMainBinding
import com.example.example.util.changeFragment
import com.example.example.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBottomNavigation()
    }


    //바텀 네비 세팅
    private fun initBottomNavigation(){
        binding.bottomNavMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_coffee -> {
                    changeFragment(R.id.fragment_container_main, CoffeeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_soju -> {
                    changeFragment(R.id.fragment_container_main, SojuFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    changeFragment(R.id.fragment_container_main, BeerFragment())
                    return@setOnItemSelectedListener true
                }

            }
        }
        binding.bottomNavMain.selectedItemId = R.id.menu_coffee
    }
}