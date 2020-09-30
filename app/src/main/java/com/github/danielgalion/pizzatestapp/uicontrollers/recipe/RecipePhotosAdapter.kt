package com.github.danielgalion.pizzatestapp.uicontrollers.recipe

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.danielgalion.pizzatestapp.MainApplication
import com.github.danielgalion.pizzatestapp.R
import com.github.danielgalion.pizzatestapp.uicontrollers.common.ClickableViewHolder
import com.github.danielgalion.pizzatestapp.uicontrollers.common.ItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_recipe_photos.view.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

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
                val bitmap = (view?.recipe_photo_iv?.drawable as BitmapDrawable).bitmap

                try {
                    val dir = File(Environment.getExternalStorageDirectory()?.absolutePath + "/DCIM/Camera")

                    if (!dir.isDirectory) {
                        dir.mkdirs()
                    }

                    val fileUri = dir.absolutePath + File.separator + System.currentTimeMillis() + ".jpg"
                    val outputStream = FileOutputStream(fileUri)

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    outputStream.flush()
                    outputStream.close()

                    Toast.makeText(
                        MainApplication.getInstance()?.applicationContext, "Pobrano zdjęcie",
                        Toast.LENGTH_LONG
                    ).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(
                        MainApplication.getInstance()?.applicationContext, "Nie można pobrać zdjęcia, sprawdź uprawnienia",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


}