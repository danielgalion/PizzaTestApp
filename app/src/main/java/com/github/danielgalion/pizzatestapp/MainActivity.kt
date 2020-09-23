package com.github.danielgalion.pizzatestapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showLogo()
        title = "RecipeMaster"
    }

    private fun showLogo() {
        Picasso.get().load("https://moodup.team/test/pizza1.jpg").into(logo_iv)
    }

    fun onRecipeFabClick(view: View) {
        startActivity(Intent(this, RecipeActivity::class.java))
    }
}