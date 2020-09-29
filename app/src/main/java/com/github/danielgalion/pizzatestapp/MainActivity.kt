package com.github.danielgalion.pizzatestapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
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
        startActivity(Intent(this, RecipeActivity::class.java))
    }
}