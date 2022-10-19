package com.example.example

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.example.base.BaseFragment
import com.example.example.databinding.FragmentCoffeeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoffeeFragment : BaseFragment<FragmentCoffeeBinding>(R.layout.fragment_coffee) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}