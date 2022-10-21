package com.example.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.domain.PuzzleBoard
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.Comparator

@HiltViewModel
class MainVIewModel @Inject constructor() : ViewModel() {

    private var _boardImage = MutableLiveData<List<PuzzleBoard>>()
    val boardImage : LiveData<List<PuzzleBoard>>
        get() = _boardImage


    fun setDefaultPuzzleBoard(image : Int){
        _boardImage.value = mutableListOf(
            PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),
            PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),
            PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image),PuzzleBoard(true,image)
        )
    }


    private var _puzzleContentImage = MutableLiveData<List<PuzzleBoard>>()
    val puzzleContentImage : LiveData<List<PuzzleBoard>>
        get() = _puzzleContentImage

    fun setDefaultPuzzleContent(image : Int){
        _puzzleContentImage.value = mutableListOf(
            PuzzleBoard(false,image),PuzzleBoard(false,image),PuzzleBoard(false,image),PuzzleBoard(false,image),
            PuzzleBoard(false,image),PuzzleBoard(false,image),PuzzleBoard(false,image),PuzzleBoard(false,image),
            PuzzleBoard(false,image),PuzzleBoard(false,image),PuzzleBoard(false,image),PuzzleBoard(false,image)
        )
    }

    fun setPuzzleContent(image : List<PuzzleBoard>){
        _puzzleContentImage.value = image
    }

    private var _bitmapPuzzle = MutableLiveData<List<PuzzleBoard>>()
    val bitmapPuzzle : LiveData<List<PuzzleBoard>>
        get() = _bitmapPuzzle

    fun setBitmapPuzzle(item : List<PuzzleBoard>){
        val bitmapPuzzleTemp = MutableLiveData<MutableList<PuzzleBoard>>()
        bitmapPuzzleTemp.value?.addAll(item)

    }
    fun onSet(targetIndex: Int, sourceIndex: Int, targetItem: PuzzleBoard) {
        val topTempData = _boardImage.value?.toMutableList()
        val bottomTempData = _puzzleContentImage.value?.toMutableList()

        if (targetItem.isTop) {
            val item = bottomTempData?.get(sourceIndex)?.copy(isTop = true)
            topTempData?.let {
                if (item != null) {
                    it[targetIndex] = item
                }
            }
        } else {
            val item = topTempData?.get(targetIndex)?.copy(isTop = false)
            bottomTempData?.let {
                if (item != null) {
                    it[sourceIndex] = item
                }
            }
        }
        _puzzleContentImage.value = bottomTempData?.toList()
        _boardImage.value = topTempData?.toList()
    }

    fun onRemove(item: PuzzleBoard) {
        if (item.isTop) {
            _boardImage.value?.let { topData ->
                val topTempData = topData.toMutableList()
                val bottomTempData = _puzzleContentImage.value?.toMutableList()
                _boardImage.value = topTempData.toList()
                bottomTempData?.add(item.copy(isTop = false))
                _puzzleContentImage.value = bottomTempData?.toList()
            }
        } else {
            _puzzleContentImage.value?.let { bottomData ->
                val topTempData = _boardImage.value?.toMutableList()
                val bottomTempData = bottomData.toMutableList()
                bottomTempData.remove(item)
                _puzzleContentImage.value = bottomTempData.toList()
                for (i in topTempData?.indices!!) {
                    if (topTempData[i] == null) {
                        topTempData[i] = item.copy(isTop = true)
                        break
                    }
                }

                _boardImage.value = topTempData.toList()
            }
        }
    }



    fun onSwap(isRed: Boolean, from: Int, to: Int) {
        if (isRed) {
            _boardImage.value?.let {
                val data = it.toMutableList()
                Collections.swap(data, from, to)
                _boardImage.value = data.toList()
            }
        } else {
            _puzzleContentImage.value?.let {
                val data = it.toMutableList()
                Collections.swap(data, from, to)
                _puzzleContentImage.value = data.toList()
            }
        }
    }


    companion object {
        const val TOP_DATA_MAX_SIZE = 3
    }

}