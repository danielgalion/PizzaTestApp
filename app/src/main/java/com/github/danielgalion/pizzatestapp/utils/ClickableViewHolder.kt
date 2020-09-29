package com.github.danielgalion.pizzatestapp.utils

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ClickableViewHolder(val view: View, vararg clickableViews: View)
    : RecyclerView.ViewHolder(view), View.OnClickListener {

    companion object {
        private const val TAG = "ClickableViewHolder"
    }

    var itemClickListener: ItemClickListener? = null

    init {
        view.setOnClickListener(this)

        for(element in clickableViews) {
            element.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        try {
            itemClickListener?.onItemClick(view)
        } catch (exception: NullPointerException) {
            Log.e(TAG, "Probably itemClickListener, or a view is null. @onClick(..)")
        }
    }
}