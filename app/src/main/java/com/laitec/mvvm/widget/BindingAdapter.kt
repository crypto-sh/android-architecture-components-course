package com.laitec.mvvm.widget

import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.laitec.mvvm.adapter.RvAdapterRepo

class BindingAdapter {

    companion object {
        @JvmStatic
        @BindingAdapter("showOrGone")
        fun ProgressBar.setVisibilityProgress(showing : Boolean) {
            this.visibility = if (showing) View.VISIBLE else View.INVISIBLE
        }

        @JvmStatic
        @BindingAdapter("loadImage")
        fun AppCompatImageView.setImageUrl(url : String) {
            //TODO load image by library
        }

        @JvmStatic
        @BindingAdapter("rvAdapter")
        fun RecyclerView.setAdapter(adapter : RvAdapterRepo?) {
            if (adapter != null)
                this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)
        }

    }

}