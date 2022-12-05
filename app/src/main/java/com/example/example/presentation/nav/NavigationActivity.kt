package com.example.example.presentation.nav

import android.os.Bundle
import com.example.example.R
import com.example.example.databinding.ActivityNavigationBinding
import com.example.example.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : BaseActivity<ActivityNavigationBinding>(R.layout.activity_navigation) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}