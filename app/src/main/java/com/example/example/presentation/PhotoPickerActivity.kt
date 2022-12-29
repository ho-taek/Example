package com.example.example.presentation

import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.example.R
import com.example.example.databinding.ActivityPhotoPickerBinding
import com.example.example.presentation.base.BaseActivity
import timber.log.Timber

class PhotoPickerActivity : BaseActivity<ActivityPhotoPickerBinding>(R.layout.activity_photo_picker) {
    private val pickMedia  = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){ uri ->
        if(uri != null){
            Timber.d("Selected URI: $uri")
        }else{
            Timber.d("Selected URI: no!")
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        clickButton()
    }

    private fun clickButton(){
        binding.buttonPhotoPicker.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }
    }
}