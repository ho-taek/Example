package com.example.example.presentation

import android.os.Bundle
import android.view.View
import com.example.example.R
import com.example.example.presentation.base.BaseFragment
import com.example.example.databinding.FragmentBeerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerFragment : BaseFragment<FragmentBeerBinding>(R.layout.fragment_beer) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}