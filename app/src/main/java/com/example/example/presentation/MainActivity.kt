package com.example.example.presentation

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.example.R
import com.example.example.presentation.adapter.MainViewPagerAdapter
import com.example.example.presentation.base.BaseActivity
import com.example.example.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var mainViewPagerAdapter: MainViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
        initBottomNavigation()
    }

    private fun initViewPager(){
        mainViewPagerAdapter = MainViewPagerAdapter(this)
        with(binding.viewpagerMain){
            adapter = mainViewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNavMain.selectedItemId = when(position){
                        0 -> R.id.menu_coffee
                        1 -> R.id.menu_soju
                        else -> R.id.menu_beer
                    }
                }
            })
        }


    }

    //바텀 네비 세팅
    private fun initBottomNavigation(){
        binding.bottomNavMain.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_coffee -> {
                    binding.viewpagerMain.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.menu_soju -> {
                    binding.viewpagerMain.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> {
                    binding.viewpagerMain.currentItem = 2
                    return@setOnItemSelectedListener true
                }

            }
        }
        binding.bottomNavMain.selectedItemId = R.id.menu_coffee
    }
}