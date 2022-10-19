package com.example.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private var _calculator = MutableLiveData(0)
    val calculator : LiveData<Int>
        get() = _calculator

    fun setCalculator(value : Int){
        _calculator.value = value

    }

}