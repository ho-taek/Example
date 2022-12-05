package com.example.piechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.piechart.base.BaseActivity
import com.example.piechart.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPieChart()
    }

    private fun initPieChart(){
        binding.pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
        }
    }
}