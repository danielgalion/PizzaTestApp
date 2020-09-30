package com.github.danielgalion.pizzatestapp.uicontrollers

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.danielgalion.pizzatestapp.R
import com.github.danielgalion.pizzatestapp.network.FetcherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val fetcherViewModel: FetcherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLogo()
        title = "RecipeMaster"
    }

    private fun showLogo() {
        logo_iv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pizza1))
    }

    fun onRecipeFabClick(view: View) {
//        startActivity(Intent(this, RecipeActivity::class.java))

        fetcherViewModel.fetchRecipe()
    }
}