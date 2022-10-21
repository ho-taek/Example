package com.example.example.util

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.example.domain.PuzzleBoard
import com.example.example.presentation.adapter.PuzzleAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("imgBind")
    fun setImage(imageView: ImageView, image: Int) {
        Glide.with(imageView.context)
            .load(image)
            .transform(CenterCrop())
            .into(imageView)
    }

    @BindingAdapter(value = ["data", "onAdapterListener", "isSwappable"])
    @JvmStatic
    fun bindGeneralDragRecyclerView(
        view : RecyclerView,
        data: List<PuzzleBoard>?,
        onAdapterListener: PuzzleAdapter.OnAdapterListener,
        isSwappable: Boolean?,
    ){
        view.setHasFixedSize(true)
        data?.let{ items ->
            val adapter = view.adapter as? PuzzleAdapter
            adapter?.submitList(items) ?: run{
                view.adapter = PuzzleAdapter(isSwappable ?: false, onAdapterListener).apply {
                    submitList(items)
                    view.setOnDragListener(dragListener)
                }
            }
        }
    }

}