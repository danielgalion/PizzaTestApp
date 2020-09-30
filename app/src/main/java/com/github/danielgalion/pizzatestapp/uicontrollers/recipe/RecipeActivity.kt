package com.github.danielgalion.pizzatestapp.uicontrollers.recipe

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.AccessToken
import com.facebook.Profile
import com.github.danielgalion.pizzatestapp.R
import com.github.danielgalion.pizzatestapp.models.Recipe
import com.github.danielgalion.pizzatestapp.network.FetcherViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "RecipeActivity"
    }

    private val fetcherViewModel: FetcherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        showFbData()
        fetcherViewModel.getRecipe()?.observe(this, { showRecipe(it) })
        fetcherViewModel.fetchRecipe()
        setUpTopBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) onBackPressed()

        return super.onOptionsItemSelected(item)
    }

    private fun showFbData() {
        val accessToken = AccessToken.getCurrentAccessToken()

        if (accessToken != null && !accessToken.isExpired) {
            fb_info_bottom_layout.visibility = View.VISIBLE
            fb_name_tv.text = getString(R.string.fb_name, Profile.getCurrentProfile().name)
            Picasso.get().load(Profile.getCurrentProfile().getProfilePictureUri(20, 20))
                .into(fb_photo_iv)
        } else {
            fb_info_bottom_layout.visibility = View.GONE
        }
    }

    private fun showRecipe(recipe: Recipe) {
        pizza_desc_tv.text = recipe.description
        ingredients_desc_tv.text = recipe.ingredientsStr
        showPreparing(recipe)
        showImages(recipe)
    }

    private fun showPreparing(recipe: Recipe) {
        preparing_desc_rv.layoutManager = LinearLayoutManager(this)
        preparing_desc_rv.adapter = RecipeStepsAdapter(this, recipe.preparing)
        ViewCompat.setNestedScrollingEnabled(preparing_desc_rv, false)
    }

    private fun showImages(recipe: Recipe) {
        images_rv.layoutManager = GridLayoutManager(this, 2)
        images_rv.adapter = RecipePhotosAdapter(this, recipe.imgs)
        ViewCompat.setNestedScrollingEnabled(images_rv, false)
    }

    private fun setUpTopBar() {
        title = "Pizza Recipe!"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}