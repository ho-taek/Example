package com.example.example.util

import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.example.R
import com.example.example.presentation.adapter.PuzzleAdapter
import timber.log.Timber

abstract class RecyclerViewDragAdapter<T, VH : RecyclerView.ViewHolder>(
    diffUtil : DiffUtilCallback<T>
) : ListAdapter<T, VH>(diffUtil){

    abstract val isSwappable : Boolean
    val dragListener = View.OnDragListener { view, event ->
        event?.let {
            if(it.action == DragEvent.ACTION_DROP){
                Timber.d("드래그 하는 중?")
                //현재 내가 드래그하는 RecyclerView -> localState가 이를 관찰
                val sourceView = it.localState as View
                val sourceRecyclerView = sourceView.parent as RecyclerView
                val sourcedAdapter = sourceRecyclerView.adapter as RecyclerViewDragAdapter<T, VH>
                val sourcePosition = sourceRecyclerView.getChildAdapterPosition(sourceView)
                Timber.d("드래그 sourcePosition $sourcePosition")
                //targetView -> 내가 드래그를 놓는 곳.
                view?.let { targetView ->
                    var targetRecyclerView : RecyclerView? = targetView as? RecyclerView
                    if(targetRecyclerView == null){
                        targetRecyclerView = targetView.parent as? RecyclerView
                    }
                    if(targetRecyclerView !is RecyclerView){
                        false
                    }

                    val targetAdapter = targetRecyclerView?.adapter as RecyclerViewDragAdapter<T, VH>
                    var targetPosition = if( targetView is RecyclerView){
                        val targetView = targetRecyclerView.findChildViewUnder(event.x,event.y)
                        val targetViewHolder = targetView?.let { it1 ->
                            targetRecyclerView.getChildViewHolder(
                                it1
                            )
                        }
                        Timber.d("드래그 뷰홀더 $targetViewHolder")
                        targetViewHolder?.adapterPosition!!
                    }else{
                        targetRecyclerView.getChildAdapterPosition(targetView)
                    }
                    Timber.d("드래그 제발 $targetPosition")
                    if (sourceRecyclerView.id == targetRecyclerView.id){
                        if(isSwappable){
                            if(targetPosition >= 0 && sourcedAdapter.currentList[targetPosition] != null){
                                if(targetPosition >= 0){
                                    //두개가 같은 경우 변경
                                    sourcedAdapter.onSwap(sourcePosition, targetPosition)
                                }else{}
                            }else{}
                        }else{}
                    }else{
                        try{
                            targetAdapter.currentList[targetPosition]?.let{ target ->
                                sourcedAdapter.onSet(
                                    targetPosition,
                                    sourcePosition,
                                    targetAdapter.currentList[targetPosition]
                                )
                            }
                            sourcedAdapter.onRemove(sourcedAdapter.currentList[sourcePosition])
                        }catch (e: IndexOutOfBoundsException){
                            sourcedAdapter.onRemove(sourcedAdapter.currentList[sourcePosition])
                        }
                    }
                } ?: run{
                    false
                }
            }
        }
        true
    }


    abstract fun onRemove(item : T)
    abstract fun onSet(targetIndex : Int, sourceIndex: Int, targetItem : T)
    abstract fun onSwap(from: Int, to : Int)


}