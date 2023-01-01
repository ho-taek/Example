package com.example.example.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.viewModels
import com.example.example.R
import com.example.example.databinding.ActivityMainBinding
import com.example.example.domain.PuzzleBoard
import com.example.example.presentation.adapter.PuzzleAdapter
import com.example.example.presentation.base.BaseActivity
import com.example.example.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), PuzzleAdapter.OnAdapterListener {
    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var puzzleAdapter: PuzzleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPuzzleBoard()
        setImage()
    }

    //보드 이미지
    private fun initPuzzleBoard() {
        binding.viewmodel = mainViewModel
        binding.onAdapterListener = this
        mainViewModel.setDefaultPuzzleBoard(R.drawable.ic_launcher_background)
        mainViewModel.setDefaultPuzzleContent(R.drawable.character)
        Timber.d("기본 사진 ${R.drawable.character}")
    }



    //이미지 자르기
    private fun setImage() :List<PuzzleBoard> {

        val option = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.character,option)
        val bitMapHeight = option.outHeight
        val bitMapWidth = option.outWidth
        val bitMapHeightDiv = bitMapHeight/16
        val bitMapWidthDiv = bitMapWidth/16
        var divHeight = 0
        var divWidth = 0
        val divList = mutableListOf<PuzzleBoard>()
        for(i in 1..16){
            val drawable = BitmapDrawable(resources,Bitmap.createBitmap(bitmap,divWidth,divHeight,bitMapWidthDiv,bitMapHeightDiv))

            divList.add(PuzzleBoard(false,Bitmap.createBitmap(bitmap,divWidth,divHeight,bitMapWidthDiv,bitMapHeightDiv).rowBytes))
            divHeight += bitMapWidthDiv
            divWidth += bitMapHeightDiv
        }

        Timber.d("사진 크기 ${bitMapHeight}")
        return divList
    }


    override fun onRemove(itemInfo: PuzzleBoard) {
        mainViewModel.onRemove(itemInfo)
    }

    override fun onSet(targetIndex: Int, sourceIndex: Int, itemInfo: PuzzleBoard) {
        mainViewModel.onSet(targetIndex,sourceIndex,itemInfo)
    }

    override fun onSwap(isTop: Boolean, from: Int, to: Int) {
        mainViewModel.onSwap(isTop,from,to)
    }
}