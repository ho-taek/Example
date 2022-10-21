package com.example.example.presentation.adapter

import android.content.ClipData
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example.databinding.ItemPuzzleBinding
import com.example.example.databinding.ItemPuzzleBoardBinding
import com.example.example.domain.PuzzleBoard
import com.example.example.util.DiffUtilCallback
import com.example.example.util.RecyclerViewDragAdapter
import timber.log.Timber

class PuzzleAdapter(
    override val isSwappable: Boolean,
    private val onAdapterListener: OnAdapterListener
) : RecyclerViewDragAdapter<PuzzleBoard, RecyclerView.ViewHolder>(DiffUtilCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            TOP -> {
                PuzzleBoardViewHolder(
                    ItemPuzzleBoardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
               PuzzleViewHolder(
                    ItemPuzzleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            TOP -> {

                (holder as PuzzleBoardViewHolder).bind(getItem(position))
                holder.binding.root.setOnClickListener {
                    Timber.d("드래그 drop 호출")
                }
                holder.binding.root.setOnLongClickListener { view ->
                    val data = ClipData.newPlainText("", "")
                    val shadowBuilder = View.DragShadowBuilder(view)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        view?.startDragAndDrop(data, shadowBuilder, view, 0)
                    }
                    false
                }
            }else -> {
            (holder as PuzzleViewHolder).bind(getItem(position))
            holder.binding.root.setOnLongClickListener { view ->
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(view)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view?.startDragAndDrop(data, shadowBuilder, view, 0)
                }
                false
            }
            }
        }

    }


    class PuzzleViewHolder(
        val binding: ItemPuzzleBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(puzzle: PuzzleBoard) {
            with(binding) {
                this.puzzle = puzzle
                executePendingBindings()

            }
        }
    }

    class PuzzleBoardViewHolder(
        val binding: ItemPuzzleBoardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(puzzleBoard: PuzzleBoard) {
            with(binding) {
                this.puzzleBoard = puzzleBoard
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].isTop) TOP
        else BOTTOM
    }


    override fun onRemove(item: PuzzleBoard) {
        onAdapterListener.onRemove(item)
    }

    override fun onSet(targetIndex: Int, sourceIndex: Int, targetItem: PuzzleBoard) {
        onAdapterListener.onSet(targetIndex,sourceIndex,targetItem)
    }

    override fun onSwap(from: Int, to: Int) {
        if(currentList.any{ it.isTop}){
            onAdapterListener.onSwap(true, from, to)
        }else{
            onAdapterListener.onSwap(false, from, to)
        }
    }

    interface OnAdapterListener {
        fun onRemove(itemInfo: PuzzleBoard)
        fun onSet(targetIndex: Int, sourceIndex: Int, itemInfo: PuzzleBoard)
        fun onSwap(isTop: Boolean, from: Int, to: Int)
    }

    companion object {
        private const val TOP = 0
        private const val BOTTOM = 1
    }
}