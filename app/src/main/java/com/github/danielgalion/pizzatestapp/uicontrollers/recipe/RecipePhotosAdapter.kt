package com.github.danielgalion.pizzatestapp.uicontrollers.recipe

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.danielgalion.pizzatestapp.R
import com.github.danielgalion.pizzatestapp.uicontrollers.common.ClickableViewHolder
import com.github.danielgalion.pizzatestapp.uicontrollers.common.ItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_recipe_photos.view.*

class RecipePhotosAdapter(private val context: Context, private val photoLinks: List<String>)
    : RecyclerView.Adapter<ClickableViewHolder>() {

    companion object {
        private const val TAG = "RecipePhotosAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClickableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.row_recipe_photos, parent, false)

        return ClickableViewHolder(row)
    }

    override fun onBindViewHolder(holder: ClickableViewHolder, position: Int) {
        Picasso.get().load(photoLinks[position]).into(holder.view.recipe_photo_iv)

        onClick(holder)
    }

    override fun getItemCount(): Int {
        return photoLinks.size
    }

    private fun onClick(holder: ClickableViewHolder) {
        holder.itemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View?) {

            }
        }
    }
}