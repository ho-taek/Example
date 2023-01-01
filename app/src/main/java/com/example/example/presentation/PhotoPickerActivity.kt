package com.example.example.presentation

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.example.R
import com.example.example.databinding.ActivityPhotoPickerBinding
import com.example.example.presentation.base.BaseActivity
import com.example.example.presentation.viewmodel.PhotoPickerViewModel
import timber.log.Timber

class PhotoPickerActivity : BaseActivity<ActivityPhotoPickerBinding>(R.layout.activity_photo_picker) {

    private val photoPickerViewModel : PhotoPickerViewModel by viewModels()


    private val pickMedia  = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if(uri != null){
            Timber.d("Selected URI: $uri")
        }else{
            Timber.d("Selected URI: no!")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("0707 onSaved $savedInstanceState")
        Timber.d("0707 onCreate")
        clickButton()
        clickText()
    }

    override fun onResume() {
        super.onResume()
        Timber.d("0707 onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("0707 onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("0707 onStop")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.d("0707 onRestoreInstance")
    }

    private fun clickButton(){
        binding.buttonPhotoPicker.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }

    private fun clickText(){
        photoPickerViewModel.countLive.observe(this){
            binding.textCount.text = it.toString()
        }
        binding.textCount.setOnClickListener {
            photoPickerViewModel.plusCount()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
    }
}