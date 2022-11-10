package com.example.piechart.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes private val layoutRes : Int) : AppCompatActivity() {
    private var _binding : T? = null
    val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView<T>(this, layoutRes).apply {
            lifecycleOwner = this@BaseActivity
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}