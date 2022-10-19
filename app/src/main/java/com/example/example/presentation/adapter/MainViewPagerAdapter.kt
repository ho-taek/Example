package com.example.example.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.example.presentation.BeerFragment
import com.example.example.presentation.CoffeeFragment
import com.example.example.presentation.SojuFragment

class MainViewPagerAdapter(fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> CoffeeFragment()
            1 -> SojuFragment()
            else -> BeerFragment()
        }
    }
}