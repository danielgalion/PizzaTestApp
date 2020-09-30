package com.github.danielgalion.pizzatestapp.uicontrollers.recipe

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.danielgalion.pizzatestapp.R
import com.github.danielgalion.pizzatestapp.uicontrollers.common.ClickableViewHolder
import com.github.danielgalion.pizzatestapp.uicontrollers.common.ItemClickListener
import com.github.danielgalion.pizzatestapp.utils.savePhoto
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_recipe_photos.view.*

class RecipePhotosAdapter(private val context: Context, private val photoLinks: List<String>) :
    RecyclerView.Adapter<ClickableViewHolder>() {

    companion object {
        private const val TAG = "RecipePhotosAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClickableViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val row = inflater.inflate(R.layout.row_recipe_photos, parent, false)

        return ClickableViewHolder(row)
    }

    override fun onBindViewHolder(holder: ClickableViewHolder, position: Int) {
        Picasso.get()
            .load(photoLinks[position])
            .placeholder(R.drawable.pizza1)
            .into(holder.view.recipe_photo_iv)

        onClick(holder)
    }

    override fun getItemCount(): Int {
        return photoLinks.size
    }

    private fun onClick(holder: ClickableViewHolder) {
        holder.itemClickListener = object : ItemClickListener {
            override fun onItemClick(view: View?) {
                askToSavePhoto(view)
            }
        }
    }

    private fun askToSavePhoto(view: View?) {
        val alertDialog = AlertDialog.Builder(context).create()

        alertDialog.setMessage("Czy chcesz pobrać zdjęcie?")
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Tak") { dialog, _ ->
            savePhoto(view?.recipe_photo_iv)
            dialog.dismiss()
        }
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Nie") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.show()
    }
}