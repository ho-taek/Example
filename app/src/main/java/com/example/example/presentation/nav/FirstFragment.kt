package com.example.example.presentation.nav

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.example.R
import com.example.example.databinding.FragmentFirstBinding
import com.example.example.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding>(R.layout.fragment_first) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moveSecondFragment()
    }


    private fun moveSecondFragment(){
        binding.textFirstFragment.setOnClickListener {
            it.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
    }
}