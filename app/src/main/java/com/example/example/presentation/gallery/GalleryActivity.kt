package com.example.example.presentation.gallery

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import androidx.core.content.ContextCompat
import com.example.example.R
import com.example.example.databinding.ActivityGalleryBinding
import com.example.example.presentation.base.BaseActivity

class GalleryActivity : BaseActivity<ActivityGalleryBinding>(R.layout.activity_gallery) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkGalleryPermission()
    }

    private fun checkGalleryPermission(){
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
        } else {
            requestGalleryPermission()
        }
    }

    private fun requestGalleryPermission(){
        ActivityCompat.requestPermissions(this,arrayOf(
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ), REQ_GALLERY)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQ_GALLERY -> {
                if(grantResults.isEmpty()){
                    throw RuntimeException("Empty permission result")
                }
                if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                    if(shouldShowRequestPermissionRationale(this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE
                        )){
                        requestGalleryPermission()
                    }else{
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null))
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    companion object{
        const val REQ_GALLERY = 1
    }
}