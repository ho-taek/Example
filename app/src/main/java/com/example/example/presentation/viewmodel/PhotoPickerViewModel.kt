package com.example.example.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoPickerViewModel @Inject constructor(
    private val handle : SavedStateHandle
) : ViewModel() {
    private val COUNT_KEY = "count"

    private var count = handle.get<Int>(COUNT_KEY) ?: 0
        set(value){
            handle[COUNT_KEY] = value
            field = value
        }

    var countLive = handle.getLiveData<Int>(COUNT_KEY)

    fun plusCount(){
        ++count
    }
}