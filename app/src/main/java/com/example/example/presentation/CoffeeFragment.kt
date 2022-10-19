package com.example.example.presentation

import android.os.Bundle
import android.view.View
import com.example.example.R
import com.example.example.presentation.base.BaseFragment
import com.example.example.databinding.FragmentCoffeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeFragment : BaseFragment<FragmentCoffeeBinding>(R.layout.fragment_coffee) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}